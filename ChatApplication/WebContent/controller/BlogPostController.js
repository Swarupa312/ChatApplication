/**
 * 
 */
app.controller('BlogPostController',function(BlogPostService,$scope,$location){
	//TO get the blogs waiting for approval
	BlogPostService.getBlogPostWaiting().then(function(response){
	$scope.blogwaiting=response.data;
		
	},function(response){
		$location.path('/login')
	})
	
	//To get the blogs approved
	BlogPostService.getBlogPostapproved().then(function(response){
	$scope.blogapproved=response.data;
		
	},function(response){
		$location.path('/login')
	})
	
	
	
	
	
	$scope.saveBlog=function(){
	
		BlogPostService.saveBlog($scope.blog).then(function(response){
			console.log(response.status)
			console.log(response.data)
			$location.path('/home')
			alert("Saved successfully")
			
		},function(response){
			
			console.log(response.status)
			error=response.data
			alert(error.message)
			$location.path('/writeblog')
		})
	}
	
	
})