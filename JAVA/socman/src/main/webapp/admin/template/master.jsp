<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html ng-app="socmanApp">
<head>
<meta charset="UTF-8">
<title>SocMan | Dashboard</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>

<link href="<c:url value='/admin/css/bootstrap.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/admin/css/font-awesome.min.css'  />"
	rel="stylesheet" />
<link href="<c:url value='/admin/css/ionicons.min.css'  />"
	rel="stylesheet" />
<link href="<c:url value='/admin/css/AdminLTE.css'  />" rel="stylesheet" />
<script src="<c:url value='/public/resources/angular/angular.min.js' />"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
          
        <![endif]-->
</head>
<body class="skin-black">
	<!-- header logo: style can be found in header.less -->
	<header class="header">
		<a href="<c:url value='/protected/admin'/>" class="logo"> SocMan </a>
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation">
			<!-- Sidebar toggle button-->
			<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
			<div class="navbar-right" ng-controller="MemberHeaderController">
				<ul class="nav navbar-nav">
					<!-- Messages: style can be found in dropdown.less-->
					<tiles:insertAttribute name="messages" />
					<!-- Notifications: style can be found in dropdown.less -->
					<tiles:insertAttribute name="notifications" />
					<!-- Tasks: style can be found in dropdown.less -->
					<tiles:insertAttribute name="tasks" />
					<!-- User Account: style can be found in dropdown.less -->
					<tiles:insertAttribute name="loggedInUser" />
				</ul>
			</div>
		</nav>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="left-side sidebar-offcanvas">
			<tiles:insertAttribute name="sidebar" />
		</aside>

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<tiles:insertAttribute name="body" />
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->


	<!-- jQuery 2.0.2 -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="<c:url value='/admin/js/socman/MemberController.js' />"></script>
	<script src="<c:url value='/admin/js/socman/SocietyController.js' />"></script>
	<script src="<c:url value='/admin/js/socman/PropertyController.js' />"></script>
	<!-- Bootstrap -->
	<script src="<c:url value='/admin/js/bootstrap.min.js' />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value='/admin/js/AdminLTE/app.js' />"></script>



</body>
</html>