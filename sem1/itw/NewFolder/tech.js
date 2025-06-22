// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //--- Local Variables
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/Technical_officials');
    self.displayName = 'Paris2024 Technical Officials List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.techs = ko.observableArray([]);
    self.currentPage = ko.observable(1);
    self.pagesize = ko.observable(20);
    self.totalRecords = ko.observable(50);
    self.hasPrevious = ko.observable(false);
    self.hasNext = ko.observable(false);
    self.sortMode = ko.observable(1)
    self.searchQ = ko.observable('')
    self.favourite_techs = ko.observableArray([])
    self.details = ko.observableArray([]);
    self.isTableVisible = ko.observable(true)
    self.isCardVisible = ko.observable(false)
    self.ViewMode = ko.observable('')
    self.suggestions = ko.observableArray([])
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
    self.toggleFavourite = function (coach) {
        if (self.isFavourite(coach.Id)) {
            self.favourite_techs.remove(coach.Id);
            localStorage.setItem('favourite_techs', JSON.stringify(self.favourite_techs()));
        } else {
            self.favourite_techs.push(coach.Id);
            localStorage.setItem('favourite_techs', JSON.stringify(self.favourite_techs()));
        }
    }
    self.isFavourite = function (techsId) {
        return (self.favourite_techs().includes(techsId))
    }
    self.loadFavourites = function () {
        try {
            const storedFavourites = localStorage.getItem('favourite_techs');
            console.log("Stored techs:", storedFavourites);
            if (storedFavourites) {
                self.favourite_techs(JSON.parse(storedFavourites));
            } else {
                self.favourite_techs([]);
            }
        } catch (error) {
            self.favourite_techs([]);
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
    self.loadsort_search = function () {
        var storedSortMode = sessionStorage.getItem('sortMode');
        console.log(storedSortMode)
        var storedSearchQuery = sessionStorage.getItem('searchQ');
        console.log(storedSearchQuery)

        if (storedSortMode) {
            self.sortMode(storedSortMode);
            $("#sortmode-select").val(storedSortMode);
        }

        if (storedSearchQuery) {
            self.searchQ(storedSearchQuery);
            $("#searchprompt").val(storedSearchQuery);
        }
    }
    self.resetsort_search = function () {
        sessionStorage.setItem('sortMode', 1);
        sessionStorage.setItem('searchQ', '');
        self.sortMode(1);
        self.searchQ('');
        self.suggestions([]);


    }
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

            });
        }
        else {
            self.suggestions([]);
        }
    }
    $(document).ready(function () {
        $("#sortmode-select").change(function () {
            self.sortMode($(this).find('option:selected').val());
            sessionStorage.setItem('sortMode', self.sortMode());
            self.activate(self.currentPage());
        }); 

        $("#submit-search-btn").click(function (e) {
            e.preventDefault();
            self.searchQ($('#searchprompt').val());
            sessionStorage.setItem('searchQ', self.searchQ());
            self.currentPage()
            self.totalPages(0)
            console.log("before fetch")
            self.techs(fetchDetailsForSearchResults(self.suggestions()))
            self.suggestions([])
        });
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
    self.activate = function (id) {
            console.log('call: gettechs...');
        self.loadsort_search()
            var composedUri = self.baseUri() + "?page=" + id + "&pageSize=" + self.pagesize()
            ajaxHelper(composedUri, 'GET').done(function (data) {
                console.log("Received data:", data);
                self.currentPage(data.CurrentPage);
                self.hasNext(data.HasNext);
                self.hasPrevious(data.HasPrevious);
                self.pagesize(data.PageSize);
                self.totalPages(data.TotalPages);
                self.totalRecords(data.TotalOfficials);


                fetchDetailsForSearchResults(data.Technical_officials);
            });

    };

    async function fetchCoachDetails(techs) {
        const detailedtechs = [];
        for (let Technical_officials of techs) {
            try {
                const details = await ajaxHelper(self.baseUri() + "/" + Technical_officials.Id, 'GET');
                details.Photo = details.Photo || 'Images/PersonNotFound.png';
                detailedtechs.push({ ...Technical_officials, ...details });
                console.log(detailedtechs)
            } catch (error) {
                console.warn(`error: ${Technical_officials.Id}`, error);
                detailedtechs.push(Technical_officials);
            }
            await new Promise(resolve => setTimeout(resolve, 10));
        }
        self.techs(detailedtechs);
        hideLoading();
    }
    async function fetchDetailsForSearchResults(searchResults) { //new function so it works for search results since the structure is different
        const detailedtechs = [];

        for (let item of searchResults) {
            try {
                const details = await ajaxHelper(`${self.baseUri()}/${item.Id}`, 'GET');
                console.log("Details object:", details);
                details.Photo = details.Photo || 'Images/PersonNotFound.png';
                details.Organisation = details.Organisation || "Unknown";
                detailedtechs.push({ ...item, ...details });
            } catch (error) {
                console.warn(`Error fetching details for tech with id: ${item.id}`, error);
                item.Organisation = "Unknown"; // Default to avoid binding issues
                detailedtechs.push(item);
            }
            await new Promise(resolve => setTimeout(resolve, 10));
        }

        console.log("Enriched search results:", detailedtechs);
        self.techs(detailedtechs);
        hideLoading();
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
                console.error("AJAX Call Failed: ", {
                    uri: uri,
                    response: jqXHR.responseText,
                    status: jqXHR.status,
                    textStatus: textStatus
                });
                hideLoading();
                self.error(`Error ${jqXHR.status}: ${errorThrown}`);
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
        });
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


    //--- Start
    showLoading();
    var sort = getUrlParameter('sortMode');
    var search = getUrlParameter('search');
    if (sort) { self.sortMode(sort) };
    if (search) { self.searchQ(search) };
    var pg = getUrlParameter('page');
    console.log("Page parameter:", pg);
    if (pg === undefined)
        self.activate(1);
    else {
        self.activate(pg);
    }
    console.log("VM initialized!");
};

$(document).ready(function () {
    console.log("Document ready!");
    var newViewModel = new vm();
    newViewModel.loadView();
    newViewModel.loadFavourites()
    newViewModel.loadsort_search()
    ko.applyBindings(newViewModel);
});

$(document).ajaxComplete(function (event, xhr, options) {
    $("#myModal").modal('hide');
});
