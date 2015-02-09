app.controller("recettesController",['$scope','$modal','$http',function($scope,$modal,$http){
	$scope.nbRecettes=0;
	$scope.currentPage=1;
	$scope.recettes=[];

	var loadRecettes=function(){
		var url=path+'/recettes/lister?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		if($scope.filtre!=undefined && $scope.filtre!=''){
			url+='&filtre='+$scope.filtre;
		}
		$http.get(url).success(function(data,status){
				$scope.recettes=angular.fromJson(data);
		});
	};

	$scope.showRecette=function(recette){
		var modalInstance = $modal.open({
			templateUrl: 'recettes/showRecette.html',
			controller: 'showRecetteCtrl',
			size: 'lg',
			resolve:{
				recette:function(){return recette;}
			}
    		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'recettes/formulaireRecettes.html',
			controller: 'formulaireRecettesCtrl',
			size: 'lg'
    		});
	};

	loadRecettes();
}]);

app.controller('showRecetteCtrl', function ($scope,$http, $modalInstance,recette) {
	$scope.recette={};

	var loadRecette=function(){
		var url=path+'/recettes/recette/'+recette.id;
		$http.get(url).success(function(data,status){
				$scope.recette=angular.fromJson(data);
		});
	};

	loadRecette();
});

app.controller('formulaireRecettesCtrl', function ($scope,$http, $modalInstance) {
	$scope.tempsPrepaH=0;
	$scope.tempsPrepaM=0;
	$scope.tempsCuissonH=0;
	$scope.tempsCuissonM=0;
	$scope.tempsRefrigH=0;
	$scope.tempsRefrigM=0;
	$scope.tempsMacerationH=0;
	$scope.tempsMacerationM=0;
	$scope.aliments={};
	$scope.nom="";
	$scope.preparation="";
	$scope.calories=0;
	$scope.categories=0;
	$scope.listeAliment=[];
	$scope.alimentRecette='';

	var lastSearch='';

	var loadCategorie=function(){
		var url=path+'/recettes/categories';
		$http.get(url).success(function(data,status){
				$scope.categories=angular.fromJson(data);
		});
	};

	$scope.choisirAliment=function(){
		if($scope.alimentRecette.length>2){
			if(lastSearch!=$scope.alimentRecette.substr(0,3)){
				lastSearch=$scope.alimentRecette.substr(0,3);
				var url=path+'/aliment/lister?filtre='+$scope.alimentRecette;
				$http.get(url).success(function(data,status){
						$scope.listeAliment=angular.fromJson(data).aliments;
				});
			}
		}
		else{
			$scope.listeAliment={};
		}
	};

	$scope.addAliment=function(){
		var nom=$scope.alimentRecette;
		console.log(nom);
		$scope.aliments[nom]={nom:nom,quantite:0};
	};

	$scope.removeAliment=function(nom){
		delete $scope.aliments[nom];
	};
	
	$scope.save = function (){
		var aliments=[];
		for(var al in $scope.aliments){
			/*aliments[]={
				quantite:al.quantite,
				aliment:{id:}
			};*/
		}
		var data={
			nom:$scope.nom,
			preparation:$scope.preparation,
			tmpPreparation:$scope.tempsPrepaH*60+$scope.tempsPrepaM,
			tmpCuisson:$scope.tempsCuissonH*60+$scope.tempsCuissonM,
			tmpRefrigeration:$scope.tempsRefrigH*60+$scope.tempsRefrigM,
			tmpMaceration:$scope.tempsMacerationH*60+$scope.tempsMacerationM,
			calories:$scope.calories,
			aliments:aliments
		};
		/*$http.post('/declinaison/ajouter',{data:angular.toJson(data)}).success(function(data,status){
		});*/
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadCategorie();
});
