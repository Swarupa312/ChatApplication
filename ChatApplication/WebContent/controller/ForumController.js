/**
 * 
 */
app.controller('ForumController',function($scope,$location,ForumService){

	
	ForumService.getforumsapproved().then(function(response){
		$scope.showapprovedforum=response.data;
			
		},function(response){
			$location.path('/login')
		})
		
	ForumService.getforumsWaiting().then(function(response){
	$scope.showWaitingforum=response.data;
		
	},function(response){
		$location.path('/login')
	})
	
	/*$scope.getForumComments=function(forumid)
	{
		alert("in controller")
	ForumService.getForumsComments(forumid).then(function(response){
	$scope.comments=response.data;
		
	},function(response){
		$location.path('/login')
	})
	
	}*/
	
	/*$scope.saveForumcomment=function(){
		//alert(forumdetails)
		ForumService.saveForumcomment(forumid).then(function(response){
			alert("Saved successfully")
		},function(response){
			console.log(response.status)
			if(response.status==401)
			$location.path('/login')
			$scope.error=response.data
			console.log(error.message)
		})
	}*/
	
	$scope.saveForum=function()
{
		ForumService.saveForum($scope.forum).then(function(response){
			alert("saved")
		},function(response){
			console.log(response.status)
			$location.path('/forums')
		})		
}
	
$scope.updateForum=function(forumlist,forumstatus){
		
	forumlist.forumstatus=forumstatus
	ForumService.updateforum(forumlist).then(function(response){
			$location.path('/forums')
			
			
		},function(response){
			console.log(response.data)
			alert("unsuccessful")
			if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				console.log(error.message)
				
		})
	}

$scope.askquestionenable=function()
{
$scope.question=true	
}
/*$scope.forumDetails=function(forumid){
	
ForumService.forumDetails(forumid).then(function(response){
	console.log(response.data)
	$scope.forumdetails=response.data
	$location.path('/forumdetails')
},function(response){
	error=response.data
	alert(error.message)
	
})*/
//}
	



})
