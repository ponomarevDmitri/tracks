<!DOCTYPE html>
<html>
<head>
    <script src="/static/assets/js/jquery/jquery-1.12.3.js"></script>
    <script src="/static/assets/js/app/map-manager.js"></script>

    <style type="text/css"></style>

    <link href="/static/assets/bootstrap-3.3.6/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/static/assets/bootstrap-3.3.6/css/bootstrap-theme.css" rel="stylesheet" type="text/css">



    <script>
        function initMap() {
            initUserMapByElemId("map");
        }
    </script>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOSXCtPQOP1QJxTZnsJnmUZjQSkGWztIo&callback=initMap"
            async defer></script>
<#--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOSXCtPQOP1QJxTZnsJnmUZjQSkGWztIo"-->
<#--></script>-->
    <style>

        .app_map {
            width: 500px ;
            height: 500px;
        }

    </style>
</head>
<body>

<h1>SOME user PAGE asd112</h1>

<div class="container">
    <div class="row">
        <div id="map" class="app_map">

        </div>
    </div>

    <div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default" onclick="enableAddPointMode();">Добавить точку</button>
            <button type="button" class="btn btn-default">Удалить точку</button>
            <button type="button" class="btn btn-default">Right</button>
        </div>
    </div>

</div>
</body>
</html>