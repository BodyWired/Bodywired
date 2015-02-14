BodyWiredApp.factory('Aliment', function(){
    var aliment = function(data){
       angular.extends(this, data);
    }
    return user;
});

BodyWiredApp.service('AlimentService', function($http, Toast){
    var urlNutriments = baseURL + "nutriments/dec/";
    var urlDeclinaison = baseURL + "aliment/declinaisons";
    var urlCategories = baseURL+"categories/lister";
    var urlAlimentCategorie = baseURL + "aliment/lister?idCategorie=";
    this.getCategories = function() {
        return $http.get(urlCategories)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des catégories d'aliments");
        });
    }
    this.getAlimentsByCategorie = function(categorie) {
        return $http.get(urlAlimentCategorie+categorie.id)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des aliments");
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

BodyWiredApp.controller('AlimentController', function($scope, AlimentService){
    $scope.$watch('categorieSelected', function(){
        $scope.getAlimentsByCategorie($scope.categorieSelected);
    });
    $scope.getAlimentsByCategorie = function(categorie) {
        AlimentService.getAlimentsByCategorie(categorie).then(function(data) {
            $scope.aliments = data.data;
        });
    }
    $scope.getDeclinaison = function(aliment) {
        $scope.declinaisons = aliment.declinaisons;
    }
    $scope.getNutriments = function(declinaison) {
        AlimentService.getNumtriments(declinaison).then(function(data) {
            $scope.nutriments = data.data;
            console.log(data.data);
        });
    }
    AlimentService.getCategories().then(function(data) {
        $scope.categories = data.data;
    });
});