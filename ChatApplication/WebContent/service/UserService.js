/**
 * 
 */
app.factory('UserService',function($http){
	var userservice={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	userservice.registerUser=function(user){
		
		 return $http.post(MAINURL+"/registeruser",user)
	 }
	
	userservice.loginUser=function(user){
		
		 return $http.post(MAINURL+"/loginUser",user)
	 }
	
	userservice.userLogout=function(){
		
		 return $http.post(MAINURL+"/logotUser")
	 }
	
	return userservice;
})