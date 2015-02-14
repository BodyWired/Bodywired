app.controller("categoriesController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbCategories=0;
	$scope.currentPage=1;
	$scope.categories=[];
	$scope.filtre=undefined;

	var loadCategories=function(){
		var url=path+'/categories/lister?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		if($scope.filtre!=undefined && $scope.filtre!=''){
			url+='&filtre='+$scope.filtre;
		}
		$http.get(url).success(function(data,status){
				$scope.categories=angular.fromJson(data);
		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireCategorie.html',
			controller: 'formulaireCategorieCtrl',
			size: 'lg'
    		});
	};

	$scope.pageChanged=function(){
		loadCategories();
	};

	loadCategories();
}]);

app.controller('formulaireCategorieCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {


	$scope.save = function (){
		var data={nom:$scope.nom,description:$scope.desc};
		$http({url:path+'/categories/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
			$modalInstance.close();
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
}]);
