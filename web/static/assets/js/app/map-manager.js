/**
 * Created by dima-pc on 17.05.2016.
 */

/**
 * routePoint and route comes from server
 * @type {{lat: string, lng: string, extra: {shortDescription: string, description: string}}}
 */
var routePoint = {
    lat: 121212.32121,
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


/**
 * Клиентская модель маршрута
 * @param name имя
 * @param description описание
 * @param shortDescription краткое описание
 * @constructor
 */
function RouteModel(name, description, shortDescription) {
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.points = []
}

/**
 *
 * @param latlng обхект в формате  {lat: 11, lng: 12}
 * @param name
 * @param description
 * @param shortDescription
 * @constructor
 */
function RoutePointModel(latlng, name, description, shortDescription) {
    this.latlng = latlng;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;

}

/**
 *
 * @param mapInfoParameters for future use
 * @constructor
 */
function MapInfo(mapInfoParameters) {
    this.map = undefined; // instanceof new google.maps.Map(domElement, googleMapConfig);
    this.polylines = []; // резерв
    this.polyline = undefined; //instanceof  new google.maps.Polyline
    this.routeModel = new RouteModel("Some route name", "descr", "shortDescr");

    // region PRIVATE MapInfo members

    // endregion


    // region PUBLIC MapInfo members
    /**
     * Удаляет линию на карте.
     */
    this.deleteAllPolilines = function () {
        this.routeModel = null;
        if (this.polyline) {
            this.polyline.setMap(null);
            this.polyline = null;
        }
    };

    /**
     * Очищает карту. Отрисовывает на карте this.map клиентскую модель маршрута routeModel.
     * @param routeModel модель маршрута в формате RouteModel
     */
    this.drawRoute = function (routeModel) {
        this.deleteAllPolilines();
        createAndAddRoutePolilyne(this);

        this.routeModel = routeModel;

        var points = routeModel.points;

        for (var pointIndex = 0; pointIndex < points.length; pointIndex++) {
            createMarkerAndAddToPath(points[pointIndex], this);
        }
    };

    /**
     * Добавляет точку на карту
     * @param routePointModel описание точки в формате RoutePointModel
     */
    this.addPoint = function (routePointModel) {
        if (routePointModel) {
            mapInfo.routeModel.points.push(routePointModel)

        }
    };
    // endregion
}


var mapInfo = new MapInfo();


/*{
    map: undefined, // instanceof new google.maps.Map(domElement, googleMapConfig);
    polylines: [], // резерв
    polyline: undefined, //instanceof  new google.maps.Polyline
    routeModel: new RouteModel("Some route name", "descr", "shortDescr"),

    /!**
     * Удаляет маршрут, описанный на карте this.map и содержащийся в точках this.polyline
     *!/
    deleteAllPolilines: function () {
        this.routeModel = null;
        if (this.polyline) {
            this.polyline.setMap(null);
            this.polyline = null;
        }
    },

    /!**
     * Очищает карту. Отрисовывает на карте this.map клиентскую модель маршрута routeModel.
     * @param routeModel модель маршрута в формате RouteModel
     *!/
    drawRoute: function (routeModel) {
        this.deleteAllPolilines();
        createAndAddRoutePolilyne(this);

        this.routeModel = routeModel;

        var points = routeModel.points;

        for (var pointIndex = 0; pointIndex < points.length; pointIndex++) {
            createMarkerAndAddToPath(points[pointIndex], this);
        }
    },

    addPoint: function (routePointModel) {
        if (routePointModel) {
            mapInfo.routeModel.points.push(routePointModel)

        }
    }
};*/

/**
 * Создает маркер google-карт из объекта point
 * @param point объект в формате RoutePointModel
 * @param mapInfo объект mapInfo
 */
function createMarkerAndAddToPath(point, mapInfo) {
    var path = mapInfo.polyline.getPath();
    var coordinates = {
        lat: point.latlng.lat,
        lng: point.latlng.lng
    };
    // Because path is an MVCArray, we can simply append a new coordinate
    // and it will automatically appear.
    path.push({
        lat: function () {
            return coordinates.lat;
        },
        lng: function () {
            return coordinates.lng;
        }

    });

    // Add a new marker at the new plotted point on the polyline.
    var marker = new google.maps.Marker({
        position: coordinates,
        title: '#' + path.getLength(),
        map: mapInfo.map
    });
}


//region
//======================================================================
// работа с маршрутами (конвертация и т.п.)
//@param routeModel - instance of RouteModel

/**
 * Конвертирование клиентской модели маршрута в серверную модель
 */
function convert2ServerRouteModel(routeModel) {
    var routePoints = routeModel.points;

    var resultRoute = {
        points: []
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
 * Конвертирование клиентской модели точки в модель серверную
 */
function convert2ServerPointModel(routePointModel) {
    var resultModel = {};

    resultModel.lat = routePointModel.latlng.lat;
    resultModel.lng = routePointModel.latlng.lng;
    resultModel.name = routePointModel.name;
    resultModel.description = routePointModel.description;
    resultModel.shortDescription = routePointModel.shortDescription;

    return resultModel;
}


/**
 * Конвертирование списка серверных моделей маршрута (приходящих из RestRouteController#getUserRoutes())
 */
function convertFromServerRouteModelList(serverRouteModelList) {
    var result = [];

    if (serverRouteModelList) {
        for (var i = 0; i < serverRouteModelList.length; i++) {
            result.push(convertFromServerRouteModel(serverRouteModelList[i]));
        }
    }

    return result;
}

/**
 * Конвертирование серверную модели в клиентскую модель маршрута
 */
function convertFromServerRouteModel(serverRouteModel) {
    var serverRoutePoints = serverRouteModel.points;

    var localModelRoute = {
        points: []
    };

    for (var i = 0; i < serverRoutePoints.length; i++) {
        var convertedPointModel = convertFromServerPointModel(serverRoutePoints[i]);
        localModelRoute.points.push(convertedPointModel);
    }

    localModelRoute.id = serverRouteModel.id;
    localModelRoute.name = serverRouteModel.name;
    localModelRoute.description = serverRouteModel.description;
    localModelRoute.shortDescription = serverRouteModel.shortDescription;

    return localModelRoute;
}

/**
 * Конвертирование серверной модели точки в модель клиентскую
 */
function convertFromServerPointModel(serverPointModel) {
    var resultModel = {};

    resultModel.latlng = {};
    resultModel.latlng.lat = serverPointModel.lat;
    resultModel.latlng.lng = serverPointModel.lng;
    resultModel.name = serverPointModel.name;
    resultModel.description = serverPointModel.description;
    resultModel.shortDescription = serverPointModel.shortDescription;

    return resultModel;
}

//==========================================================
//endregion

//region работа по отрисовке маршрута, содержащегося в routeModel на карте, описанной в mapInfo
//=========================================================

//=========================================================
//endregion


//region работа с картой на конкретной странице (тестовой)


function UserRoutes(routeClientList) {
    this.routes = routeClientList;
}

var userRoutes;

function loadAllRoutesForCurrentUser() {
    jQuery.ajax({
        type: "GET",
        url: "/routes/user",
        success: function (data) {
            var clientModelRoutes = convertFromServerRouteModelList(data);
            userRoutes = new UserRoutes(clientModelRoutes);
            for (var i = 0; i < clientModelRoutes.length; i++) {
                var clientModelRouteObject = clientModelRoutes[i];
                $("#routeList").append(
                    '<li class="list-group-item" onclick="clearMapAndDrawRouteWithId(' + clientModelRouteObject.id + ')"></li>');
            }
        }
    })
}


/**
 * Отрисовывает соответсвующий маршрут по его id.
 * @param routeId идентификатор маршрута.
 */
function clearMapAndDrawRoute() { //????
    var routeId = $("#routeNumberInput").val();

    jQuery.ajax({
        type: "GET",
        url: "/routes/" + routeId,
        success: function (data) {
            mapInfo.drawRoute(convertFromServerRouteModel(data));
            //cleanAndDrawRouteFromClientModel(convertFromServerRouteModel(data), mapInfo);
        }
    })
}

//endregion

/**
 * Отрисовывает мапу в DOM-элементе c id=mapElenentId
 * @param mapElementId
 */
function initUserMapByElemId(mapElementId) {
    initUserMap(document.getElementById(mapElementId));
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
    enableAddPointModeToMapInfo(mapInfo);
}

function enableAddPointModeToMapInfo(mapInfo) {
    if (!mapInfo.polyline) {
        createAndAddRoutePolilyne(mapInfo);
    }
    // Add a listener for the click event
    mapInfo.map.addListener('click', addLatLngFromEvent);
}

function createAndAddRoutePolilyne(mapInfo) {
    var poly = new google.maps.Polyline({
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    poly.setMap(mapInfo.map);
    mapInfo.polyline = poly;
}

// Handles click events on a map, and adds a new point to the Polyline.
function addLatLngFromEvent(event) {

    var coordinates = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
    };

    addLatLngFromCoordinates(coordinates, mapInfo);
}

/**
 * Отрисовывает точку на карте, добавляет клиентскую модель точки в mapInfo
 * @param coordinates объект координат в формате {lat: 11, lng: 12}
 * @param mapInfo объект-данные о карте
 */
function addLatLngFromCoordinates(coordinates, mapInfo) {
    var path = mapInfo.polyline.getPath();
    // Because path is an MVCArray, we can simply append a new coordinate
    // and it will automatically appear.
    path.push({
        lat: function () {
            return coordinates.lat;
        },
        lng: function () {
            return coordinates.lng;
        }

    });

    // Add a new marker at the new plotted point on the polyline.
    var marker = new google.maps.Marker({
        position: coordinates,
        title: '#' + path.getLength(),
        map: mapInfo.map
    });
    mapInfo.routeModel.points.push(new RoutePointModel(coordinates, "", "", ""))
}

/**
 * Итерирутеся через массив mapInfo.routeModel.points: для каждой точки отрисовывает её на карте,
 * хранящейся в mapInfo.map
 * @param mapInfo
 */
function iterateOverRouteModelPointsAndAddMapMarkers(mapInfo) {
}


function saveCurrentRoute() {
    saveRoute(mapInfo);
}

function saveRoute(mapInfo) {
    $.ajax({
        type: "PUT",
        url: "/routes",
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
