<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Angular JS Show Skill | Login Page</title>

<!-- Bootstrap core CSS -->
<link href="./resources/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="./resources/styles/signin.css" rel="stylesheet">

</head>

<body>

	<div class="container">

		<form class="form-signin" action="./ajsSignIn" method="post"> 
			<h2 class="form-signin-heading">Please Enter Email ID</h2>
			<label for="userEmail" class="sr-only">Email address</label> <input
				type="email" id="userEmail" name="userEmail" class="form-control"
				placeholder="Email address" required autofocus>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<!-- 
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
         -->
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
		</form>

	</div>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
