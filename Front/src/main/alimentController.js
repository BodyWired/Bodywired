BodyWiredApp.factory('Aliment', function(){
    var aliment = function(data){
       angular.extends(this, data);
    }
    return user;
});

BodyWiredApp.service('AlimentService', function($http, Toast){
    var urlAliment = "aliment/lister/";
    var urlDeclinaison = "aliment/declinaisons";
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
    this.getDeclinaison = function(aliment) {
        $http.get(urlDeclinaison, {id: aliment.id})
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des déclinaisons");
        });
        return [{"name":"frite"},{"name":"vapeur"},{"name":"eau"}];
    }
    this.getAliment = function(aliment) {
        $http.get(urlAliment, {id: aliment.id})
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation de l'aliment");
        });
        return {"name":"frite", "nutriments":[
            {"name":"nutriment1", "value":"10"},
            {"name":"nutriment2", "value":"12"},
            {"name":"nutriment3", "value":"13"}]
        };
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
    $scope.getDeclinaison = function(aliment) {
        $scope.declinaisons = AlimentService.getDeclinaison(aliment);
    }
    $scope.getAliment = function(aliment) {
        $scope.aliment = AlimentService.getAliment(aliment);
        console.log($scope.aliment.nutriments[0].name);
    }
});