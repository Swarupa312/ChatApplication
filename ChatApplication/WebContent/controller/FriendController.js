/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function getsuggestedUsers(){
		FriendService.getSuggestedUsers().then(function(response){
			console.log(response.status)
			$scope.suggesteduserslist=response.data
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
	})
	}
	
	getsuggestedUsers()
	getPendingRequests()
	
	
	function getPendingRequests(){
		FriendService.getPendingRequests().then(function(response){
			console.log(response.status)
			$scope.pendingrequestslist=response.data
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
	})
	}
	
	$scope.friendRequest=function(toId)
	{
		
		FriendService.friendRequest(toId).then(function(response){
			getsuggestedUsers()
			$location.path('/suggestedUsers')
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
})