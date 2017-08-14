/**
 * 
 */
app.controller('UserController', function(UserService,$scope,$location,$rootScope,$cookieStore){
	
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
			$rootScope.currentUser=response.data

			$cookieStore.put("currentUser",response.data)
			$location.path('/home')
			},function(response){
			console.log(response.status)
			console.log(response.data)
			error=response.data
			alert(error.message)
			$location.path('/signup')
			
		})
	}

$scope.userLogout=function(){

	UserService.userLogout().then(function(response){
		console.log(response.status)
		alert("logged out")
		delete $rootScope.currentUser
		$cookieStore.remove("currentUser")
		$location.path("/login")
	},function(response){
		console.log(response.status)
		console.log(response.data)
		$location.path("/signup")
	})
}



})