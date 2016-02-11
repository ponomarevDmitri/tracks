<!DOCTYPE html>
<html>
<body>

<h1>FREEMARKER LOGIN</h1>

<p>My first paragraph.</p>

<p>authentified!!</p>

<form action="/j_spring_security_check" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="admin">
    <input type="password" class="form-control" name="j_password" placeholder="Password" required value="admin">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
</form>

</body>
</html>