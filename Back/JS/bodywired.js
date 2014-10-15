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
	  controller: 'menusController'
        })
	.otherwise({
		redirectTo:'/aliments'
	});
    }
]);

app.controller("globalController",['$scope',function($scope){
	$scope.menus=menu;
}]);
