/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies']);
app.config(function ($routeProvider){
	$routeProvider
	.when('/home',{
	templateUrl:'views/Home.html'	
	})
	
	.when('/aboutus',{
	templateUrl:'views/Aboutus.html'	
	})
	
	
	.when('/login',{
	controller:'UserController',
	templateUrl:'views/Login.html'	
	})
	
	
	
	.when('/blog',{
	templateUrl:'views/Blog.html'	
	})
	
	.when('/forums',{
	templateUrl:'views/Forums.html'	
	})

		.when('/signup',{
	controller:'UserController',
	templateUrl:'views/Signup.html'
	})
	
	app.run(function ($rootScope,$cookieStore,UserService,$location){
		if($rootScope.currentUser==undefined)
			$rootScope.currentUser=$cookieStore.get("currentUser")
			
			
	})
	
})
