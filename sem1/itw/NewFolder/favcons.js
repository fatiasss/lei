// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/NOCs');
    self.displayName = 'Your Favourite National Olympic Committees List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.cons = ko.observableArray([]);
    self.favourite_cons = ko.observableArray([])
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
    self.toggleFavourite = function (con) {
        if (self.isFavourite(con.Id)) {
            self.favourite_cons.remove(con.Id);
            localStorage.setItem('favourite_cons', JSON.stringify(self.favourite_cons()));
        } else {
            self.favourite_cons.push(con.Id);
            localStorage.setItem('favourite_cons', JSON.stringify(self.favourite_cons()));
        }
    }
    self.isFavourite = function (conId) {
        return (self.favourite_cons().includes(conId))
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
            const storedFavourites = localStorage.getItem('favourite_cons');
            console.log("Stored favourites:", storedFavourites);
            if (storedFavourites) {
                self.favourite_cons(JSON.parse(storedFavourites));
            } else {
                self.favourite_cons([]);
            }
        } catch (error) {
            self.favourite_cons([]);
        }
    };



    //--- Page Events
    self.activate = async function () { //had to rewrite the whole thing so it could have the delay and be asynchronous
        console.log('CALL: getcons...');
        self.loadFavourites();
        for (const conId of self.favourite_cons()) {
            const composedUri = `${self.baseUri()}/${conId}`;
            await ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log(data);
                hideLoading();
                self.cons.push(data);
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