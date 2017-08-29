/**
 * 
 */
app.controller('BlogPostDetailsController',function($scope,$location,$routeParams,BlogPostService){
	
	var blogid=$routeParams.blogid
	$scope.blog=BlogPostService.getBlogById(blogid).then(function(response){
	$scope.blog=response.data
	console.log(response.status)
	},function(response){
		console.log(response.status)
		if(response.status==401)
			$location.path('/login')
	})
	
	$scope.updateBlog=function(){
		BlogPostService.updateBlog($scope.blog).then(function(response){
			alert("Updated Successsfully")
			$location.path('/viewblog')
		},function(response){
			console.log(response.data)
			alert("unsuccessful")
			if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				console.log(error.message)
				$loction.path('/')
		})
	}
})