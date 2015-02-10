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

	var openModal=function(data){
		var modalInstance = $modal.open({
			templateUrl: 'recettes/formulaireRecettes.html',
			controller: 'formulaireRecettesCtrl',
			size: 'lg',
			resolve:{
				data : function(){
					return data;
				}
			}
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

	$scope.deleteRecette=function(id){
		var url=path+'/recettes/supprimer/'+id;
		$http.delete(url).success(function(data,status){
			loadRecettes();
		});
	};

	$scope.updateRecette=function(id){
		var url=path+'/recettes/recette/'+id;
		$http.get(url).success(function(data,status){
				openModal(angular.fromJson(data));
		});
	};

	$scope.add=function(){
		openModal(undefined);
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

app.controller('formulaireRecettesCtrl', function ($scope,$http, $modalInstance,data) {
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
	$scope.categorie=[];
	$scope.categories={};
	$scope.listeAliment=[];
	$scope.alimentRecette='';

	var lastSearch='';
	var globalData=data;

	if(data!=undefined){
		console.log(data);
		$scope.nom=data.nom;
		$scope.calories=data.calories;
		$scope.preparation=data.preparation;
		$scope.tempsPrepaH=Math.floor(data.tmpPreparation/60);
		$scope.tempsPrepaM=data.tmpPreparation%60;
		$scope.tempsCuissonH=Math.floor(data.tmpCuisson/60);
		$scope.tempsCuissonM=data.tmpCuisson%60;
		$scope.tempsRefrigH=Math.floor(data.tmpRefrigeration/60);
		$scope.tempsRefrigM=data.tmpRefrigeration%60;
		$scope.tempsMacerationH=Math.floor(data.tmpMaceration/60);
		$scope.tempsMacerationM=data.tmpMaceration%60;
		var aliments=[];
		for(var al in data.aliments){
			aliments.push({
				quantite:data.aliments[al].quantite,
				id:data.aliments[al].aliment.id,
				nom:data.aliments[al].aliment.nom
			});
		}
		$scope.aliments=aliments;
	}

	var loadCategorie=function(){
		var categorie=[];
		var url=path+'/recettes/categories';
		$http.get(url).success(function(data,status){
				for(var cat in globalData.categories){
					categorie.push(globalData.categories[cat].id);
				}
				$scope.categorie=categorie;
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
		for(var al in $scope.listeAliment){
			if($scope.listeAliment[al].nom==nom){
				$scope.aliments[nom]={id:$scope.listeAliment[al].id,nom:nom,quantite:0};
			}
		}
	};

	$scope.removeAliment=function(nom){
		delete $scope.aliments[nom];
	};
	
	$scope.save = function (){
		var aliments=[];
		var categories=[];
		for(var al in $scope.aliments){
			aliments.push({
				quantite:$scope.aliments[al].quantite,
				aliment:{id:$scope.aliments[al].id}
			});
		}
		for(var cat in $scope.categorie){
			categories.push({
				id:$scope.categorie[cat]
			});
		}
		var data={
			nom:$scope.nom,
			preparation:$scope.preparation,
			tmpPreparation:$scope.tempsPrepaH*60+$scope.tempsPrepaM,
			tmpCuisson:$scope.tempsCuissonH*60+$scope.tempsCuissonM,
			tmpRefrigeration:$scope.tempsRefrigH*60+$scope.tempsRefrigM,
			tmpMaceration:$scope.tempsMacerationH*60+$scope.tempsMacerationM,
			calories:$scope.calories,
			aliments:aliments,
			categories:categories
		};
		if(globalData==undefined){
			$http.post(path+'/recettes/ajouter',angular.toJson(data)).success(function(data,status){
			});
		}
		else{
			data.id=globalData.id;
			$http.put(path+'/recettes/modifier',angular.toJson(data)).success(function(data,status){
			});
		}
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadCategorie();
});
