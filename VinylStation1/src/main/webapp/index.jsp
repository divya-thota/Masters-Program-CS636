<!DOCTYPE html>
<html>
<head>
<title>Vinyl Station</title>
	<link rel="stylesheet" type="text/css" href="assets/index.css">
</head>
<body>
	<div class="slider">
		<div class="loginbox">
			<img src="assets/images/logo.jpg" class="logo">
			<h1>Login Here</h1>
			<form name="loginform" action="./loginServlet" method="post">
			<span id="errormessage">${error}</span>
			<span id="registrationmessage">${registration}</span>
				<p>Username</p>
				<input type="text" name="username" placeholder="Enter Username/Email">
				<p>Password</p>
				<input type="password" name="password" placeholder="Enter Password">
				<input type="submit" name="" value=Login>
				<!-- <a href="#">Lost Your password?</a><br> -->
				<a href="create_customer.jsp">Don't have an account?</a>
			</form>
		</div>
	</div>
</body>
</html>