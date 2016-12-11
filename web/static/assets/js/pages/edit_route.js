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

function saveRoute() {
    page_data.mapInfo
    $.ajax({
        url: "/routes/" + routeId,
        success: function (data) {
            drawRouteOnPage(convertFromServerRouteModel(data));
            //todo init route points elements
            //todo handle errors
        }
    });
}

function initUserMapOnUserListPage() {
    var mapDomElement = getMapDomElement();

    prepareMapContaner();

    page_data.mapInfo = new MapInfo({mapDomElement: mapDomElement})
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


//endregion


