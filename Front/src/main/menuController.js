BodyWiredApp.service('MenuService', function($http, Toast){
    var urlRecettes = baseURL + "recettes/lister";
    var urlNutriment = baseURL + "nutriments/dec/";
    var urlRecetteFull = baseURL + "recettes/recette/";
    var urlFavoris = baseURL + "users/favoris/";
    var urlAddFavoris = baseURL + "users/ajouterFavori/";
    
    this.getRecettes = function() {
        return $http.get(urlRecettes)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des menus");
        });
    };

    this.getRecette = function(id) {
        return $http.get(urlRecetteFull+id).success(function(data) {
            return data
        }).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation du menu");
        });
    };
    this.getNutriments = function(declinaison) {
        return $http.get(urlNutriments+declinaison)
        .success(function(data) {
            return data;
		}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation de l'aliment");
        });
    };
});

BodyWiredApp.controller('MenuController', function($scope,$modal, MenuService, AlimentService,UserService,FavorisService){
    $scope.previousRecette = undefined;
    MenuService.getRecettes().then(function(data) {
        $scope.recettes = data.data;
    });
    if(UserService.user!=undefined){
	    FavorisService.getFavoris(UserService.user.id).then(function(data) {
		UserService.user.favoris = data.data;
	    });
    }
    $scope.selectRecette = function(id, previous) {
        $scope.previousRecette = previous;
        console.log($scope.previousRecette);
        MenuService.getRecette(id).then(function(data) {
           $scope.selectedRecette = data.data; 
            console.log($scope.selectedRecette);
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
    $scope.setFavoris = function(idRec){
	if(UserService.user!=undefined){
		FavorisService.setFavoris(UserService.user.id,idRec).then(function(data){
		});
	}
    };
    $scope.isFavoris = function(id){
	if(UserService.user!=undefined){
		return UserService.user.isFavoris(id);
	}
	return false;
     };
     $scope.addPlanning=function(recette){
	var modalInstance = $modal.open({
		      templateUrl: 'partiels/addPlanning.html',
		      controller: 'AddPlanningCtrl',
		      size: 'sm'
    });

     };
});
