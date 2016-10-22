<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <script src="/static/assets/js/jquery/jquery-1.12.3.js"></script>
    <script src="/static/assets/js/app/map-manager.js"></script>

    <link href="/static/assets/css/user_route_list.css" type="text/css">
    <script src="/static/assets/js/pages/user_route_list.js"></script>
    <title></title>
    <style>
        .user_route_map {
            position: relative;
            overflow: hidden;
            height: 400px;
            width: 500px;
            /*display: inline-block;*/
        }
        .user_route_container {
            display: inline-block;
        }
        .user_route_view_container {
            height: 400px;
            width: 600px;
            display: inline-block;
        }

    </style>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOSXCtPQOP1QJxTZnsJnmUZjQSkGWztIo&callback=initMap"
            async defer></script>

    <script>
        $(document).ready(function () {
            drawUsersRouteList($("#userRouteContainer"));

        });
        function initMap() {
//            initUserMapByElemId("mapForRoute");
            initUserMapOnUserListPage();
//            drawUsersRoutes($("#userRouteContainer"));
        }
    </script>
</head>
<body>
<nav>
<#include "/templates/ftl/UserNavigationBar.ftl">
</nav>

HELLO! THIS IS USER ROUTE LIST!

<div class="container">
    <div class="row ">
        <div id="userRouteContainer" class="user_route_container">

        </div>
    <#--<ul class="user_route_list_ul">
            <li>

            </li>
        </ul>-->
        <div id="userMapId" class="user_route_view_container">
            <div id="mapForRoute" class="user_route_map" <#--hidden="true"-->></div>
        </div>
    </div>
</div>

HELLO! THIS IS USER ROUTE LIST!
</body>
</html>