socmanCtrls.controller('SocietyController', function($scope, $http,
		$location) {
	debugger;
	$scope.updateSociety = function(society) {
		alert($scope.society.name);
	};
	
	$scope.updateOffice = function(office) {
		alert($scope.office.chairman);
	};
});