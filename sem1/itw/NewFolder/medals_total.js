var vm = function () {
    console.log("ViewModel initiated...");
    var self = this;

    // API configuration
    self.baseUri = "http://192.168.160.58/Paris2024/API/Medals/Top";
    self.medals = ko.observableArray([]);
    self.page = ko.observable(1);
    self.pageSize = ko.observable(20); // Load 20 items per page
    self.hasMore = ko.observable(true);
    self.error = ko.observable("");

    // Fetch medals data from the API
    self.loadMedals = function () {
        var url = `${self.baseUri}?page=${self.page()}&pageSize=${self.pageSize()}`;
        console.log("Fetching data from:", url);

        self.error(""); // Clear any previous errors

        $.getJSON(url)
            .done(function (data) {
                console.log("API response:", data);

                // Assuming the API response contains an object with 'Medals' array and 'HasMore' boolean
                if (data && data.Medals && data.Medals.length > 0) {
                    // Append new medals to the observable array
                    self.medals(self.medals().concat(data.Medals));

                    // Update hasMore flag if there's no more data
                    self.hasMore(data.HasMore);
                } else {
                    console.log("No data received or empty result.");
                    self.hasMore(false);
                }
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
                console.error("Error loading data:", textStatus, errorThrown);
                self.error("Failed to load medals data. Please try again.");
            });
    };

    // Load more medals when the button is clicked
    self.loadMore = function () {
        if (self.hasMore()) {
            self.page(self.page() + 1); // Increment the page number
            self.loadMedals(); // Fetch the next page of data
        }
    };

    // Reset medals and pagination
    self.reset = function () {
        self.page(1); // Reset to the first page
        self.hasMore(true); // Enable further loading
        self.medals([]); // Clear the medals array
        self.loadMedals(); // Reload the data
    };

    // Initial load
    self.loadMedals();
};

$(document).ready(function () {
    console.log("Document ready...");
    ko.applyBindings(new vm());
});


    self.loadMore = function () {
        self.page(self.page() + 1);
        self.loadMedals();
    };

    // Initial load
    self.loadMedals();

$(document).ready(function () {
    ko.applyBindings(new vm());
});