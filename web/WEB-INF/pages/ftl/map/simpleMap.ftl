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
    <style>

        .app_map {
            width: 500px;
            height: 500px;
        }

    </style>
</head>
<body>

<h1>SOME user PAGE asd112</h1>

<div class="container">
    <div class="row">
    <#--<iframe>-->
        <div id="map" class="app_map">

        </div>
    <#--</iframe>-->
    </div>
</div>
</body>
</html>