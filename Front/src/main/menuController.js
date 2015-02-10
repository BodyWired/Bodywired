BodyWiredApp.service('MenuService', function($http, Toast){
    var urlRecettes = baseURL + "recettes/lister";
    var urlNutriment = baseURL + "nutriments/dec/";
    var urlRecetteFull = baseURL + "recettes/recette/";
    
    this.getRecettes = function() {
        return $http.get(urlRecettes)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des menus");
        });
    }
    this.getRecette = function(id) {
        return $http.get(urlRecetteFull+id).success(function(data) {
            return data
        }).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation du menu");
        });
    }
    this.getNutriments = function(declinaison) {
        return $http.get(urlNutriments+declinaison)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation de l'aliment");
        });
    }
});

BodyWiredApp.controller('MenuController', function($scope, MenuService, AlimentService){
    $scope.previousRecette = undefined;
    MenuService.getRecettes().then(function(data) {
        $scope.recettes = data.data;
    });
    $scope.selectRecette = function(id, previous) {
//        $scope.selectedRecette = recette;
        $scope.previousRecette = previous;
        console.log($scope.previousRecette);
        MenuService.getRecette(id).then(function(data) {
           $scope.selectedRecette = data.data; 
            console.log($scope.selectedRecette);
        });
    }
    $scope.getNutriments = function(declinaison) {
        AlimentService.getNumtriments(declinaison).then(function(data) {
            $scope.coefficient = 1;
            $scope.nutriments = data.data;
        });
    }
    $scope.getNumber = function(num) {
        return new Array(num);   
    }
});