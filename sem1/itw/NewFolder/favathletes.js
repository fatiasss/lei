// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/athletes');
    self.displayName = 'Your Favourite Athletes List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.athletes = ko.observableArray([]);
    self.favourite_athletes = ko.observableArray([])
    self.isTableVisible = ko.observable(true)
    self.isCardVisible = ko.observable(false)
    self.ViewMode = ko.observable('')

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



    //--- Page Events
    self.activate = async function () { //had to rewrite the whole thing so it could have the delay and be asynchronous
        console.log('CALL: getAthletes...');
        self.loadFavourites();
        for (const athleteId of self.favourite_athletes()) {
            const composedUri = `${self.baseUri()}/${athleteId}`;
            await ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data);
                hideLoading();
                self.athletes.push(data);
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
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("ready!");
    var newViewModel = new vm();
    newViewModel.loadView();
    newViewModel.loadFavourites()
    ko.applyBindings(newViewModel);
    newViewModel.activate();
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
});