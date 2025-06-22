// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //--- Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/api/CountryMedals?Countries=204');
    self.displayName = 'Paris 2024 Medals';
    self.countries = ko.observableArray([]);
    self.medallists = ko.observableArray([]);
    self.chosencountry = ko.observable()
    self.chart1 = null;
    self.chart2 = null;
    self.chart3 = null;



    //--- Page Events
    self.activate = function (id) {
        console.log('CALL: getRoutes...');
        var composedUri = self.baseUri();
        var composedUrim = 'http://192.168.160.58/Paris2024/api/Medals/Top25';
        ajaxHelper(composedUri, 'GET').done(function (data) {
            console.log(data)
            self.countries(data);
            const dataForChart = [];
            self.countries().forEach(function (country) {
                dataForChart.push({ x: country.CountryName, total: country.Total, gold: country.GoldMedal, silver: country.SilverMedal, bronze: country.BronzeMedal  });
            });
            const ctx = document.getElementById('myChart');
            console.log(self.chosencountry())
            let usaData = self.countries().find(country => country.CountryCode === self.chosencountry());
            console.log("usadata", usaData)
            var labels = []
            if (!self.chart1) { 
                self.chart1 = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: self.countries().forEach(function (country) { labels.push(country.CountryName); }),
                        datasets: [{
                            label: '# of Total Medals',
                            data: dataForChart,
                            parsing: {
                                yAxisKey: 'total'
                            },
                            borderWidth: 1,
                            borderColor: '#36A2EB',
                            backgroundColor: '#9BD0F5',
                        }, {
                            label: '# of Gold Medals',
                            data: dataForChart,
                            parsing: {
                                yAxisKey: 'gold'
                            },
                            borderWidth: 1,
                            borderColor: '#36A2EB',
                            backgroundColor: '#FFD700',
                        }, {
                            label: '# of Silver Medals',
                            data: dataForChart,
                            parsing: {
                                yAxisKey: 'silver'
                            },
                            borderWidth: 1,
                            borderColor: '#36A2EB',
                            backgroundColor: '#c0c0c0',

                        }, {
                            label: '# of Bronze Medals',
                            data: dataForChart,
                            parsing: {
                                yAxisKey: 'bronze'
                            },
                            borderWidth: 1,
                            borderColor: '#36A2EB',
                            backgroundColor: '#cd7f32',

                        }
                        ]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                
                });
            }
            const ctx2 = document.getElementById('pie');
            var labels2 = []
            if (self.chart2) self.chart2.destroy();
            if (usaData) {
                self.chart2 = new Chart(ctx2, {
                    type: 'pie',
                    data: {
                        labels: ['Gold Medals', 'Silver Medals', 'Bronze Medals'],
                        datasets: [{
                            label: 'USA medal Ratio',
                            data: [usaData.GoldMedal, usaData.SilverMedal, usaData.BronzeMedal],
                            borderWidth: 1,
                            borderColor: '#36A2EB',
                            backgroundColor: ['#FFD700', '#c0c0c0', '#cd7f32'],
                        },
                        ]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
}
          
        });
        ajaxHelper(composedUrim, 'GET').done(function (data) {
            self.medallists(data);
            let dataForChart = []
            var labels = []
            self.medallists().forEach(function (medallist) {
                dataForChart.push({ x: medallist.Name, total: medallist.Medals, gold: medallist.Gold, silver: medallist.Silver, bronze: medallist.Bronze });
            });
            const ctx3 = document.getElementById('medallists');
            var labels = [];
            self.medallists().forEach(function (medallist) {
                labels.push(medallist.Name);
            });

            var totalData = [];
            var goldData = [];
            var silverData = [];
            var bronzeData = [];

            dataForChart.forEach(function (item) {
                totalData.push(item.total);
                goldData.push(item.gold);
                silverData.push(item.silver);
                bronzeData.push(item.bronze);
            });
            if (!self.chart3) { 
            self.chart3 = new Chart(ctx3, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '# of Total Medals',
                        data: totalData,
                        borderWidth: 1,
                        borderColor: '#36A2EB',
                        backgroundColor: '#9BD0F5',
                    }, {
                        label: '# of Gold Medals',
                        data: goldData,
                        borderWidth: 1,
                        borderColor: '#36A2EB',
                        backgroundColor: '#FFD700',
                    }, {
                        label: '# of Silver Medals',
                        data: silverData,
                        borderWidth: 1,
                        borderColor: '#36A2EB',
                        backgroundColor: '#c0c0c0',
                    }, {
                        label: '# of Bronze Medals',
                        data: bronzeData,
                        borderWidth: 1,
                        borderColor: '#36A2EB',
                        backgroundColor: '#cd7f32',
                    }]
                },
                options: {
                    indexAxis: 'y',
                    scales: {
                        x: {
                            beginAtZero: true
                        }
                    }
                }
            });
            }
        });
        console.log(self.chosencountry())
    };
    //--- Start ....
    self.activate(1);
    console.log("VM initialized!");
}

//--- Internal functions
function ajaxHelper(uri, method, data) {
    return $.ajax({
        type: method,
        url: uri,
        dataType: 'json',
        contentType: 'application/json',
        data: data ? JSON.stringify(data) : null,
        error: function (jqXHR, textStatus, errorThrown) {
            alert("AJAX Call[" + uri + "] Fail...");
        }
    });
}
$(document).ready(function () {
    console.log("Document is ready!");
    var viewModel = new vm()
    ko.applyBindings(viewModel);
    viewModel.activate(1)

});

