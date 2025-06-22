// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/Cycling_Tracks');
    self.displayName = 'Paris2024 Cycling Tracks';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.athletes = ko.observableArray([]);
    self.currentPage = ko.observable(1);
    self.pagesize = ko.observable(21);
    self.totalRecords = ko.observable(50);
    self.hasPrevious = ko.observable(false);
    self.hasNext = ko.observable(false);
    self.event = ko.observable('')
    self.stage = ko.observable('')
    self.favourite_athletes = ko.observableArray([])
    self.details = ko.observableArray([]);
    self.isTableVisible = ko.observable(true)
    self.isCardVisible = ko.observable(false)
    self.ViewMode = ko.observable('')
    self.events = ko.observableArray([])
    self.events2 = ko.observableArray([])
    self.events3 = ko.observableArray([])
    self.stages = ko.observableArray([])
    self.possiblecomps = ko.observableArray([])
    self.isLoading = ko.observable(false)

    self.toggleViewMode = function () {
        if (self.ViewMode() == "Cards") {
            self.isTableVisible(true)
            self.isCardVisible(false)
            self.ViewMode("Table");
            localStorage.setItem("ViewMode", "Table")
        }
        else {

            self.isTableVisible(false)
            self.isCardVisible(true)
            self.ViewMode("Cards");
            localStorage.setItem("ViewMode", "Cards")
        }

    }
    self.toggleFavourite = function (athlete) {
        if (self.isFavourite(athlete.Id)) {
            self.favourite_athletes.remove(athlete.Id);
            localStorage.setItem('favourite_athletes', JSON.stringify(self.favourite_athletes()));
        } else {
            self.favourite_athletes.push(athlete.Id);
            localStorage.setItem('favourite_athletes', JSON.stringify(self.favourite_athletes()));
        }
    }
    self.isFavourite = function (athleteId) {
        return (self.favourite_athletes().includes(athleteId))
    }
    self.loadFavourites = function () {
        try {
            const storedFavourites = localStorage.getItem('favourite_athletes');
            console.log("Stored favourites:", storedFavourites);
            if (storedFavourites) {
                self.favourite_athletes(JSON.parse(storedFavourites));
            } else {
                self.favourite_athletes([]);
            }
        } catch (error) {
            self.favourite_athletes([]);
        }
    };
    self.loadView = function () {
        const ViewMode = localStorage.getItem('ViewMode');
        self.ViewMode(ViewMode)
        if (ViewMode == "Cards") {
            self.isTableVisible(false)
            self.isCardVisible(true)
        }
        else {
            self.isTableVisible(true)
            self.isCardVisible(false)
        }
    }
    self.findStages = function () {
        console.log('in findstage, current event', self.event());
        sleep(100)
        if (self.event()) {
            var composedUri = self.baseUri() + "?EventId=" + self.event() + "&StageId=";
            console.log(composedUri);
            ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data);
                let stagesArray = [];
                for (let event of data) {
                    if (!stagesArray.some(stage => stage.StageId === event.StageId)) {
                        try {
                            stagesArray.push({
                                StageId: event.StageId,
                                StageName: event.StageName
                            });
                        } catch (error) {
                            console.warn(`error: ${event.StageId}`, error);
                            stagesArray.push(event);
                        }
                        sleep(100);
                    }

                }
                self.stages(stagesArray);
                console.log(self.stages())
                hideLoading();
            });
        } else {
            console.warn("No event selected.");
        }
        self.activate(1)
    };
    self.event.subscribe(function (newValue) {
        console.log('Event changed to:', newValue);
        self.findStages();
    });
    self.stage.subscribe(function (newValue) {
        console.log('Event changed to:', newValue);
        self.activate(1);
    });

    self.previousPage = ko.computed(function () {
        return self.currentPage() * 1 - 1;
    }, self);
    self.nextPage = ko.computed(function () {
        return self.currentPage() * 1 + 1;
    }, self);
    self.fromRecord = ko.computed(function () {
        return self.previousPage() * self.pagesize() + 1;
    }, self);
    self.toRecord = ko.computed(function () {
        return Math.min(self.currentPage() * self.pagesize(), self.totalRecords());
    }, self);
    self.totalPages = ko.observable(0);
    self.pageArray = function () {
        var list = [];
        var size = Math.min(self.totalPages(), 9);
        var step;
        if (size < 9 || self.currentPage() === 1)
            step = 0;
        else if (self.currentPage() >= self.totalPages() - 4)
            step = self.totalPages() - 9;
        else
            step = Math.max(self.currentPage() - 5, 0);

        for (var i = 1; i <= size; i++)
            list.push(i + step);
        return list;
    };
    //--- Page Events
    self.activate = function (id) {
        if (self.event() == '') {
            console.log('CALL: calling all events...');
            self.stage('')
            self.event('')
            self.stages([])
            var composedUri = self.baseUri() + "/Events";
            ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data)
                self.events(data)
                self.possiblecomps(data)
            });
            hideLoading();


        }
        else {
            if (self.stage() == '') {
                console.log('CALL: calling');
                sleep(100)
                self.stage('')
                let selectedEvent
                let selectedStage
                if (self.event() == undefined || self.event() == '' || self.event() == 'Select an Event') { selectedEvent = '' } else { selectedEvent = self.event() }
                if (self.stage() == undefined || self.stage() == '' || self.stage() == 'Select a Stage') { selectedStage = '' } else { selectedStage = self.stage() }
                var composedUri = self.baseUri() + "?EventId=" + selectedEvent + "&StageId=";
                console.log(composedUri, "for events 2")

                ajaxHelper(composedUri, 'GET').done(function (data) {
                    self.events2(data)
                    //fetchCoachDetails2(data)
                    console.log("Events before binding:", self.events());
                });
                hideLoading();


            }
            else {
                console.log('CALL: calling');
                sleep(100)
                let selectedEvent
                let selectedStage
                if (self.event() == undefined || self.event() == '' || self.event() == 'Select an Event') { selectedEvent = '' } else { selectedEvent = self.event() }
                if (self.stage() == undefined || self.stage() == '' || self.stage() == 'Select a Stage') { selectedStage = '' } else { selectedStage = self.stage() }
                var composedUri = self.baseUri() + "?EventId=" + selectedEvent + "&StageId=" + selectedStage;
                console.log(composedUri, "for events 3")

                ajaxHelper(composedUri, 'GET').done(function (data) {
                    self.events3(data)
                    //fetchCoachDetails3(data)
                    console.log("Events before binding:", self.events());
                });
                hideLoading();


            }

        }


    }

    //--- Internal functions
    function ajaxHelper(uri, method, data) {
        self.error(''); // Clear error message
        return $.ajax({
            type: method,
            url: uri,
            dataType: 'json',
            contentType: 'application/json',
            data: data ? JSON.stringify(data) : null,
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("AJAX Call[" + uri + "] Fail...");
                hideLoading();
                self.error(errorThrown);
            }
        });
    }

    function sleep(milliseconds) {
        const start = Date.now();
        while (Date.now() - start < milliseconds);
    }

    function showLoading() {
        $("#myModal").modal('show', {
            backdrop: 'static',
            keyboard: false
        });
        self.isLoading(true)

    }
    function hideLoading() {
        $('#myModal').on('shown.bs.modal', function (e) {
            $("#myModal").modal('hide');
        })
        self.isLoading(false)
    }

    function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
        console.log("sPageURL=", sPageURL);
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };
    async function fetchCoachDetails2(coaches) {
        showLoading()
        const detailedCoaches = [];
        for (let coach of coaches) {
            try {
                const details = await ajaxHelper(self.baseUri() + "/" + coach.Id, 'GET');
                detailedCoaches.push({ ...coach, ...details });
            } catch (error) {
                console.warn(`error: ${coach.Id}`, error);
                detailedCoaches.push(coach);
            }
            await new Promise(resolve => setTimeout(resolve, 10));
        }
        self.events2(detailedCoaches);
        console.log(self.events2())
        hideLoading();
    }
    async function fetchCoachDetails3(coaches) {
        showLoading()
        const detailedCoaches = [];
        for (let coach of coaches) {
            try {
                console.log("error:", coach.Id);
                const details = await ajaxHelper(self.baseUri() + "/" + coach.Id, 'GET');
                detailedCoaches.push({ ...coach, ...details });
            } catch (error) {
                console.warn(`error: ${coach.Id}`, error);
                detailedCoaches.push(coach);
            }
            await new Promise(resolve => setTimeout(resolve, 10));
        }
        self.events3(detailedCoaches);
        hideLoading();
    }
    //--- start ....
    showLoading();
    self.activate(1)
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("ready!");
    var newViewModel = new vm();
    newViewModel.loadView();
    newViewModel.loadFavourites()
    ko.applyBindings(newViewModel);
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
});