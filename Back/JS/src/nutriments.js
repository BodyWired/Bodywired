app.controller("nutrimentsController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbEtats=0;
	$scope.currentPage=1;
	$scope.nutriments=[];
	$scope.filtre=undefined;

	var loadNutriments=function(){
		var url=path+'/nutriments/types?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		if($scope.filtre!=undefined && $scope.filtre!=''){
			url+='&filtre='+$scope.filtre;
		}
		$http.get(url).success(function(data,status){
			/*$http.get(path+'/aliment/total').success(function(data2,status){*/
				$scope.nutriments=angular.fromJson(data);
				/*$scope.nbAliments=data2;
			});*/
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
		loadNutriments();
	};

	loadNutriments();
}]);
/*
app.controller('formulaireAlimentsCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {
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
		$http({url:path+'/aliment/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
			$modalInstance.close();
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	loadCategorie();
}]);
*/
