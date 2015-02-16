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

BodyWiredApp.controller('MenuController', function($scope,$modal, MenuService, AlimentService,UserService,FavorisService,$stateParams){
    $scope.previousRecette = undefined;
    MenuService.getRecettes().then(function(data) {
        $scope.recettes = data.data;
    });
    if(UserService.user!=undefined && UserService.user.id!=undefined){
	    FavorisService.getFavoris(UserService.user.id).then(function(data) {
		UserService.user.favoris = data.data;
	    });
    }
    $scope.selectRecette = function(id, previous) {
        $scope.previousRecette = previous;
        MenuService.getRecette(id).then(function(data) {
           $scope.selectedRecette = data.data; ;
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
	if(UserService.user!=undefined && UserService.user.id!=undefined){
		FavorisService.setFavoris(UserService.user.id,idRec).then(function(data){
			MenuService.getRecettes().then(function(data) {
        			$scope.recettes = data.data;
    			});
			FavorisService.getFavoris(UserService.user.id).then(function(data) {
				UserService.user.favoris = data.data;
			});
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
		      controller: 'AddPlanningController',
		      size: 'sm',
		      resolve:{
			recette : function(){
				return recette;
			}
		      }
    	});

     };
     if($stateParams.recId!=undefined){
	showRecette();
	$scope.selectRecette($stateParams.recId);
     }
});

BodyWiredApp.controller('AddPlanningController', function($scope,$modalInstance,$http,UserService, recette){
    $scope.recette=recette;
    $scope.date = new Date();

    var d=new Date();
    var day=d.getDate()<9?'0'+d.getDate():d.getDate();
    var month=d.getMonth()<9?'0'+(d.getMonth()+1):(d.getMonth()+1);

    $scope.minDate=d.getFullYear()+"-"+month+"-"+day;

    $scope.ok=function(){
	$http.get(baseURL+"users/ajouterPlanning/"+UserService.user.id+"/"+recette.id+"/"+$scope.date.getTime()+"/"+$scope.repas)
        .success(function(data) {
        	$modalInstance.dismiss();
	}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des favoris");
        });
    };

    $scope.cancel=function(){
        $modalInstance.dismiss();
    };
});
