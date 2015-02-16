var BodyWiredApp = angular.module('BodyWiredApp', ['ui.router', 'ngToast','ui.calendar','ui.bootstrap']);

BodyWiredApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');
	$stateProvider.state('home', {url: '/', templateUrl: '/partiels/accueil.html', controller: 'HomeController'})
                  .state('login', {url: '/login', templateUrl: '/partiels/connexion.html', controller: 'UserController'})
                  .state('logout', {url: '/logout', templateUrl: '/partiels/logout.html', controller: 'UserController'})
                  .state('profil', {url: '/profil', templateUrl: '/partiels/profil.html', controller: 'UserController'})
                  .state('editerProfil', {url: '/profilEdit', templateUrl: '/partiels/profilEdit.html', controller: 'UserController'})
                  .state('aliments', {url: '/aliments', templateUrl: '/partiels/aliments.html', controller:'AlimentController'})
                  .state('carences', {url: '/carences', templateUrl: '/partiels/carences.html', controller:'CarenceController'})
                  .state('recettes', {url: '/recettes', templateUrl: '/partiels/recettes.html', controller:'MenuController'})
		  .state('recettes2', {url: '/recettes/:recId', templateUrl: '/partiels/recettes.html', controller:'MenuController'})
		  .state('favoris', {url: '/favoris', templateUrl: '/partiels/favoris.html', controller:'FavorisController'});
});
BodyWiredApp.config(["$locationProvider", function($locationProvider) {
  $locationProvider.html5Mode(true);
  $locationProvider.autoscroll = "false";
}]);

BodyWiredApp.service('Toast', function(ngToast){
	this.success = function(message) {
		ngToast.create({content:message,
						class:'alert alert-success'});
	}
	this.info = function(message) {
		ngToast.create({content:message,
						class:'alert alert-info'});
	}
	this.warning = function(message) {
		ngToast.create({content:message,
						class:'alert alert-warning'});
	}
	this.error = function(message) {
		ngToast.create({content:message,
						class:'alert alert-danger'});
	}
})

var baseURL = "http://iagl-server.cloudapp.net/apitest/";
