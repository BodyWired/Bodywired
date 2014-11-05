var BodyWiredApp = angular.module('BodyWiredApp', ['ui.router', 'ngToast']);

BodyWiredApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');
	$stateProvider.state('home', {url : '/', templateUrl: 'partiels/accueil.html', controller: 'HomeController'})
			      .state('login', {url : '/login', templateUrl: 'partiels/connexion.html', controller: 'UserController'})
			      .state('logout', {url : '/logout', templateUrl: 'partiels/logout.html', controller: 'UserController'})
			      .state('profil', {url : '/profil', templateUrl: 'partiels/profil.html', controller: 'UserController'})
			      .state('editerProfil', {url : '/profilEdit', templateUrl: 'partiels/profilEdit.html', controller: 'UserController'});
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