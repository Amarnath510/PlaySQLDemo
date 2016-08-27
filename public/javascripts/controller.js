var myApp = angular.module('myApp', ['ngRoute']);

function Student(pName, pEmail, pAge) {
	this.name = pName;
	this.email = pEmail;
	this.age = pAge;
}

myApp.controller('MyCtrl', ['$scope', '$location', '$http', function($scope, $location, $http) {
	
	$scope.students = [];
	
	$scope.showUpdate = false;
	
	refresh();
	
	$scope.updateStudent = new Object();
	
	$scope.create = function() {
		
		var stuObj = new Student($scope.student_name, $scope.student_email, $scope.student_age);
		if(!validate(stuObj)) {
			return false;
		}
		
		$http({
			url: '/api/student/add',
			method: 'post',
			header: 'application/json',
			data: stuObj
		}).success(function(response) {
			refresh();
		});
	};
	
	$scope.clear = function() {
		$scope.student_name = '';
		$scope.student_email = '';
		$scope.student_age = '';
	};
	
	function refresh() {
		$http({
			url: '/api/student/all',
			method: 'get',
			header: 'application/json',
		}).success(function(response) {
			// console.log(response);
			$scope.students = response;
		});
	};
	
	$scope.remove = function(student) {

		// when editing a record .. block the delete funtionality.
		if($scope.showUpdate == true) {
			return;
		}

		$http({
			url: '/api/student/delete',
			method: 'post',
			header: 'application/json',
			data: student
		}).success(function(response) {
			refresh();
		});
	};
	
	function validate(student) {
		if(student.name == undefined || student.email == undefined || student.age == undefined) {
			return false;
		} else if(student.name.trim().length == 0 || 
						student.email.trim().length == 0 || 
							student.age.trim().length == 0) {
			return false;
		} else {
			return true;
		}
	};
	
	$scope.edit = function(student) {
		$scope.showUpdate = true;
//		console.log(student);
		
		/*$scope.update_name = student.name;
		$scope.update_email = student.email;
		$scope.update_age = student.age;*/
		
		angular.copy(student, $scope.updateStudent);
	};
	
	$scope.cancelUpdate = function() {
		$scope.showUpdate = false;
	};
	
	$scope.update = function() {
//		console.log($scope.updateStudent);
		
		$http({
			url: '/api/student/update',
			method: 'post',
			header: 'application/json', 
			data: $scope.updateStudent
		}).success(function(response) {
//			console.log(response);
			$scope.showUpdate = false;
			refresh();
		});
	};
	
}]);