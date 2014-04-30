<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" id="login-block">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">

			<div class="login-box clearfix animated flipInY">
				<div class="page-icon animated bounceInDown">
					<img class="img-responsive"
						src="<c:url value='/public/resources/images/login-key-icon.png' />" alt="Key icon" />
				</div>
				<div class="login-logo">
					<a href="#"><img src="<c:url value='/public/resources/images/login-logo.png' />"
						alt="Company Logo" /></a>
				</div>
				<hr />
				<div class="login-form">
					<div class="alert alert-error hide">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Error!</h4>
						Your Error Message goes here
					</div>
					<form action="<c:url value='/j_spring_security_check' />" method="post">
						<input name="j_username" id="j_username" type="text"  placeholder="User name" class="input-field"
							required /> 
						<input name="j_password" id="j_password" type="password" placeholder="Password"
							class="input-field" required />
						<button type="submit" class="btn btn-login">Login</button>
					</form>
					<div class="login-links">
						<a href="forgot-password.html"> Forgot password? </a> <br /> <a
							href="sign-up.html"> Don't have an account? <strong>Sign
								Up</strong>
						</a>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>


