/**
 * 
 */
app.controller('FriendDetailController',function($scope,$routeParams,$location,FriendService){
	
	var fromId=$routeParams.fromId
	FriendService.getUserDetails(fromId).then(function(response){
		$scope.userfriend=response.data
		
	},function(response){
		alert("failed")
		$location.path('/pendingrequests')
	})
})