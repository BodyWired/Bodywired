BodyWiredApp.service('FavorisService', function($http, Toast){
    var urlFavoris = baseURL + "users/favoris/";
    var urlAddFavoris = baseURL + "users/ajouterFavori/";
    
    this.getFavoris = function(userId){
	return $http.get(urlFavoris+userId)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des favoris");
        });
    };

    this.setFavoris = function(userId,idRec){
	return $http.get(urlAddFavoris+userId+"/"+idRec)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des favoris");
        });
    };
});

BodyWiredApp.controller('FavorisController', function($scope,MenuService, AlimentService,UserService){
    $scope.previousRecette = undefined;
    $scope.recettes=UserService.user.favoris;

    $scope.selectRecette = function(id, previous) {
        $scope.previousRecette = previous;
        MenuService.getRecette(id).then(function(data) {
           $scope.selectedRecette = data.data; 
        });
    };
    $scope.getNutriments = function(declinaison) {
        AlimentService.getNumtriments(declinaison).then(function(data) {
            $scope.coefficient = 1;
            $scope.nutriments = data.data;
        });
    };
    $scope.getNumber = function(num) {
        return new Array(num);   
    };
});
