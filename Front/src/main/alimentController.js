BodyWiredApp.factory('Aliment', function(){
    var aliment = function(data){
       angular.extends(this, data);
    }
    return user;
});

BodyWiredApp.service('AlimentService', function($http, Toast){
    var urlAliments = "aliment/lister/";
    var urlAliments = "aliment/";
    var urlCategories = "categorie/lister";
    var urlAlimentCategorie = "aliment/categorie/";
    this.getCategories = function() {
        $http.get(urlCategories)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des catégories d'aliments");
        });
        return [{"name":"fruits"},{"name":"legumes"},{"name":"viandes"}];
    }
    this.getAlimentsByCategorie = function(categorie) {
        $http.get(urlAlimentCategorie, {categorie: categorie})
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des aliments");
        });
        if(categorie == "legumes") {
            return [{"name":"salade"},{"name":"tomate"},{"name":"navet"}];
        }
        if(categorie == "fruits") {
            return [{"name":"pomme"},{"name":"kiwi"},{"name":"fraise"}];
        }
        if(categorie == "viandes") {
            return [{"name":"cheval"},{"name":"boeuf"},{"name":"steack"}];
        }
    }
    this.getAliment = function(aliment) {
        $http.get(urlAliment, {id: aliment.id})
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation de l'aliment");
        });
    }
});

BodyWiredApp.controller('AlimentController', function($scope, AlimentService){
    $scope.categories= AlimentService.getCategories();
    $scope.$watch('categorieSelected', function(){
        $scope.getAlimentsByCategorie($scope.categorieSelected);
    });
    $scope.getAlimentsByCategorie = function(categorie) {
        console.log(categorie.name);
        $scope.aliments = AlimentService.getAlimentsByCategorie(categorie.name);
    }
    $scope.getAliment = function(aliment) {
        $scope.aliment = AlimentService.getAliment(aliment);
    }
});