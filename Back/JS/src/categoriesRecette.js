app.controller("categoriesRecetteController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbCategories=0;
	$scope.currentPage=1;
	$scope.categories=[];
	$scope.filtre=undefined;

	var loadCategories=function(){
		var url=path+'/recettes/categories';
		$http.get(url).success(function(data,status){
				$scope.categories=angular.fromJson(data);
		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'recettes/formulaireCategorie.html',
			controller: 'formulaireCategorieRecetteCtrl',
			size: 'lg'
    		});
	};

	$scope.pageChanged=function(){
		loadCategories();
	};

	loadCategories();
}]);

app.controller('formulaireCategorieRecetteCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {


	$scope.save = function (){
		var data={nom:$scope.nom};
		$http({url:path+'/recettes/categories/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
			$modalInstance.close();
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
}]);
