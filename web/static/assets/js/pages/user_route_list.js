/**
 * Needs map manager script to be present.
 */


//region common object for working with list of user's routes
/**
 *
 * @param containerElem a dom elem, which will contain route list
 * @constructor
 */
function UserRouteList(containerElem) {
    this.routes = []; // array of RouteModel from map manager
    this.containerElem = containerElem;
    this.listDomElem = null;

    //region route drawing functions
    /**
     * Adds DOM element to the list and appropriate . Element corresponds the RouteModel instance
     * @param routeModel instance of client RouteModel object.
     */
    this.routeDomElemDrawer = function (routeModel) {
        var listItemElem = $("<li></li>");

        listItemElem.text(routeModel.name);

        this.listDomElem.append(listItemElem);
        return listItemElem;
    };

    /**
     * @param listOfRouteModel (maybe redundant parameter) object with type {UserRoutes}
     * @returns jDOM element appropriate to the user route list.
     */
    this.routeListDomElemDrawer = function (listOfRouteModel) {
        this.listDomElem = $("<ul></ul>");
        this.containerElem.append(this.listDomElem);
        return this.listDomElem;
    };
    //endregion

    //region object init functions (this functions have to return this)

    /**
     * @param routeDomElemDrawer function which takes one argument with client RouteModel type
     */
    this.withRouteDomElemDrawer = function (routeDomElemDrawer) {
        this.routeDomElemDrawer = routeDomElemDrawer;
        return this;
    };

    this.withRouteListDomElemDrawer = function (routeListDomElemDrawer) {
        this.routeListDomElemDrawer = routeListDomElemDrawer;
        return this;
    };
    //endregion

    /**
     * Draws list of routeModels
     * @param userRoutes {UserRoutes} type object
     */
    this.drawRouteList = function (userRoutes) {
        this.routeListDomElemDrawer(userRoutes);

        var userRouteList = userRoutes.routes;
        for (var i = 0; i < userRouteList.length; i++) {
            this.drawConcreteRoute(userRouteList[i]);
        }
    };

    /**
     * Draws list of routeModels
     * @param routeModel {RouteModel} type object
     */
    this.drawConcreteRoute = function (routeModel) {
        var listItemElem = this.routeDomElemDrawer(routeModel);

        var drawRouteEventFunction = this.onDrawRouteClickEvent;
        listItemElem.click(function () {
            drawRouteEventFunction(routeModel);
        });
    };

    /**
     * Action on event when concrete route have been selected.
     * @param routeModel {RouteModel}
     */
    this.onDrawRouteClickEvent = function (routeModel) {
        //to override!
    };
}
//endregion

//region definitions for user_route_list page

var page_data = {
    userRoutes: null,
    mapInfo: null
};

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

function initUserMapOnUserListPage() {
    page_data.mapInfo = new MapInfo({mapDomElement: getMapDomElement()})
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

function getMapDomElement() {
    return document.getElementById("mapForRoute");
}
//endregion


