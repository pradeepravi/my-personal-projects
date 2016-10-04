<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pradeep Skill | Spring MVC | List all Staffs</title>
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="./resources/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="./resources/styles/bootstrap-social.css" />
<link rel="stylesheet" href="./resources/styles/mystyles.css" />
</head>


<body>
	<div class="container">
		<div class="row row-content">

			<div class="col-xs-12">
				<div class="media-left media-middle">
					<img src="./resources/images/avatar_male.png" class="media-object
						img-thumbnail" alt="Uthappizza">
				</div>
				<div class="media-body">
					<h4 class="media-heading">List of employees</h4>
				</div>

			</div>
			<div class="col-xs-9 col-xs-offset-1">

				<ul class="media-list tab-pane fade in active">
					<c:forEach items="${staffs}" var="staff">

						<li class="media">
							<blockquote>
								<div class="media-body">
									<span>${staff.firstName}</span>
								</div>
								<div>
									<span>${staff.dob}</span> <span class="badge">${staff.sex}</span>
									<footer>${staff.userType}</footer>
								</div>
							</blockquote>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

	</div>

</body>
</html>