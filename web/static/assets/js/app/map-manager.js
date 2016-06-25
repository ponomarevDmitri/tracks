/**
 * Created by dima-pc on 17.05.2016.
 */

var routePoint = {
    coordinate : {
        lat: "12",
        lng: "asd"
    },

    extra : {
        shortDescription: "",
        description: ""
    }
}

var route = {
    name: "routeName",
    points: [],
    shortDescription: "",
    description: ""
}


function initUserMap(mapElementId){
    var currentGCoords = browser2GoogleCoords(getLocation());
    map = new google.maps.Map(document.getElementById(mapElementId), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 8
    });
}

function drawRoute(route) {
    if(route.points) {

    }
}

function showError(errorElement){
    //todo
}


function getLocation() {
    var result = null;
    if (navigator.geolocation) {
        return navigator.geolocation.getCurrentPosition(function(position){
            result = position;
        });

    } else {
        //do nothing
        //x.innerHTML = "Geolocation is not supported by this browser.";
    }
    return result;
}

function browser2GoogleCoords(browserCoords){
    var result = {};

    result.lng = browserCoords.longitude;
    result.lat = browserCoords.latitude;

    return result;
}
