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
            width: 100%;
            height: 500px;
        }

    </style>
</head>
<body>

<h1>SOME user PAGE asd112</h1>

<div class="container">
    <div class="row">
    <#--<iframe>-->
        <div>
            <div style="width:80%; display: inline-block">
                <div id="map" class="app_map">

                </div>
            </div>
            <div style="width:19%; display: inline-block; position: fixed">
                <ul id="routeList" class="list-group">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
                <button type="button" class="btn btn-default" >загрузить маршруты</button>

                <input id="routeNumberInput" type="number">
                <button type="button" class="btn btn-default" onclick="clearMapAndDrawRoute();">Отрисовать маршрут</button>
            </div>
        </div>
    <#--<div style="width:20% display: block">-->
    <#--<button type="button" class="btn btn-default" >list</button>-->
    <#--</div>-->
    <#--</iframe>-->
    </div>

    <div class="row">
    <#--<iframe height="400px" width="100%" frameborder="0" scrolling="no" src="/map/simpleMap">-->
    <#--</iframe>-->
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default" onclick="enableAddPointMode();">Создать маршрут</button>
            <button type="button" class="btn btn-default">Удалить точку</button>
            <button type="button" class="btn btn-default" onclick="saveCurrentRoute();">Сохранить маршрут</button>
        </div>
    </div>
    <div class="row">
        <label for="shortPointDescription"></label>
        <input id="shortPointDescription" type="text"/>
    </div>
    <div class="row">
        <label for="pointDescription"></label>
        <input id="pointDescription" type="text"/>
    </div>

</div>
</body>
</html>