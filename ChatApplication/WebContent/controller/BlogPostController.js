/**
 * 
 */
app.controller('BlogPostController',function(BlogPostService,$scope,$location){
	
	$scope.saveBlog=function(){
		alert("controller")
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