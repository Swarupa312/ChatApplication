/**
 * 
 */
app.controller('BlogPostDetailsController',function($scope,$location,$routeParams,$rootScope,BlogPostService){
	
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
			
			$location.path('/viewblog')
		},function(response){
			console.log(response.data)
			alert("unsuccessful")
			if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				console.log(error.message)
				
		})
	}
		$scope.savecomment=function(){
			
			$scope.blogComment.blogpost=$scope.blog
			BlogPostService.addComment($scope.blogComment).then(function(response){
				alert("Saved successfully")
			},function(response){
				console.log(response.status)
				if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				console.log(error.message)
			})
		}
		
		 /*function showComments(){
			
			BlogPostService.showComments(blogid).then(function(response){
				console.log(response.data)
				console.log(response.status)
				$scope.blogcomments=response.data
				
				
			},function(response)
			{
				console.log(response.status)
				if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				
			})
		}*/
		
		$scope.showComments=function(blogid){
			
			BlogPostService.showComments(blogid).then(function(response){
				console.log(response.data)
				console.log(response.status)
				$scope.blogcomments=response.data
				
				
			},function(response)
			{
				console.log(response.status)
				if(response.status==401)
				$location.path('/login')
				$scope.error=response.data
				
			})
		}
		 //showComments();
})