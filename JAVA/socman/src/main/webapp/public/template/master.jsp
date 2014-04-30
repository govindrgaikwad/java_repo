<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="pt-BR" id="ng-app" ng-app="socmanApp">
<head>
<script type="text/javascript"
	src="<c:url value='/public/resources/jquery/jquery-1.11.0.js' />"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/public/resources/bootstrap/css/bootstrap.min.css' />"
	media="screen">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/public/resources/bootstrap/css/bootstrap-theme.min.css' />"
	media="screen">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/public/resources/css/login.css' />" media="screen">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/public/resources/css/society.css' />"
	media="screen">

<script type="text/javascript"
	src="<c:url value='/public/resources/bootstrap/js/bootstrap.min.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/public/resources/angular/angular.min.js' />"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="container">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" />
		</div>
	</div>

</body>
</html>