BodyWiredApp.service('MenuService', function($http, Toast){
    var urlRecettes = baseURL + "recettes/lister";
    var urlNutriment = baseURL + "nutriments/dec/";
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

BodyWiredApp.controller('MenuController', function($scope, MenuService, AlimentService){
    MenuService.getRecettes().then(function(data) {
        $scope.recettes = data.data;
    });
    $scope.selectRecette = function(recette) {
        $scope.selectedRecette = recette;
    }
    $scope.getNutriments = function(declinaison) {
        AlimentService.getNumtriments(declinaison).then(function(data) {
            console.log(declinaison)
            $scope.coefficient = 1;
            $scope.nutriments = data.data;
        });
    }
    $scope.getNumber = function(num) {
        return new Array(num);   
    }
});