<#--

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html content="text/html;charset=UTF-8">
<body>

<h1>FREEMARKER LOGIN</h1>

<p>My first paragraph.</p>


<form action="/j_spring_security_check" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="admin">
    <input type="password" class="form-control" name="j_password" placeholder="Password" required value="admin">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
</form>

</body>
</html>-->



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Trucks &rsaquo; Log In</title>
    <#--<link rel="stylesheet" href="login.css" >-->
    <link rel="stylesheet" href="/static/assets/css/login.css" >
</head>
<body>
<div id = "login">
    <h1 id = "logo"><a href = "http://www.tracks.com">TracksLogo</a></h1>
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
    <div id="lostPassword" >
        <a href="http://www.templategarden.com/wp-login.php?action=lostpassword" title="Password Lost and Found" class = "loginFormTextBright">Lost your password?</a>
    </div>
    <div id="backtoblog">
        <a href="http://www.templategarden.com/" title="Are you lost?" class = "loginFormTextBright">&larr; Back to TemplateGarden</a>
    </div>
</div>
</body>
</html>