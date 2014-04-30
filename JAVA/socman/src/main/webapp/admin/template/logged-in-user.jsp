<!-- User Account: style can be found in dropdown.less -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<li class="dropdown user user-menu"><a href="#"
	class="dropdown-toggle" data-toggle="dropdown"> <i
		class="glyphicon glyphicon-user"></i> <span>${member.firstName}
			<i class="caret"></i>
	</span>
</a>
	<ul class="dropdown-menu">
		<!-- User image -->
		<li class="user-header bg-light-blue"><img
			src="<c:url value='/admin/img/avatar3.png' />" class="img-circle"
			alt="User Image" />
			<p>
				${member.firstName} - ${member.roles[0].roleName} <small>Member
					Since ${member.createdDt.getDate()}  ${member.createdDt.getMonth()}  ${member.createdDt.getYear()}</small>
			</p></li>
		<!-- Menu Body -->
		<li class="user-body">
			<div class="col-xs-4 text-center">
				<a href="#">Followers</a>
			</div>
			<div class="col-xs-4 text-center">
				<a href="#">Sales</a>
			</div>
			<div class="col-xs-4 text-center">
				<a href="#">Friends</a>
			</div>
		</li>
		<!-- Menu Footer-->
		<li class="user-footer">
			<div class="pull-left">
				<a href="#" class="btn btn-default btn-flat">Profile</a>
			</div>
			<div class="pull-right">
				<a href="#" class="btn btn-default btn-flat">Sign out</a>
			</div>
		</li>
	</ul></li>