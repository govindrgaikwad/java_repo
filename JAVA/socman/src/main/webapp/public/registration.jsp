<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" id="login-block">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">

			<div class="login-box clearfix animated flipInY">
				<div class="page-icon animated bounceInDown">
					<img class="img-responsive"
						src="<c:url value='/public/resources/images/login-key-icon.png' />"
						alt="Key icon" />
				</div>
				<div class="login-logo">
					<a href="#"><img
						src="<c:url value='/public/resources/images/login-logo.png' />"
						alt="Company Logo" /></a>
				</div>
				<hr />
				<div class="login-form">
					<div class="alert alert-error hide">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Error!</h4>
						Your Error Message goes here
					</div>
					<form action="#" method="post">
						<input type="text" placeholder="User name" class="input-field"
							required /> <input type="password" placeholder="Password"
							class="input-field" required /> <input type="email"
							placeholder="Email" class="input-field" required />
						<button type="submit" class="btn btn-login">Register</button>
					</form>

				</div>
			</div>


		</div>
	</div>
</div>


