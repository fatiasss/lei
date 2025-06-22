// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/Teams');
    self.displayName = 'Paris2024 Favourite Teams List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.teams = ko.observableArray([]);
    self.currentPage = ko.observable(1);
    self.pagesize = ko.observable(21);
    self.totalRecords = ko.observable(50);
    self.hasPrevious = ko.observable(false);
    self.hasNext = ko.observable(false);
    self.sportCode = ko.observable('')
    self.searchQ = ko.observable('')
    self.details = ko.observableArray([]);
    self.isTableVisible = ko.observable(true)
    self.suggestions = ko.observableArray([])
    self.favourite_teams = ko.observableArray([])

    self.loadsort_search = function () {
        var storedSportCode = sessionStorage.getItem('sportCode');
        var storedSearchQuery = sessionStorage.getItem('searchQ');

        if (storedSportCode) {
            self.sportCode(storedSportCode);
            $("#sortmode-select").val(storedSportCode);
        }

        if (storedSearchQuery) {
            self.searchQ(storedSearchQuery);
            $("#searchprompt").val(storedSearchQuery);
        }
    }
    self.resetsort_search = function () {
        sessionStorage.setItem('sportCode', '');
        sessionStorage.setItem('searchQ', '');
        self.sportCode(1)
        self.searchQ('')


    }
    self.toggleFavourite = function (sport) {
        if (self.isFavourite(sport.Id)) {
            self.favourite_teams.remove(sport.Id);
            localStorage.setItem('favourite_teams', JSON.stringify(self.favourite_teams()));
        } else {
            self.favourite_teams.push(sport.Id);
            localStorage.setItem('favourite_teams', JSON.stringify(self.favourite_teams()));
        }
    }
    self.isFavourite = function (sportId) {
        return (self.favourite_teams().includes(sportId))
    }
    self.loadFavourites = function () {
        try {
            const storedFavourites = localStorage.getItem('favourite_teams');
            console.log("Stored favourites:", storedFavourites);
            if (storedFavourites) {
                self.favourite_teams(JSON.parse(storedFavourites));
            } else {
                self.favourite_teams([]);
            }
        } catch (error) {
            self.favourite_teams([]);
        }
    };

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
    self.search = function () {
        if (self.searchQ().length >= 3) {
            var composedUri = self.baseUri() + "/Search?q=" + self.searchQ();
            console.log("Searching", composedUri)
            ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log("Received data:", data);
                self.suggestions(data)
                console.log(self.suggestions())

            });
        }
        else {
            self.suggestions([]);
        }
    }
    $(document).ready(function () {
        $("#sport_code").change(function () {
            self.sportCode($(this).find('option:selected').val());
            sessionStorage.setItem('sportCode', self.sportCode());
            self.activate(self.currentPage());
        });

        $("#submit-search-btn").click(function (e) {
            e.preventDefault();
            self.searchQ($('#searchprompt').val());
            sessionStorage.setItem('searchQ', self.searchQ());
            self.activate(1);
        });
        $("#reset-search-btn").click(function (e) {
            e.preventDefault();
            $('#searchprompt').val('');
            self.searchQ('');
            self.sportCode('');
            sessionStorage.removeItem('searchQ')
            sessionStorage.removeItem('sportCode')
            self.activate(1);
        });

    });


    //--- Page Events
    self.activate = async function () { //had to rewrite the whole thing so it could have the delay and be asynchronous
        console.log('CALL: getAthletes...');
        self.loadFavourites();
        for (const athleteId of self.favourite_teams()) {
            const composedUri = `${self.baseUri()}/${athleteId}`;
            await ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data);
                hideLoading();
                self.teams.push(data);
            });
            await new Promise(resolve => setTimeout(resolve, 10)); //maybe as requests tão a ir demasiado rápido??
            //podia só ter usado a sleep function bruh
        };
    };

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
    }
    function hideLoading() {
        $('#myModal').on('shown.bs.modal', function (e) {
            $("#myModal").modal('hide');
        })
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

    //--- start ....
    showLoading();
    var sort = getUrlParameter('sportCode');
    var search = getUrlParameter('search');
    if (sort) { self.sportCode(sort) };
    if (search) { self.searchQ(search) };

    var pg = getUrlParameter('page');
    console.log(pg);
    if (pg == undefined)
        self.activate(1);
    else {
        self.activate(pg);
    }
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("ready!");
    var newViewModel = new vm();
    newViewModel.loadsort_search()
    ko.applyBindings(newViewModel);
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
});