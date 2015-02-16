BodyWiredApp.controller('CalendarController', function($scope,$http,$location,uiCalendarConfig,UserService) {

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    $scope.eventSource = {};
    /* event source that contains custom events on the scope */
    $scope.events = [];
    /* event source that calls a function on every view switch */
    $scope.eventsF = function (start, end, timezone, callback) {
      var s = new Date(start).getTime() / 1000;
      var e = new Date(end).getTime() / 1000;
      var m = new Date(start).getMonth();
      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
      callback(events);
    };

    var loadPlanning=function(){
	$http.get(baseURL+"users/plannings/"+UserService.user.id)
        .success(function(data) {
		$scope.events.splice(0,$scope.events.length)
        	for(var p in data){
			var title=data[p].recette.nom;
			var className="";
			switch(data[p].repas){
				case 1: className+="matin"; break;
				case 2: className+="midi"; break;
				case 3: className+="soir"; break;
			}
			var date=new Date(parseInt(data[p].date));
			var day=date.getDate()<9?'0'+date.getDate():date.getDate();
			var month=date.getMonth()<9?'0'+(date.getMonth()+1):(date.getMonth()+1);
			$scope.events.push({title:title,start:date.getFullYear()+"-"+month+"-"+day,className:[className],recette:data[p]});
	    	}
	}).error(function(error) {
            Toast.error("Une erreur est survenue lors de la récuperation des favoris");
        });
    };
		

    $scope.calEventsExt = {
       color: '#f00',
       textColor: 'yellow',
       events: [ 

        ]
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function( date, jsEvent, view){
	$location.url("/recettes/"+date.recette.recette.id);
       	console.log(date,jsEvent,view);
    };
    /* alert on Drop */
     $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
       $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
      var canAdd = 0;
      angular.forEach(sources,function(value, key){
        if(sources[key] === source){
          sources.splice(key,1);
          canAdd = 1;
        }
      });
      if(canAdd === 0){
        sources.push(source);
      }
    };
    /* add custom event*/
    $scope.addEvent = function() {
      $scope.events.push({
        title: 'Open Sesame',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        className: ['openSesame']
      });
    };
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
    };
    
    /* Change View */
    $scope.renderCalender = function(calendar) {
      if(uiCalendarConfig.calendars[calendar]){
        uiCalendarConfig.calendars[calendar].fullCalendar('render');
      }
    };
     /* Render Tooltip */
    $scope.eventRender = function( event, element, view ) { 
        element.attr({'tooltip': event.title,
                     'tooltip-append-to-body': true});
    };
    /* config object */
    $scope.uiConfig = {
      calendar:{
        height: 250,
        defaultView: 'basicWeek',
        editable: true,
        header:{
          left: 'title',
          center: '',
          right: 'today prev,next'
        },
        lang:'fr',
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender
      }
    };

    $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];

	loadPlanning();
});
