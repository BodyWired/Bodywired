app.service("$alimentService",['$modal',function($modal){
	this.getDeclinaison=function(aliment){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/declinaisons.html',
			controller: 'declinaisonCtrl',
			size: 'lg',
			resolve:{
				aliment:function(){
					return aliment;
				}
			}
    		});
	};
}]);

app.controller("alimentsController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbAliments=0;
	$scope.currentPage=1;
	$scope.aliments=[];

	var loadAliments=function(){
		var url=path+'/aliment/lister?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		$http.get(url).success(function(data,status){
			$http.get(path+'/aliment/total').success(function(data2,status){
				$scope.aliments=angular.fromJson(data);
				$scope.nbAliments=data2;
			});
		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireAliments.html',
			controller: 'formulaireAlimentsCtrl',
			size: 'lg'
    		});
	};

	$scope.pageChanged=function(){
		loadAliments();
	};

	loadAliments();
}]);

app.controller('formulaireAlimentsCtrl',['$scope','$alimentService','$http','$modalInstance', function ($scope,$alimentService,$http, $modalInstance) {
	$scope.categories=[];
	$scope.categorieSelect=[];

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
		$http({url:path+'/aliment/ajouter',method:'POST',data:{data:angular.toJson(data)}}).success(function(data,status){
			console.log(data);
			//$alimentService.getDeclinaison(angular.fromJson(data));
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadCategorie();
}]);

app.controller('declinaisonCtrl',['$scope','$http','$modal','$modalInstance','aliment', function ($scope,$http, $modal,$modalInstance,aliment) {
	$scope.nbDeclinaisons=0;
	$scope.currentPage=1;
	$scope.declinaisons=[];

	var loadDeclinaisons=function(){
		$http.get(path+'/declinaison/etat/lister').success(function(data,status){
			$scope.declinaisons=angular.fromJson(data);
		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireDeclinaisons.html',
			controller: 'formulaireDeclinaisonCtrl',
			size: 'lg'
    		});
	};

	loadDeclinaisons();

}]);

app.controller('formulaireDeclinaisonsCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {
	$scope.categories=[];

	loadCategorie=function(){
		$http.get('/aliment/categories').success(function(data,status){
			$scope.categories=angular.fromJson(data);
		});
	};

	$scope.nutrimentsUnused=angular.copy(nutriments);
	$scope.nutrimentsUsed=[];
	$scope.nutrimentsUnusedSelected=[];
	$scope.nutrimentsUsedSelected=[];

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
		$alimentService.getDeclinaison(1);
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
}]);
