/**
 * Needs map-manager js script to be present.
 */

//region definitions for user_route_list page

var page_data = {
    userRoutes: null,
    mapInfo: null
};

/**
 * Script for initiating on the old route list page
 * @param routeListContainerElement
 * @deprecated
 */
function drawUsersRouteList(routeListContainerElement) {
    var userRouteList = new UserRouteList(routeListContainerElement);
    userRouteList.onDrawRouteClickEvent = drawRouteOnPage;
    $.ajax({
        url: "/routes/user",
        success: function (data) {
            var clientModelRoutes = convertServerRouteModelList2UserRoutes(data);
            userRouteList.drawRouteList(clientModelRoutes);
            page_data.userRoutes = clientModelRoutes;
        }
    });
}


function drawUsersRouteListNew(routeListContainerElement) {
    var userRouteList = new UserRouteList(routeListContainerElement);
    userRouteList.onDrawRouteClickEvent = drawRouteOnPage;
    userRouteList.routeListDomElemDrawer = drawRouteListDomElem;
    userRouteList.routeDomElemDrawer = drawRouteDomElem;
    $.ajax({
        url: "/routes/user",
        success: function (data) {
            var clientModelRoutes = convertServerRouteModelList2UserRoutes(data);
            userRouteList.drawRouteList(clientModelRoutes);
            page_data.userRoutes = clientModelRoutes;
        }
    });
}
/**
 *
 * @param routeModel {RouteModel}
 * @return {*|jQuery|HTMLElement}
 */
function drawRouteDomElem(routeModel) {

    var listItemElem = $("<div></div>");

    listItemElem.addClass("routes_item");

    listItemElem.text(routeModel.name);

    this.listDomElem.append(listItemElem);
    return listItemElem;
}

function drawRouteListDomElem(listOfRouteModel) {
    return $("#userRouteListContainer");
}

/**
 * @param routeModel {RouteModel}
 */
function drawRouteOnPage(routeModel) {
    //var mapJDom = getMapDomElement();
    //if (!mapJDom.getAttribute("hidden")) {
    //    mapJDom.setAttribute("hidden", "false");
    //}
    page_data.mapInfo.drawRoute(routeModel);

    $("#routeNameInput").val(routeModel.name);
}

function initMapAndLoadRoute(routeId) {
    initUserMapOnUserListPage();

    $.ajax({
        url: "/routes/" + routeId,
        success: function (data) {
            drawRouteOnPage(convertFromServerRouteModel(data));
            //todo init route points elements
            //todo handle errors
        }
    });
}

function saveRouteFromPage() {
    saveRoute(page_data.mapInfo);

    /*$.ajax({
     url: "/routes/" + routeId,
     success: function (data) {
     drawRouteOnPage(convertFromServerRouteModel(data));
     //todo init route points elements
     //todo handle errors
     }
     });*/
}


/**
 *
 * @param point {RoutePointModel}
 * @param marker {google.maps.Marker}
 * @constructor
 */
function CurrSelectedPointData(point, marker) {
    this.point = point;  // instanceof {RoutePointModel}
    this.marker = marker; // instanceof google.maps.Marker

    this.removePoint = function () {
        if (this.point) {
            this.point = null;
        }

        if (this.marker) {
            this.marker.setMap(null);
        }
    }
};

var currSelectedPointData = null; //instanceof CurrSelectedPointData
var cursorPointDelta = 0.01;

function addMarkerToNearestPoint(event, mapInfo) {
    var eventLat = event.latLng.lat(); // [-90, 90]
    var eventLng = event.latLng.lng(); // [-180, 180]
    var eventLatLng = {lat: eventLat, lng: eventLng};

    var nearestPointDataList = []; // array of objects {point, distance}
    var pointArray = mapInfo.routeModel.points;
    for (var pointIndex = 0; pointIndex < pointArray.length; pointIndex++) {

        var point = pointArray[pointIndex];
        if (CoordinateUtils.isLatAndLngInDeltaArea(point.latlng, eventLatLng, cursorPointDelta)) {
            nearestPointDataList.push({
                point: point,
                distance: CoordinateUtils.flatDistanceBetweenLatLng(point.latlng, eventLatLng)
            });
        }
    }


    if (nearestPointDataList.length > 0) {
        //find nearest point
        var nearestPointData = null;
        nearestPointDataList.forEach(function (item, i, arr) {
            if (nearestPointData == null) {
                nearestPointData = item;
            } else if (item.distance < nearestPointData.distance) {
                nearestPointData = item;
             }
        });

        if (nearestPointData) {

            var nearestPointMarker = new google.maps.Marker({
                position: nearestPointData.point.latlng,
                label: 'D',
                map: mapInfo.map
                //,icon: "/static/assets/images/icons/RoutePointMouseoverSelection.jpg"
            });

            nearestPointMarker.addListener('click', function() {
                //map.setZoom(8);//todo
                //map.setCenter(marker.getPosition());
            });

            currSelectedPointData.removePoint();
            currSelectedPointData = null;

            currSelectedPointData = new CurrSelectedPointData(nearestPointData.point, nearestPointMarker);
        } else {
            currSelectedPointData.removePoint();
            currSelectedPointData = null;
        }
    } else {
        currSelectedPointData.removePoint();
        currSelectedPointData = null;
    }
}

var currentEditPoint = null;
function showEditRoutePointWindow(point) {
    $("#pointNameInput").val(point.name);
    $("#pointDescrTextArea").val(point.description);
    $("#editRouteRowModal").show();
}

function showEditRoutePointWindow() {
    $("#editRouteRowModal").hide();
}

function saveRoutePointWindow() {
    $("#editRouteRowModal");
    currentEditPoint.name = $("#pointNameInput").val();
    currentEditPoint.description = $("#pointDescrTextArea").val();
}

function initUserMapOnUserListPage() {
    var mapDomElement = getMapDomElement();

    prepareMapContaner();

    var mapInfo = new MapInfo({mapDomElement: mapDomElement});
    /*mapInfo.doCreateMarker = function (coordinates, path, point) {
     var marker = MapInfo.prototype.doCreateMarker(this, coordinates, path, point);
     marker.addListener('click', function() {
     var i = 0;
     //map.setZoom(8);
     //map.setCenter(marker.getPosition());
     });

     };*/
    mapInfo.createAndAddRoutePolyline = function (routeModel) {
        var poly = new google.maps.Polyline({
            strokeColor: '#000000',
            strokeOpacity: 1.0,
            strokeWeight: 3
        });
        poly.setMap(this.map);
        this.polyline = poly;


        /**
         * Param event contains a pair [lat, lng]
         */
        google.maps.event.addListener(poly, 'mouseover', function (event) {
            addMarkerToNearestPoint(event, mapInfo);
        });


        /**
         * Param event contains a pair [lat, lng]
         */
        google.maps.event.addListener(poly, 'mouseout', function (event) {
            if (currSelectedPointData ) {
                var eventLatLng = {lat : event.latLng.lat() , lng: event.latLng.lng()};

                if (!CoordinateUtils.isLatAndLngInDeltaArea(
                        currSelectedPointData.point.latlng, eventLatLng, cursorPointDelta)) {
                    currSelectedPointData.removePoint();
                    currSelectedPointData = null;
                }
            }
        });
    };
    page_data.mapInfo = mapInfo
}



function prepareMapContaner() {
    var mapContainerWrapper = $("#route_name_label_wrapper");
    $("#" + getMapElementIdentifier()).width(mapContainerWrapper.width());
    $("#" + getMapElementIdentifier()).height(mapContainerWrapper.height());
}

function getMapDomElement() {
    return document.getElementById(getMapElementIdentifier());
}

function getMapElementIdentifier() {
    return "mapForRoute";
}

function switchEnableAddPointMode(isEnabled) {
    if (isEnabled) {
        //enableAddPointModeToMapInfo(page_data.mapInfo);
        page_data.mapInfo.enableAddPointMode();
    } else {
        //disableAddPointModeToMapInfo(page_data.mapInfo);
        page_data.mapInfo.disableAddPointMode();
    }

}

function updateRouteName(routeName) {
    page_data.mapInfo.routeModel.name = routeName;
}
//endregion


