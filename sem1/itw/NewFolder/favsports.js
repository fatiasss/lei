// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/Sports');
    self.displayName = 'Paris2024 Sports List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.searchQ = ko.observable('');
    self.sports = ko.observableArray([])
    self.suggestions = ko.observableArray([])
    self.isTableVisible = ko.observable(true)
    self.isCardVisible = ko.observable(false)
    self.ViewMode = ko.observable('')
    self.favourite_sports = ko.observableArray([])
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
    self.toggleFavourite = function (sport) {
        if (self.isFavourite(sport.Id)) {
            self.favourite_sports.remove(sport.Id);
            localStorage.setItem('favourite_sports', JSON.stringify(self.favourite_sports()));
        } else {
            self.favourite_sports.push(sport.Id);
            localStorage.setItem('favourite_sports', JSON.stringify(self.favourite_sports()));
        }
    }
    self.isFavourite = function (sportId) {
        return (self.favourite_sports().includes(sportId))
    }
    self.loadFavourites = function () {
        try {
            const storedFavourites = localStorage.getItem('favourite_sports');
            console.log("Stored favourites:", storedFavourites);
            if (storedFavourites) {
                self.favourite_sports(JSON.parse(storedFavourites));
            } else {
                self.favourite_sports([]);
            }
        } catch (error) {
            self.favourite_sports([]);
        }
    };
    self.search = function () {
        if (self.searchQ().length >= 3) {
            var composedUri = self.baseUri() + "/Search?q=" + self.searchQ();
            console.log("Searching", composedUri)
            ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log("Received data:", data);
                self.suggestions(data)

            });
        }
        else {
            self.suggestions([]);
        }
    }
    $(document).ready(function () {
        $("#reset-search-btn").click(function (e) {
            e.preventDefault();
            $('#searchprompt').val('');
            self.searchQ('');
            sessionStorage.removeItem('searchQ')
            sessionStorage.removeItem('sortMode')
            self.activate(1);
        });

    });

    //--- Page Events
    self.activate = async function () { //had to rewrite the whole thing so it could have the delay and be asynchronous
        console.log('CALL: getAthletes...');
        self.loadFavourites();
        for (const athleteId of self.favourite_sports()) {
            const composedUri = `${self.baseUri()}/${athleteId}`;
            await ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data);
                hideLoading();
                self.sports.push(data);
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
    var search = getUrlParameter('search');
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("ready!");
    var newViewModel = new vm();
    newViewModel.loadView();
    newViewModel.loadFavourites()
    newViewModel.activate(1)
    ko.applyBindings(newViewModel);
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
});