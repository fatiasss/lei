// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //--- Local Variables
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/API/coaches');
    self.displayName = 'Paris2024 Coaches List';
    self.error = ko.observable('');
    self.passingMessage = ko.observable('');
    self.coaches = ko.observableArray([]);
    self.currentPage = ko.observable(1);
    self.pagesize = ko.observable(20);
    self.totalRecords = ko.observable(50);
    self.hasPrevious = ko.observable(false);
    self.hasNext = ko.observable(false);
    self.sortMode = ko.observable(1)
    self.searchQ = ko.observable('')
    self.favourite_coaches = ko.observableArray([])
    self.details = ko.observableArray([]);
    self.isTableVisible = ko.observable(true)
    self.isCardVisible = ko.observable(false)
    self.ViewMode = ko.observable('')
    self.suggestions = ko.observableArray([])
        self.toggleViewMode = function () {
        if (self.ViewMode()=="Cards") {
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
            self.favourite_coaches.remove(coach.Id);
            localStorage.setItem('favourite_coaches', JSON.stringify(self.favourite_coaches()));
        } else {
            self.favourite_coaches.push(coach.Id);
            localStorage.setItem('favourite_coaches', JSON.stringify(self.favourite_coaches()));
        }
    }
    self.isFavourite = function (coachesId) {
        return (self.favourite_coaches().includes(coachesId))
    }
    self.loadFavourites = function () {
        try {
            const storedFavourites = localStorage.getItem('favourite_coaches');
            console.log("Stored coaches:", storedFavourites);
            if (storedFavourites) {
                self.favourite_coaches(JSON.parse(storedFavourites));
            } else {
                self.favourite_coaches([]);
            }
        } catch (error) {
            self.favourite_coaches([]);
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
        self.sortMode(1)
        self.searchQ('')
        self.suggestions([])

    }
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
            self.activate(1);
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
        console.log('call: getcoaches...');
        self.loadsort_search()
        var composedUri = self.baseUri() + "?page=" + id + "&pageSize=" + self.pagesize() + "&search=" + self.searchQ();
        ajaxHelper(composedUri, 'GET').done(function (data) {
            console.log("Received data:", data);
            self.currentPage(data.CurrentPage);
            self.hasNext(data.HasNext);
            self.hasPrevious(data.HasPrevious);
            self.pagesize(data.PageSize);
            self.totalPages(data.TotalPages);
            self.totalRecords(data.TotalCoaches);

            fetchCoachDetails(data.Coaches);
        });
    };

    async function fetchCoachDetails(coaches) {
        const detailedCoaches = [];
        for (let coach of coaches) {
            try {
                console.log("error:", coach.Id);
                const details = await ajaxHelper(self.baseUri() + "/" + coach.Id, 'GET');
                details.Photo = details.Photo || 'Images/PersonNotFound.png';
                detailedCoaches.push({ ...coach, ...details });
            } catch (error) {
                console.warn(`error: ${coach.Id}`, error);
                detailedCoaches.push(coach); 
            }
            await new Promise(resolve => setTimeout(resolve, 10));
        }
        self.coaches(detailedCoaches);
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
