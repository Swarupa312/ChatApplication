/**
 * 
 */
app.controller('UserController', function(UserService,$scope,$location){
	
	$scope.registerUser=function(){
		
		UserService.registerUser($scope.user).then(function(response){
			console.log(response.status)
			$location.path('/login')
			alert("Successfully registered")
		},function(response){
			console.log(response.status)
			console.log(response.data)
			error=response.data
			alert(error.message)
			$location.path('/signup')
		})
	}
	
$scope.userLogin=function(){
		
		UserService.loginUser($scope.user).then(function(response){
			console.log(response.status)
			$location.path('/home')
			
		},function(response){
			console.log(response.status)
			console.log(response.data)
			error=response.data
			alert(error.message)
			$location.path('/signup')
			
		})
	}
})