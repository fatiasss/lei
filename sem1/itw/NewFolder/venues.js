// ViewModel KnockOut
var vm = function () {
    console.log('ViewModel initiated...');
    //---Variáveis locais
    var self = this;
    self.baseUri = ko.observable('http://192.168.160.58/Paris2024/api/Venues');
    self.displayName = 'Paris 2024 Torch Route';
    self.venues = ko.observableArray([]);

    //--- Page Events
    self.activate = function (id) {
        console.log('CALL: getRoutes...');
        var composedUri = 'http://192.168.160.58/Paris2024/api/Venues';
        ajaxHelper(composedUri, 'GET').done(function (data) {
            console.log(data)
            fetchCoachDetails(data, self).then(function () {
                addPins(self.venues());
            });
        });
    };

    //--- start ....
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
async function fetchCoachDetails(venues, self) {
    const detailedVenues = [];
    for (let venue of venues) {
        try {
            const details = await ajaxHelper('http://192.168.160.58/Paris2024/api/Venues' + "/" + venue.Id, 'GET');
            console.log("Details fetched:", details);
            detailedVenues.push({ ...venue, ...details });

        } catch (error) {
            console.warn(`error: ${venue.Id}`, error);
            detailedVenues.push(venue);
        }
        await new Promise(resolve => setTimeout(resolve, 10));
    }
    self.venues(detailedVenues);
    hideLoading();
}
let markersClusterGroup;
function addPins(venues) {

    markersClusterGroup = L.markerClusterGroup();

    venues.forEach(function (venue) {
        const lat = parseFloat(venue.Lat);
        const lon = parseFloat(venue.Lon);

        if (!isNaN(lat) && !isNaN(lon)) {
            let sportNames = ""
            venue.Sports.forEach(function (sport) {
                sportNames+= sport.Name + ' '
            })
            const marker= L.marker([lat, lon])
                .bindPopup(
                    "<b>" + venue.Name + "</b><br>" + "Id-" + venue.Id + "</b><br>" + 'Sports:' + sportNames + '<a href="' + venue.Url + '" target="_blank">More Info</a>'
                    
            )
            markersClusterGroup.addLayer(marker);
        } else {
            console.error("Invalid coordinates for location: " + venue.Name);
        }

    });
    map.addLayer(markersClusterGroup);
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

$('document').ready(function () {
    console.log("ready!");
    markers = []
    map = L.map('map').setView([46.81543809456121, 1.755870546231156], 5);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);
    ko.applyBindings(new vm());
});

