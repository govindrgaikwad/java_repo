var socmanApp = angular.module('socmanApp', [ 'MemberControllers' ]).config(
		function($locationProvider) {
			$locationProvider.html5Mode(true);
		});

var socmanCtrls = angular.module('MemberControllers', []);

socmanCtrls.controller('MemberHeaderController', function($scope, $http,
		$location) {

	$scope.messages = {};
	$scope.userName = 'admin';
	$scope.notifications = {};
	$scope.memberDetails;
	$scope.NumTasks = 12;
	$scope.members = {};
	var path = $location.path(); // will tell you the current path
	path = path.substr(1).split('/'); // you still have to split to get the
										// application context

	$scope.basePath = "/" + path[0];

	function getMessages() {
		var url = $scope.basePath + '/protected/member/' + $scope.userName
				+ '/messages';
		$http.get(url, {
			'userName' : $scope.userName
		}).success(function(data) {
			$scope.messages = data;
		}).error(function(status) {
			alert("Status" + status);
		});
	}
	;

	function getNotifications() {
		var url = $scope.basePath + '/protected/member/' + $scope.userName
				+ '/notifications';
		$http.get(url, {
			'userName' : $scope.userName
		}).success(function(data) {
			$scope.notifications = data;
		}).error(function(status) {
			alert("Status" + status);
		});
	}
	;

	function getMemberDetails() {
		var url = $scope.basePath + '/protected/member/' + $scope.userName;
		$http.get(url, {
			'userName' : $scope.userName
		}).success(function(data) {
			$scope.memberDetails = data;
		}).error(function(status) {
			alert(status);
		});
	}
	;

	function getMembers() {
		var url = $scope.basePath + '/protected/member/members/';
		$http.get(url).success(function(data) {
			$scope.members = data;
		}).error(function(status) {
			alert(status);
		});
	}
	;

	$scope.messages = getMessages();
	$scope.notifications = getNotifications();
	$scope.memberDetails = getMemberDetails();
	$scope.members = getMembers();
});
