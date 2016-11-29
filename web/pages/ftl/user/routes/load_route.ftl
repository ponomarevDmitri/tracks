<!DOCTYPE html>
<html>
<head>
    <script src="/static/assets/js/jquery/jquery-1.12.3.js"></script>
    <script src="/static/assets/js/app/map-manager.js"></script>

    <style type="text/css"></style>

<#--region custom css files-->
    <link href="/static/assets/css/nav_style.css" rel="stylesheet" type="text/css">
<#--endregion-->

<#--region bootstrap css files-->
    <link href="/static/assets/bootstrap-3.3.6/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/static/assets/bootstrap-3.3.6/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<#--endregion-->

<#--region custom js files-->
    <script src="/static/assets/js/pages/user/routes/load_route.js"></script>
<#--endregion -->

    <script>
        function initMap() {
            initUserMapByElemId("map");
        }
    </script>

<#--todo make getting google script from the server (for security reasons)-->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOSXCtPQOP1QJxTZnsJnmUZjQSkGWztIo&callback=initMap"
            async defer></script>
<#--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOSXCtPQOP1QJxTZnsJnmUZjQSkGWztIo"-->
<#--></script>-->
    <style>

        .app_map {
            width: 100%;
            height: 500px;
        }

    </style>
</head>
<body>
<#include "/templates/ftl/UserNavigationBar.ftl">

<h1>SOME user PAGE asd112</h1>

<div class="container">
    <div class="row">
        <div>
            <form action="/user/route/upload" method="post" enctype="multipart/form-data" id="image_upload" name="image_upload">
                <label class="btn btn-default btn-file">
                    Browse <input type="file" style="display: none;" name="routeData">
                </label>
                <input type="submit">
            </form>
        </div>
    </div>
    <footer>
    <#include "/templates/ftl/UserFooter.ftl">
    </footer>
</div>
</body>
</html>