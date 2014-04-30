<section class="content-header">
	<h1>
		Society Info <small> At a glance</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Society Info</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">General Information</h3>
					<div class="pull-right box-tools">
						<button class="btn btn-info btn-sm" title="Edit"
							data-toggle="modal" data-target="#society-modal">
							<i class="fa fa-edit"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<strong>Adriana Co. Op. Housing Society</strong> <br />
					<address>
						S. No. 88/2,<br /> Veerbhadra Nagar, Baner<br /> Pune - 411045 <br />
						Phone: +91-20-2222-4444<br /> Email: adriana@adriana.com
					</address>
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>

			</div>
			<!-- /.box -->
		</div>
		<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Office Bearers</h3>
					<div class="pull-right box-tools">
						<button class="btn btn-info btn-sm" title="Edit"
							data-toggle="modal" data-target="#office-modal">
							<i class="fa fa-edit"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<strong>Chairman:</strong> Anand Vidyasagar <br />
					<address>
						Phone: +91-20-2222-4444<br /> Email: adriana@adriana.com
					</address>
					<br /> <strong>Secretary:</strong> Anand Vidyasagar <br /> <strong>Treasurer:</strong>
					Anand Vidyasagar <br />
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
			<!-- /.box -->
		</div>

	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-primary" ng-controller="PropertyController">
				<div class="panel-heading">
					Properties
					<div class="pull-right box-tools">
						<button class="btn btn-info btn-sm " data-toggle="modal"
							data-target="#property-modal" title="Add">
							<i class="fa fa-plus-square"></i>
						</button>

						<button class="btn btn-info btn-sm" data-widget='remove'
							data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Name</th>
									<th>Type</th>

								</tr>
							</thead>
							<tbody ng-repeat="property in properties">
								<tr>
									<td class="vcenter"><input type="checkbox" id="blahA"
										value="{{property.propertyID}}" /></td>
									<td>{{property.propertyID}}</td>
									<td>{{property.propertyName}}</td>
									<td>{{property.propertyType}}</td>


								</tr>
						</table>
					</div>
				</div>
				<div class="panel-footer">Panel footer</div>
			</div>
		</div>

		<div class="col-md-6">
			<div class="panel panel-info" ng-controller="MemberHeaderController">
				<div class="panel-heading">
					Members
					<div class="pull-right box-tools">
						<button class="btn btn-info btn-sm " data-toggle="modal"
							data-target="#member-modal" title="Add">
							<i class="fa fa-plus-square"></i>
						</button>

						<button class="btn btn-info btn-sm" data-widget='remove'
							data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed ">
							<thead>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>First Name</th>
									<th>Middle Name</th>
									<th>Last Name</th>
									<th>Email-ID</th>
									<th>Roles</th>
									<th>Contact Info</th>
								</tr>
							</thead>
							<tbody ng-repeat="member in members">
								<tr>
									<td class="vcenter"><input type="checkbox" id="blahA"
										value="{{member.memberID}}" /></td>
									<td>{{member.memberID}}</td>
									<td>{{member.firstName}}</td>
									<td>{{member.middleName}}</td>
									<td>{{member.lastName}}</td>
									<td>{{member.emailId}}</td>
									<td>{{member.roles}}</td>

									<td>Adress :- {{member.contact.addressLine1}} ,
										{{member.contact.addressLine2}},
										{{member.contact.city}},{{member.contact.state}},{{member.contact.zipCode}}
										Contacts:-
										{{member.contact.primaryContact}},{{member.contact.secondaryContact}}</td>

								</tr>
						</table>
					</div>
				</div>
				<div class="panel-footer">Panel footer</div>
			</div>
		</div>
	</div>
</section>
<!-- /.content -->

<!-- Edit Society Info Modal -->
<div ng-controller="SocietyController">
	<div class="modal fade" id="society-modal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<i class="fa  fa-home"></i> &nbsp; Society Information
					</h4>
				</div>
				<form ng-submit="updateSociety(society)">
					<div class="modal-body">
						<div class="form-group">
							<label for="societyName">Society Name</label> <input type="text"
								class="form-control" id="societyName"
								placeholder="Enter Society Name" ng-model="society.name"
								required>
						</div>
						<div class="form-group">
							<label for="address1">Address Line1</label> <input type="text"
								class="form-control" id="address1"
								placeholder="Enter Address1.." ng-model="society.address1"
								required>
						</div>
						<div class="form-group">
							<label for="address2">Address Line2</label> <input type="text"
								class="form-control" id="address2"
								placeholder="Enter Address2.." ng-model="society.address2"
								required>
						</div>
						<div class="form-group">
							<label for="city">City</label> <input type="text"
								class="form-control" id="city" placeholder="Enter City.."
								ng-model="society.city" required>
						</div>
						<div class="form-group">
							<label for="zipcode">ZipCode</label> <input type="text"
								class="form-control" id="zipcode" placeholder="Enter zipcode.."
								ng-model="society.zipcode" required>
						</div>
						<div class="form-group">
							<label for="phoneNo">Phone No.</label> <input type="text"
								class="form-control" id="phoneNo" placeholder="Enter Phone No.."
								ng-model="society.phone" required>
						</div>
						<div class="form-group">
							<label for="email">Email Address</label>
							<div class="input-group">
								<span class="input-group-addon">@</span> <input type="email"
									class="form-control" id="eamil" placeholder="Enter email.."
									ng-model="society.email" required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success">Save
							changes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<div class="modal fade" id="office-modal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<i class="fa fa-building-o"></i> &nbsp; Office Information
					</h4>
				</div>
				<form ng-submit="updateOffice(office)">
					<div class="modal-body">
						<div class="form-group">
							<label for="chairmanName">Chairman Name</label> <input
								type="text" class="form-control" id="chairmanName"
								placeholder="Enter Society Name" ng-model="office.chairman"
								required>
						</div>
						<div class="form-group">
							<label for="phoneNo">Phone No.</label> <input type="text"
								class="form-control" id="phoneNo" placeholder="Enter Phone No.."
								ng-model="office.phone" required>
						</div>
						<div class="form-group">
							<label for="email">Email Address</label>
							<div class="input-group">
								<span class="input-group-addon">@</span> <input type="email"
									class="form-control" id="eamil" placeholder="Enter email.."
									ng-model="office.email" required>
							</div>
						</div>

						<div class="form-group">
							<label for="secretaryName">Secretary Name</label> <input
								type="text" class="form-control" id="secretaryName"
								placeholder="Enter Society Name" ng-model="office.secretary"
								required>
						</div>
						<div class="form-group">
							<label for="treasurerName">Treasurer Name</label> <input
								type="text" class="form-control" id="treasurerName"
								placeholder="Enter Society Name" ng-model="office.treasurer"
								required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success">Save
							changes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</div>
<!-- /.modal -->






<div class="modal fade" id="member-modal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<i class="fa fa-building-o"></i> &nbsp; Member Information
				</h4>
			</div>
			<form ng-submit="addMember(member)">
				<div class="modal-body">
					<div class="form-group">
						<label for="propertyName">First Name</label> <input type="text"
							class="form-control" id="propertyName"
							placeholder="Enter Property Name.." ng-model="property.name"
							required>
					</div>
					<div class="form-group">
						<label for="propertyType">Middle Name</label> <input type="text"
							class="form-control" id="propertyType"
							placeholder="Enter Property Type.." ng-model="property.type"
							required>
					</div>
					<div class="form-group">
						<label for="propertyType">Last Name</label> <input type="text"
							class="form-control" id="propertyType"
							placeholder="Enter Property Type.." ng-model="property.type"
							required>
					</div>

					<div class="form-group">
						<label for="propertyType">Email Id</label> <input type="email"
							class="form-control" id="propertyType"
							placeholder="Enter Property Type.." ng-model="property.type"
							required>
					</div>

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Save changes</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>