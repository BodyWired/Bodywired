app.controller("alimentsController",['$scope','$modal',function($scope,$modal){
	$scope.nbAliments=0;
	$scope.currentPage=1;
	$scope.aliments=[];

	var loadAliments=function(){
		$scope.aliments=[
			{id:1,
			nom:'carotte',
			categorie:'Légume',
			}
		];
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'formulaireAliments.html',
			controller: 'formulaireAlimentsCtrl',
			size: 'lg'
    		});
	};
}]);

app.controller('formulaireAlimentsCtrl', function ($scope, $modalInstance) {
	$scope.nutrimentsUnused=angular.copy(nutriments);
	$scope.nutrimentsUsed=[];
	$scope.nutrimentsUnusedSelected=[];
	$scope.nutrimentsUsedSelected=[];
	$scope.categories=categorieAliments;

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
		//TODO
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});
