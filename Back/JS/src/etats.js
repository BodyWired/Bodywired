app.controller("etatsController",['$scope','$http','$modal',function($scope,$http,$modal){
	$scope.nbEtats=0;
	$scope.currentPage=1;
	$scope.etats=[];
	$scope.filtre=undefined;

	var loadEtats=function(){
		var url=path+'/declinaison/etat/lister?offset='+(($scope.currentPage-1)*10)+'&limite=10';
		if($scope.filtre!=undefined && $scope.filtre!=''){
			url+='&filtre='+$scope.filtre;
		}
		$http.get(url).success(function(data,status){
				$scope.etats=angular.fromJson(data);
		});
	};

	$scope.add=function(){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireEtat.html',
			controller: 'formulaireEtatCtrl',
			size: 'lg'
    		});
	};

	$scope.pageChanged=function(){
		loadEtats();
	};

	loadEtats();
}]);

app.controller('formulaireEtatCtrl',['$scope','$http','$modalInstance', function ($scope,$http, $modalInstance) {
	$scope.save = function (){
		var data={nom:$scope.nom};
		$http({url:path+'/declinaison/etat/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
			$modalInstance.close();
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
}]);
