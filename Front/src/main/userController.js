BodyWiredApp.factory('User', function(){
    var user = function(data) {
        angular.extend(this, {
            login: "",
            isConnected: function() {
                if (this.login.length > 0) {
                    return true;
                }
                return false;
            },
            fullname: function() {
                return this.name + " " + this.lastname;
            }
        }, data);
    }
    return user;
});

BodyWiredApp.service('UserService', function($location, $http, Toast, User) {
    this.user = new User({});
	var urlConnexion = "/user/connexion";
	var urlEdit = "/user/edit";
	var urlRegister = "/user/enregistrement";
	this.signin = function(user) {
        $http.post(urlConnexion, {email:user.email, password:user.password})
		.success(function(data) {
            return new User(data);
		}).error(function(error) {
            Toast.error("Mauvais email et/ou mot de passe");
		});
        if (user.email == 'twx@twx.fr' && user.password == "123") {
            this.user = new User({id:'0', login:'TwX', email:'twx@twx.fr', name:'Alexandre', lastname:'FRANCOIS'});
            $location.path('/');
        }
	}
    this.logout = function() {
        this.user = new User({id:'', login:'', email:'', name:'', lastname:''});
        $location.path('/');
        Toast.info("Vous êtes déconnecté");
    }
    this.register = function(user) {
        $http.post(urlRegister, {login:user.login, email:user.email, password:user.password})
            .success(function(data) {
                Toast.info("Vous êtes enregisté, vous pouvez vous connecter");
            }).error(function(data) {
                Toast.error("Une erreur est survenu lors de votre enregistrement");
            });
    }
    this.update = function(user) {
        this.user = user;
        $http.post(urlRegister, user).success(function(data) {
            this.user = user;
            Toast.info("Votre profil a été modifié");
        }).error(function(data) {
            Toast.error("Une erreur lors de l'edition de votre profil");
        });
        $location.path('/profil');
    }
});

BodyWiredApp.controller('UserController', function($rootScope, $scope, UserService, Toast, User) {
    $rootScope.user = UserService.user;
    $scope.signin = function(user) {
        UserService.signin(user);
        $rootScope.user = UserService.user;
    }
    $scope.logout = function() {
        $scope.user = UserService.logout();
        $rootScope.user = UserService.user;
    }
    $scope.register = function(user) {
        if (user.password == user.passwordAgain) {
            UserService.register(user);
        } else {
            Toast.error("Votre mot de passe n'est pas identique");
        }
    }
    $scope.update = function(user) {
        UserService.update(user);
        $rootScope.user = UserService.user;
    }
    $scope.copyUser = function(user) {
        $scope.userTmp = angular.copy(user);
        
    }
});