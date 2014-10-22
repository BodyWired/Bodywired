var app=angular.module("bodywired",['ngRoute','ui.bootstrap']);

app.config(['$routeProvider',
    function($routeProvider) {
      $routeProvider
        .when('/aliments', {
          templateUrl: 'aliments.html',
	  controller: 'alimentsController'
        })
        .when('/menus', {
          templateUrl: 'menus.html',
        })
	.otherwise({
		redirectTo:'/aliments'
	});
    }
]);

app.controller("globalController",['$scope','$location',function($scope,$location){
	$scope.menus=menu;

	$scope.getClass = function(path) {
	    if ($location.hash() == path) {
	      return "'active'"
	    } else {
	      return ""
	    }
	};
}]);
