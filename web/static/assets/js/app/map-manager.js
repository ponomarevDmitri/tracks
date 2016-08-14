/**
 * Created by dima-pc on 17.05.2016.
 */

/**
 * routePoint and route comes from server
 * @type {{lat: string, lng: string, extra: {shortDescription: string, description: string}}}
 */
var routePoint = {
    lat:  121212.32121,
    lng: 2311321.21312321,

    extra: {
        shortDescription: "",
        description: ""
    }
};
var route = {
    name: "routeName",
    route: [], //insances of routePoints (see above)
    shortDescription: "",
    description: ""
};
//============================================================================


// mapRoute model on a client
var mapRoute = {
    name: "routeName",
    points: [], //insances of routePoints (see above)
    shortDescription: "",
    description: ""
};

function RouteModel(name, description, shortDescription) {
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.points = []
}

function RoutePointModel(latlng, name, description, shortDescription) {
    this.latlng = latlng;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;

}




var mapInfo = {
    map: undefined,
    polylines: [], // резерв
    polyline: undefined,
    routeModel: new RouteModel("Some route name", "descr", "shortDescr")
};

//======================================================================
// работа с маршрутами (конвертация и т.п.)
//@param routeModel - instance of RouteModel

/**
 * Конвертирование локальной модели маршрута в серверную модель
 */
function convert2ServerRouteModel(routeModel) {
    var routePoints = routeModel.points;

    var resultRoute = {
        points : []
    };

    for (var i = 0; i < routePoints.length; i++) {
        var convertedPointModel = convert2ServerPointModel(routePoints[i]);
        resultRoute.points.push(convertedPointModel);
    }

    resultRoute.name = routeModel.name;
    resultRoute.description = routeModel.description;
    resultRoute.shortDescription = routeModel.shortDescription;

    return resultRoute;
}

/**
 * Конвертирование локальной модели точки в модель серверную
 */
function convert2ServerPointModel(routePointModel) {
    var resultModel = {};

    resultModel.lat = routePointModel.latlng.lat();
    resultModel.lng = routePointModel.latlng.lng();
    resultModel.name = routePointModel.name;
    resultModel.description = routePointModel.description;
    resultModel.shortDescription = routePointModel.shortDescription;

    return resultModel;
}
//==========================================================


/**
 * Отрисовывает мапу в DOM-элементе c id=mapElenentId
 * @param mapElementId
 */
function initUserMapByElemId(mapElementId) {
    initUserMap(document.getElementById(mapElementId));
}

function initMap() {
    initUserMapByElemId("map");
}

function initUserMap(domElement) {
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

function enableAddPointMode() {
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
    mapInfo.routeModel.points.push(new RoutePointModel(event.latLng, "", "", ""))
}

function saveCurrentRoute() {
    saveRoute(mapInfo);
}

function saveRoute(mapInfo) {
    $.ajax({
        type: "POST",
        url: "/routes/create",
        data: JSON.stringify(convert2ServerRouteModel(mapInfo.routeModel)),
        dataType: "json",
        contentType: "application/json"
    })
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

        for (var index = 0; index < route.points.length; index++) {
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
function showError(errorElement, errText) {
    //todo
}

/**
 * Получает текущее положение пользователя из информации браузера
 * @returns {*}
 */
function getLocation() {
    var result = null;
    if (navigator.geolocation) {
        return navigator.geolocation.getCurrentPosition(function (position) {
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
function browser2GoogleCoords(browserCoords) {
    if (browserCoords) {
        var result = {};

        result.lng = browserCoords.longitude;
        result.lat = browserCoords.latitude;

        return result;
    } else {
        return undefined;
    }
}
