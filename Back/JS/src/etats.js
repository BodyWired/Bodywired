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

	var openModal=function(data){
		var modalInstance = $modal.open({
			templateUrl: 'aliments/formulaireEtat.html',
			controller: 'formulaireEtatCtrl',
			size: 'lg',
			resolve:{
				data : function(){
					return data;
				}
			}
    		});
	};

	$scope.deleteEtat=function(id){
		var url=path+'/declinaison/etat/'+id;
		$http.delete(url).success(function(data,status){
			loadEtats();
		});
	};

	$scope.updateEtat=function(etat){
		openModal(etat);
	};

	$scope.add=function(){
		openModal(undefined);
	};

	$scope.pageChanged=function(){
		loadEtats();
	};

	loadEtats();
}]);

app.controller('formulaireEtatCtrl',['$scope','$http','$modalInstance','data', function ($scope,$http, $modalInstance,data) {
	var globalData=data;

	if(data!=undefined){
		$scope.nom=data.nom;
	}

	$scope.save = function (){
		var data={nom:$scope.nom,id:globalData.id};
		if(globalData!=undefined){
			$http({url:path+'/declinaison/etat/modifier',method:'PUT',data:angular.toJson(data)}).success(function(data,status){
				$modalInstance.close();
			});
		}
		else{
			$http({url:path+'/declinaison/etat/ajouter',method:'POST',data:angular.toJson(data)}).success(function(data,status){
				$modalInstance.close();
			});
		}
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
}]);
