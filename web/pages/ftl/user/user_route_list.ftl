<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <script src="/static/assets/js/jquery/jquery-1.12.3.js"></script>
    <script src="/static/assets/js/app/map-manager.js"></script>

    <link href="/static/assets/css/user_route_list.css" type="text/css">
    <script src="/static/assets/js/pages/user_route_list.js"></script>
    <title></title>

    <script >
        $( document ).ready(function() {
            drawUsersRoutes($("#userRouteContainer"));
        })
    </script>
</head>
<body>
<nav>
<#include "/templates/ftl/UserNavigationBar.ftl">
</nav>

HELLO! THIS IS USER ROUTE LIST!

<div class="container">
    <div id="userRouteContainer" class="row">
        <#--<ul class="user_route_list_ul">
            <li>

            </li>
        </ul>-->
    </div>
</div>

HELLO! THIS IS USER ROUTE LIST!
</body>
</html>