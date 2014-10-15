app.controller("menusController",['$scope','$modal',function($scope,$modal){
	$scope.nbMenus=0;
	$scope.currentPage=1;
	$scope.menus=[];

	var loadAliments=function(){
		$scope.menus=[
			{id:1,
			nom:'boeuf carotte',
			categorie:'plat',
			}
		];
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'formulaireMenus.html',
			controller: 'formulaireMenusCtrl',
			size: 'lg'
    		});
	};

	loadAliments();
}]);

app.controller('formulaireMenusCtrl', function ($scope, $modalInstance) {
	
});
