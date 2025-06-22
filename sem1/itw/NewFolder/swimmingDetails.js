// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/Swimmings');
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.details = ko.observableArray([]);
    var id = getUrlParameter("id");
    self.WikipediaLink = ko.observable('');
    self.athletes = ko.observableArray([])
    self.medals = ko.observableArray([])
    self.comps = ko.observableArray([])
    self.teams = ko.observableArray([])
    self.coaches = ko.observableArray([])
    //--- Page Events
    self.activate = function (id) {
        console.log('CALL: getDetails...');
        var composedUri = self.baseUri() + "/" + id;
        console.log(composedUri)
        ajaxHelper(composedUri, 'GET').done(function (data) {
            console.log(data);
            hideLoading();
            self.details(data);
            console.log(data)
            self.linkableName = ko.observable('')
            self.WikipediaLink(getWikipediaArticle(data.Name));
        });
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
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };
    function getWikipediaArticle(keyword) {
        if (!keyword) return
        var searchword = keyword + " Olympics"
        console.log("Wikipedia Search Word:", searchword);
        var wikipediaUri = `https://en.wikipedia.org/w/api.php?action=query&format=json&origin=*&list=search&srsearch=${encodeURIComponent(searchword)}`;
        ajaxHelper(wikipediaUri, 'GET').done(function (response) {
            console.log('Wikipedia API Response:', response);
            if (response.query && response.query.search && response.query.search.length > 0) {
                var title = response.query.search[0].title;
                self.WikipediaLink(`https://en.wikipedia.org/wiki/${encodeURIComponent(title)}`);
            } else {
                self.WikipediaLink('No article found.');
            }
        }).fail(function () {
            self.WikipediaLink('Error fetching Wikipedia article.');
        });


    }

    //--- start ....
    showLoading();
    self.activate(id);
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("ready!");
    ko.applyBindings(new vm());
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
})