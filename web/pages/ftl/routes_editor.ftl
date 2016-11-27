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
<div class="modal fade" id="myModal">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>
        <h3>Введите логин и пароль</h3>
    </div>
    <div class="modal-body">
        <form name="loginform" id="loginform" action="/j_spring_security_check" method="post">
            <div class = "">
                <label for="user_login" class = "loginFormTextLight">Username:<br />
                    <input type="text" name="j_username" id="user_login" class="loginTextField" value="" size="20" /></label>
            </div>
            <div>
                <label for="user_pass" class = "loginFormTextLight">My Password:<br />
                    <input type="password" name="j_password" id="user_pass" class="loginTextField" value="" size="20" /></label>
            </div>
            <div id = "rememberMe"  class = "loginFormTextLight">
                <label for = "rememberMeField"><input id  = "rememberMeField" name="rememberField" type="checkbox"  value="forever" />Remember Me</label>
            </div>
            <div class="submit" class = "loginFormTextLight">
                <input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="Log In" />
                <input type="hidden" name="redirect_to" value="http://www.templategarden.com/" />
                <input type="hidden" name="testcookie" value="1" />
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div id="lostPassword" >
            <a href="http://www.templategarden.com/wp-login.php?action=lostpassword" title="Password Lost and Found" class = "loginFormTextBright">Забыли пароль?</a>
        </div>
        <div id="backtoblog">
            <a href="http://www.templategarden.com/" title="Are you lost?" class = "loginFormTextBright">&larr; Регистрация</a>
        </div>
    </div>
</div>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://bootstrap-3.ru/examples/jumbotron/#">TrucksHuyaks</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="main_page">Маршруты</a></li>
                <li ><a href="stat_table">Прочее</a></li>
                <li><a href="http://bootstrap-3.ru/examples/starter-template/#about">О проекте</a></li>
            </ul>

            <ul  class="nav navbar-nav navbar-right"  >

                <li ><a style = "height:40px; margin:5px;line-height:8px;color:white" id="signup-header-btn" class="btn btn-success signupbtn" href="https://snappa.io/#" data-toggle="modal" data-target="#loginModal" data-action="signup-form">Войти</a></li>

            </ul>
            <div class = "navbar-text navbar-right"></div>
            <div class = "navbar-text navbar-right"></div>

            <div class = "navbar-text navbar-right" ><a href="#myModal"  data-toggle="modal">RU</a></div>


        </div>
    </div>
</div>






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