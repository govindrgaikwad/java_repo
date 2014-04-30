socmanCtrls.controller('PropertyController',
		function($scope, $http, $location) {

			debugger;

			var path = $location.path(); // will tell you the current path
			path = path.substr(1).split('/'); // you still have to split to
			// get the application context

			$scope.basePath = "/" + path[0];

			$scope.properties = {};

			$scope.add = function(property) {
				alert($scope.proerty.name);

			};

			function getProperties() {
				debugger;
				var url = $scope.basePath + '/property/';
				$http.get(url).success(function(data) {
					$scope.properties = data;
				}).error(function(status) {
					alert("Status" + status);
				});
			}
			;

			$scope.properties = getProperties();
		});