user.controller('userController',function($scope,$http){
	var urlBase=window.location.pathname;
	$scope.selected = {};
	$scope.toggle=true;
	$scope.statuses=['Active','Inactive'];
	$scope.sortType= 'name';       // set the default sort type
	$scope.sortReverse= false;     // set the default sort order
	$scope.searchUser= '';         // set the default search/filter term
	$http.defaults.headers.post["Content-Type"] = "application/json";
	
	//get all users and display initially
	$http.get(urlBase+"users")
		.success(function(data){
			 $scope.users = data;
		});
	
	$scope.addUser = function addUser() {
		if($scope.user.firstName=="" || $scope.user.lastName=="" || $scope.user.email == "" || $scope.user.mobile == ""|| $scope.user.status == ""){
			alert("Insufficient Data! Please provide all * marked values !!");
		}
		else if($scope.user.mobile.length>10){
			alert("Please enter mobile number 10-digits only!! ");
		}
		else{
		    //$http.post(urlBase + 'users/insert/'+$scope.user)
			//$http.post(urlBase + 'users/insert/'+$scope.user.firstName+'/'+$scope.user.lastName+'/'+$scope.user.email+'/'+$scope.user.mobile+'/'+$scope.user.status+'/'+$scope.user.city+'/'+$scope.user.state)
			
			$http.post(urlBase + 'users/insert/'+$scope.user.firstName+'/'+$scope.user.lastName+'/'+$scope.user.email+'/'+$scope.user.mobile+'/'+$scope.user.status)
		 	.success(function(data) {
			 $scope.users = data;	
			 $scope.user.lastName="";
			 $scope.user.email="";
			 $scope.mobile="";
			 $scope.status="";
			 $scope.toggle='!toggle';	 		 
		    });
			}
		};
					  
		$scope.archiveUser = function archiveUser(id){
			$http.post(urlBase+'users/archive/'+id)
			.success(function(data) {
				$scope.users = data;
			});
			
		};
		
		//editing inline
		$scope.getTemplate = function (user) {
		 if (user.id === $scope.selected.id){
			 	return 'edit';
			 }
			  else return 'display';	};
			
		$scope.editUser = function (user) {
				 $scope.selected = angular.copy(user);
		 };
		 
		 $scope.reset = function () {
			 $scope.selected = {};
		 };

	    //update the details of the user  
	    $scope.updateUser=function(user){
	    	if(user.firstName=='' || user.lastName=='' || user.email=='' || user.mobile==''){
	    		alert("Please enter the new values !!!");
	    	}else{	    		
	    		$http.post(urlBase+'users/update/'+user.id+'/'+user.firstName+'/'+user.lastName+'/'+user.email+'/'+user.mobile+'/'+user.creation_time+'/'+user.status)
				.success(function(data) {
					$scope.users = data;
					 $scope.selected = {};
				});
	    	}	
	    };	
	});
	