BodyWiredApp.service('MenuService', function($http, Toast){
    var urlMenus = "http://iagl-server.cloudapp.net/api/menu/lister";
    this.getMenus = function() {
        return $http.get(urlMenus)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des menus");
        });
    }
    this.getMenu = functioin() {
        return $http.get(urlMenu).success(function(data) {
            return data
        }).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation du menu");
        });
    }
});

BodyWiredApp.controller('MenuController', function($scope, MenuService){
    MenuService.getMenus.then(function(data) {
        $scope.menus = data.data;
    });
    $scope.selectMenu = function(menu) {
        MenuService.getMenu(menu.id).then(function(data) {
            $scope.selectedMenu = data.data;
        });
    }
});