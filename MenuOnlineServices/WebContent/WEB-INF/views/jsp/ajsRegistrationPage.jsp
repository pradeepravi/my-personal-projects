<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Angular JS Show Skill | Registration Page</title>

	<!-- Bootstrap core CSS -->
	<link href="./resources/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="./resources/styles/signin.css" rel="stylesheet">
	<script type="text/javascript" src="./resources/bootstrap/dist/js/jquery.js"></script>
	<script type="text/javascript" src="./resources/bootstrap/dist/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="./resources/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./resources/bootstrap/dist/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$('#datepicker1').datepicker();
		});
	</script>
</head>

<body>
	<div class="container">
		<form class="form-registration"  action="./ajsRegisterNew" method="post"> 
		
			<h3 class="form-registration-heading">Please register with the following details</h3>
			<div class="form-group">

				<label class="col-md-2 control-label" for="fn">First name</label>
				<div class="col-md-10">
					<input id="fn" name="firstName" type="text" placeholder="first name"
						class="form-control input-md" required="">
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-2 control-label" for="ln">Last name</label>
				<div class="col-md-10">
					<input id="ln" name="lastName" type="text" placeholder="last name"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-2 control-label" for="mn">Middle Name</label>
				<div class="col-md-10">
					<input id="mn" name="middleName" type="text" placeholder="Middle Name"
						class="form-control input-md" required="">
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-2 control-label" for="email">Email</label>
				<div class="col-md-10">
					<input id="email" name="email" type="text" placeholder="email"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-2 control-label" for="dob">Date Of Birth</label>
				<div class='col-md-10 input-group date' id='datepicker1'>
                    <input type='text' name="dob" id="dob"  class="form-control" />
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-2 control-label" for="mobile">Mobile Number</label>
				<div class="col-md-10">
					<input id="mobile" name="mobile" type="text" placeholder="Mobile Number"
						class="form-control input-md">
				</div>
			</div>

			<!-- Radio -->
			<div class="form-group">
				<label class="col-md-2 control-label" for="sex">Sex</label>
				<div class="col-md-10">
					<label class="radio-inline" for="sex-0"> <input
						type="radio" name="sex" id="sex-0" value="m" checked="checked">
						Male
					</label> <label class="radio-inline" for="sex-1"> <input
						type="radio" name="sex" id="sex-1" value="f"> Female
					</label>
				</div>
			</div>
			
			
			<!-- Radio -->
			<div class="form-group">
				<label class="col-md-2 control-label" for="Training">User Type</label>
				<div class="col-md-10">
					<label class="radio-inline" for="userType-0"> 
						<input type="radio" name="userType" id="userType-0" value="normalUser" checked="checked">Normal
					</label> 
					<label class="radio-inline" for="userType-1"> 
						<input type="radio" name="userType" id="userType-1" value="staffUser">Staff
					</label>
				</div>
			</div>
			
			
			<div class="form-group">
				<div class="col-md-12">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
				</div>
			</div>
			
		</form>
	</div>


</body>
</html>
