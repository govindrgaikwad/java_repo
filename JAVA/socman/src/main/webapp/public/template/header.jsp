<!-- Menu Navigation -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a id="society" class="navbar-brand"
				href="<c:url value='/public/home'/>">Socman</a>
		</div>
		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/public/home'/>">Home</a></li>
				<li><a href="<c:url value='/public/aboutus'/>">About Us</a></li>
				<li><a href="<c:url value='/public/newsevents'/>">News &
						Events</a></li>
				<li><a href="<c:url value='/public/team'/>">Our Team</a></li>
				<li><a href="<c:url value='/public/contactus'/>">Contact Us</a></li>
			</ul>
			<div class="nav navbar-nav navbar-right">

				<a class="btn btn-danger navbar-btn"
					style="margin-left: 10px;" href="<c:url value='/public/login'/>">
						Login</a>
				<a class="btn btn-success navbar-btn"
					href="<c:url value='/public/register' />">Register</a>

			</div>
		</nav>
	</div>
</div>

