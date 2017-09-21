/**
 * 
 */
app.controller('ForumDetailsController',function($scope,$location,$routeParams,ForumService){
	
	
	
	
	var forumid=$routeParams.forumid
	$scope.forum=ForumService.getForumById(forumid).then(function(response){
	$scope.forum=response.data
	console.log(response.status)
	},function(response){
		console.log(response.status)
		if(response.status==401)
			$location.path('/login')
	})	
	
	getForumComments(forumid)
	$scope.saveForumcomment=function(){
	
		$scope.ForumComment.forum=$scope.forum
		ForumService.saveForumcomment($scope.ForumComment).then(function(response){
			alert("Saved successfully")
		},function(response){
			console.log(response.status)
			if(response.status==401)
			$location.path('/login')
			$scope.error=response.data
			console.log(error.message)
		})
	}
	
	function getForumComments(forumid)
	{
		
	ForumService.getForumsComments(forumid).then(function(response){
	$scope.comments=response.data;
		
	},function(response){
		$location.path('/login')
	})
	
	}
})
	
	

