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

app.controller("globalController",['$scope','$location',function($scope,$location){
	$scope.menus=menu;

	/*
	$scope.$on('$routeChangeSuccess', function(next, current) { 
   		var items = document.getElementsById("menu-item");
		var i;
		for (i = 0; i < items.length; i++) {
		    items[i].setAttribute("ng-class",getClass(item[i].getAttribute("ng-href"));
		}
 	});

	$scope.getClass = function(path) {
	    if ($location.hash() == path) {
	      return "'active'"
	    } else {
	      return ""
	    }
	};*/

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
