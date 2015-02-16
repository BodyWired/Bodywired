app.controller("alimentsController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbAliments=0;
	$scope.currentPage=1;
	$scope.aliments=[];
	$scope.filtre=undefined;

	var loadAliments=function(){
		var url=path+'/aliment/lister?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		if($scope.filtre!=undefined && $scope.filtre!=''){
			url+='&filtre='+$scope.filtre;
		}
		$http.get(url).success(function(data,status){
			var jsonObj=angular.fromJson(data);
			$scope.aliments=jsonObj.aliments;
			$scope.nbAliments=jsonObj.total;
		});
	};

	var openModal=function(data){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireAliments.html',
			controller: 'formulaireAlimentsCtrl',
			size: 'lg',
			resolve:{
				data : function(){
					return data;
				}
			}
    		});
	};

	$scope.parseEtat=function(etats){
		var etats_tab=[];
		for(var i=0;i<etats.length;i++){
			etats_tab.push(etats[i].nom);
		}
		return etats_tab.join(', ');
	};

	$scope.updateAliment=function(aliment){
		openModal(aliment);
	};
		

	$scope.add=function(){
		openModal(undefined);
	};

	$scope.deleteAliment=function(id){
		var url=path+'/aliment/supprimer/'+id;
		$http.delete(url).success(function(data,status){
			loadAliments();
		});
	};

	$scope.deleteDeclinaison=function(id){
		var url=path+'/declinaison/rm/'+id;
		$http.delete(url).success(function(data,status){
			loadAliments();
		});
	};

	$scope.addDeclinaison=function(){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireDeclinaison.html',
			controller: 'formulaireDeclinaisonsCtrl',
			size: 'lg'
    		});
	};

	$scope.pageChanged=function(){
		loadAliments();
	};

	loadAliments();
}]);

app.controller('formulaireAlimentsCtrl',['$scope','$http','$modalInstance','data', function ($scope,$http, $modalInstance,data) {
	$scope.categories=[];
	$scope.categorieSelect=[];

	var globalData=data;

	if(data!=undefined){
		$scope.nom=data.nom;
		var categories=[];
		for(var cat in data.categories){
			categories.push(data.categories[cat].id);
		}
		$scope.categorieSelect=categories;
	}

	loadCategorie=function(){
		$http.get(path+'/categories/lister').success(function(data,status){
			$scope.categories=angular.fromJson(data);
		});
	};

	$scope.save = function (){
		var tab=[];
		for(var i in $scope.categorieSelect){
			tab.push({id:$scope.categorieSelect[i]});
		}
		var data={nom:$scope.nom,categories:tab};
		if(globalData!=undefined){
			data.id=globalData.id;
			$http({url:path+'/aliment/modifier',method:'PUT',data:angular.toJson(data)}).success(function(data,status){
				$modalInstance.close();
			});
		}
		else{
			$http({url:path+'/aliment/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
				$modalInstance.close();
			});
		}
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadCategorie();
}]);

app.controller('formulaireDeclinaisonsCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {
	$scope.nutrimentsUnused=[];
	$scope.nutrimentsUsed=[];
	$scope.nutrimentsUnusedSelected=[];
	$scope.nutrimentsUsedSelected=[];

	$scope.etats=[];

	var loadNutriments=function(){
		$http.get(path+"/nutriments/types").success(function(data,status){
			$scope.nutrimentsUnused=[];
			for(var i=0;i<data.vitamines.length;i++){
				$scope.nutrimentsUnused.push({name:"Vitamine "+data.vitamines[i].code});
			}
		});
	};

	var loadEtat=function(){
		$http.get(path+"/declinaison/etat/lister").success(function(data,status){
			$scope.etats=data;
		});
	};

	$scope.toUsed=function(){
		var decalage=0;
		for(var i in $scope.nutrimentsUnusedSelected){
			var selected=$scope.nutrimentsUnusedSelected[i];
			$scope.nutrimentsUsed.push($scope.nutrimentsUnused[selected]);
		}
		for(var i in $scope.nutrimentsUnusedSelected){
			var selected=$scope.nutrimentsUnusedSelected[i];
			$scope.nutrimentsUnused.splice(selected-decalage,1);
			decalage++;
		}
	};

	$scope.onChangeUnused=function(items){
		$scope.nutrimentsUnusedSelected=items;
	};

	$scope.toUnused=function(){
		var decalage=0;
		for(var i in $scope.nutrimentsUsedSelected){
			var selected=$scope.nutrimentsUsedSelected[i];
			$scope.nutrimentsUnused.push($scope.nutrimentsUsed[selected]);
		}
		for(var i in $scope.nutrimentsUsedSelected){
			var selected=$scope.nutrimentsUsedSelected[i];
			$scope.nutrimentsUsed.splice(selected-decalage,1);
			decalage++;
		}
	};

	$scope.onChangeUsed=function(items){
		$scope.nutrimentsUsedSelected=items;
	};
	

	$scope.save = function (){
		var data={nom:$scope.nom,categorie:$scope.categorie};
		$http.post('/declinaison/ajouter',{data:angular.toJson(data)}).success(function(data,status){
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadNutriments();
	loadEtat();
}]);
