/**
 * Created by dima-pc on 17.05.2016.
 */

/**
 * routePoint and route comes from server
 * @type {{lat: string, lng: string, extra: {shortDescription: string, description: string}}}
 */
var routePoint = {
    lat: "12",
    lng: "asd",

    extra : {
        shortDescription: "",
        description: ""
    }
};

var route = {
    name: "routeName",
    points: [], //insances of routePoints (see above)
    shortDescription: "",
    description: ""
};




var mapInfo = {
    map: undefined,
    polylines: [], // резерв
    polyline: undefined
};

/**
 * Отрисовывает мапу в DOM-элементе c id=mapElenentId
 * @param mapElementId
 */
function initUserMapByElemId(mapElementId){
    initUserMap(document.getElementById(mapElementId));
}

function initMap() {
    initUserMapByElemId("map");
}

function initUserMap(domElement){
    var googleMapConfig = {
        zoom: 8
    };

    var currentGCoords = browser2GoogleCoords(getLocation());
    if (currentGCoords) {
        googleMapConfig.center = currentGCoords;
    } else {
        googleMapConfig.center = {lat: -34.397, lng: 150.644};
    }

    map = new google.maps.Map(domElement, googleMapConfig);


    mapInfo.map = map;
}

function enableAddPointMode(){
    if (!mapInfo.polyline) {
        var poly = new google.maps.Polyline({
            strokeColor: '#000000',
            strokeOpacity: 1.0,
            strokeWeight: 3
        });
        poly.setMap(map);
        mapInfo.polyline = poly;
    }
    // Add a listener for the click event
    map.addListener('click', addLatLng);
}

// Handles click events on a map, and adds a new point to the Polyline.
function addLatLng(event) {
    var path = mapInfo.polyline.getPath();

    // Because path is an MVCArray, we can simply append a new coordinate
    // and it will automatically appear.
    path.push(event.latLng);

    // Add a new marker at the new plotted point on the polyline.
    var marker = new google.maps.Marker({
        position: event.latLng,
        title: '#' + path.getLength(),
        map: map
    });
}

function saveCurrentRoute() {
    saveRoute(mapInfo.polyline);
}

function saveRoute(route) {

}

function drawRoute(route) {
    if (route.points) {
        //todo make map style
        var poly = new google.maps.Polyline({
            strokeColor: '#000000',
            strokeOpacity: 1.0,
            strokeWeight: 3
        });


        poly.setMap(mapInfo.map);

        for(var index = 0; index < route.points.length; index++) {
            var routePoint = route.points[i];

            var path = poly.getPath();

            // Because path is an MVCArray, we can simply append a new coordinate
            // and it will automatically appear.
            //path.push(event.latLng);
            path.push(event.latLng);

            // Add a new marker at the new plotted point on the polyline.
            var markerConfig = {
                position: event.latLng,
                title: '#' + path.getLength(),
                map: map
            };

            if (routePoint.extra) {
                markerConfig.title = routePoint.extra.shortDescription;
            }

            var marker = new google.maps.Marker(markerConfig);
        }


    } else {
        showError()
    }
}

/**
 * Отображает ошибку c текстом errText внутри элемента errorElement (планируется, что это будет некий div)
 * @param errorElement
 * @param errText
 */
function showError(errorElement, errText){
    //todo
}

/**
 * Получает текущее положение пользователя из информации браузера
 * @returns {*}
 */
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

/**
 *
 * @param browserCoords
 * @returns google coords object or undefined
 */
function browser2GoogleCoords(browserCoords){
    if (browserCoords) {
        var result = {};

        result.lng = browserCoords.longitude;
        result.lat = browserCoords.latitude;

        return result;
    } else {
        return undefined;
    }
}
