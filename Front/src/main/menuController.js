BodyWiredApp.service('MenuService', function($http, Toast){
    var urlRecettes = "http://iagl-server.cloudapp.net/api/recettes/lister";
    var urlNutriment = "http://iagl-server.cloudapp.net/api/nutriments/dec/";
    this.getRecettes = function() {
        return $http.get(urlRecettes)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des menus");
        });
    }
    this.getRecette = function() {
        return $http.get(urlMenu).success(function(data) {
            return data
        }).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation du menu");
        });
    }
    this.getNumtriments = function(declinaison) {
        return $http.get(urlNutriments+declinaison)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation de l'aliment");
        });
    }
});

BodyWiredApp.controller('MenuController', function($scope, MenuService){
    MenuService.getRecettes().then(function(data) {
        $scope.recettes = data.data;
    });
    $scope.selectRecette = function(recette) {
        $scope.selectedRecette = recette;
/*
        MenuService.getMenu(menu.id).then(function(data) {
            $scope.selectedMenu = data.data;
        });
*/
    }
    $scope.getNutriments = function(declinaison) {
        AlimentService.getNumtriments(declinaison).then(function(data) {
            $scope.nutriments = data;
            console.log($scope.nutriments)
        });
    }
    $scope.getNumber = function(num) {
        return new Array(num);   
    }
});