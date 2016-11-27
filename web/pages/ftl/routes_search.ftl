<!DOCTYPE html>
<!-- saved from url=(0040)http://bootstrap-3.ru/examples/carousel/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://bootstrap-3.ru/assets/ico/favicon.ico">

    <title>Carousel Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/assets/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/assets/css/wellcome.css" rel="stylesheet">
    <link href="/static/assets/css/routes_editor.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="./css/carousel.css" rel="stylesheet">
    <style id="holderjs-style" type="text/css"></style></head>
<!-- NAVBAR
================================================== -->

<#include "/templates/ftl/LoginUserModal.ftl">
<body>

    <#include "/templates/ftl/UserNavigationBar.ftl">






<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container" id  = "main_container">

    <!-- Three columns of text below the carousel -->
    <div class="row" id = "row1">
        здесь будут дохуя вских рахных фильтров
    </div><!-- /.row -->
    <div class="row" id = "row2">
        <div class  = "col-lg-9 col-md-8" id = "route_name_label_wrapper">
            сюда вставлять карту
        </div>
        <div class  = "col-lg-3 col-md-4" id = "route_desc_edit_wrapper">
            <div id = "wrapper">
                <div  id = "search_results_head">
                    <span style = "float:left">Результаты поиска</span>
                    <span style = "float:right">1-10</span>
                </div>
                <div  id = "search_results_head">
                    <span style = "float:left">Добавить маршрут</span>
                    <span style = "float:right">1-10</span>
                </div>
                <div class = "routes_item">
                    маршрут 1
                </div>
                <div class = "routes_item">
                    маршрут 2
                </div>
                <div class = "routes_item">
                    маршрут 3
                </div>
                <div class = "routes_item">
                    маршрут 4
                </div>
                <div class = "routes_item">
                    маршрут 6
                </div>
                <div class = "routes_item">
                    маршрут 7
                </div>
                <div class = "routes_item">
                    маршрут 8
                </div>
                <div class = "routes_item">
                    маршрут 9
                </div>

                <div class = "routes_item" id = "search_results_pages">
                    <ul class="pagination">
                        <li class="disabled"><a href="#">&laquo;</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li ><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>

                </div>
            </div>

        </div>


    </div><!-- /.row -->


    <!-- /END THE FEATURETTES -->





</div><!-- /.container -->
<!-- FOOTER -->
<footer id = "footer">
    <div class = "container">
        <div class = "row">
            <hr>
            <p class="pull-right"><a href="http://bootstrap-3.ru/examples/carousel/#">Back to top</a></p>
            <p>© 2014 Company, Inc. · <a href="http://bootstrap-3.ru/examples/carousel/#">Privacy</a> · <a href="http://bootstrap-3.ru/examples/carousel/#">Terms</a></p>
        </div>
    </div>
</footer>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/static/assets/js/jquery/jquery-1.12.3.js"></script>
<script src="/static/assets/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<script src="/static/assets/js/doc/docs.min.js"></script>

</body></html>