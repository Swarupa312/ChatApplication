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
	
	
	
	.when('/writeblog',{
		controller:'BlogPostController',
	templateUrl:'views/BlogPost.html'	
	})
	
	.when('/viewblog',{
		controller:'BlogPostController',
	templateUrl:'views/ShowBlog.html'	
	})
	
	
	.when('/getblogbyidwaiting/:blogid',{
		controller:'BlogPostDetailsController',
	templateUrl:'views/ApproveBlog.html'	
	})
	
	.when('/getblogbyidapproved/:blogid',{
		controller:'BlogPostDetailsController',
	templateUrl:'views/BlogComment.html'	
	})
	
	.when('/forums',{
	templateUrl:'views/Forums.html'	
	})

		.when('/signup',{
	controller:'UserController',
	templateUrl:'views/Signup.html'
	})
	
	.when('/edituser',{
		controller:'UserController',
		templateUrl:'views/EditProfile.html'
	})
	
	.when('/savejob',{
		controller:'JobController',
		templateUrl:'views/SaveJob.html'
	})
	
	.when('/showjob',{
		controller:'JobController',
		templateUrl:'views/Showjob.html'
	})
	
	.when('/profilepic',{
		templateUrl:'views/ProfilePicture.html'
	})
	
})


app.run(function ($rootScope,$cookieStore,UserService,JobService,$location){
		if($rootScope.currentUser==undefined)
			$rootScope.currentUser=$cookieStore.get("currentUser")
			
			
	})
