/**
 * 
 */
var app=angular.module("app",['ngRoute']);
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
	
	.when('/logout',{
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
	
})
