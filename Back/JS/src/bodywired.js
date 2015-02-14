var path="apitest";

var app=angular.module("bodywired",['ngRoute','ui.bootstrap']);     

app.config(['$routeProvider','$httpProvider',
    function($routeProvider,$httpProvider) {
      $routeProvider
	.when('/categories', {
          templateUrl: 'categories.html',
	  controller: 'categoriesController'
        })
	.when('/etats_declinaisons', {
          templateUrl: 'etats.html',
	  controller: 'etatsController'
        })
        .when('/aliments', {
          templateUrl: 'aliments.html',
	  controller: 'alimentsController'
        })
	.when('/categoriesRecette', {
          templateUrl: 'categoriesRecette.html',
	  controller: 'categoriesRecetteController'
        })
        .when('/recettes', {
          templateUrl: 'recettes.html',
	  controller: 'recettesController'
        })
	.otherwise({
		redirectTo:'/aliments'
	});
	//$httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
	$httpProvider.defaults.headers.delete = { 'Content-Type' : 'application/json','Content-Length' : 0 };
    	$httpProvider.defaults.useXDomain = true;
    }
]);

app.controller("globalController",['$scope','$location',function($scope,$location){
	$scope.menus=menu;
}]);

app.controller("menuItemController",['$scope','$location',function ($scope,$location) {
		$scope.getClass = function(path){
			if ("#"+$location.path() == path) {
	    		return "active"
	    	} else {
	    		return ""
	    	}
		};		
}]);
