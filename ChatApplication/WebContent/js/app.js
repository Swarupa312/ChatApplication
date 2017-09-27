/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies']);
app.config(function ($routeProvider){
	$routeProvider
	.when('/home',{
	templateUrl:'views/Home.html',
	controller:'BlogPostController'
	})
	
	.when('/contactus',{
	templateUrl:'views/ContactUs.html'	
	})
	
	.when('/aboutus',{
	templateUrl:'views/AboutUs.html'	
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
	
	.when('/suggestedUsers',{
		controller:'FriendController',
		templateUrl:'views/SuggestedUsers.html'
	})
	
	.when('/pendingrequests',{
		controller:'FriendController',
		templateUrl:'views/PendingRequests.html'
	})
	
	.when('/userdetail/:fromId',{
		controller:'FriendDetailController',
		templateUrl:'views/UserDetail.html'
	})
	
	.when('/friendlist',{
		controller:'FriendController',
		templateUrl:'views/FriendList.html'
	})
	
	 .when('/chat',{
		templateUrl:'views/Chat.html',
		controller:'Chatcontroller'
	})
	
	.when('/forums',{
	controller:'ForumController',
	templateUrl:'views/Forum.html'
	})
	
	.when('/approveforum',{
		controller:'ForumController',
		templateUrl:'views/ForumApprove.html'
		})
		
	.when('/getforumbyid/:forumid',{
		controller:'ForumDetailsController',
		templateUrl:'views/ForumDetails.html'
	})
})


app.run(function ($rootScope,$cookieStore,UserService,JobService,$location){
		if($rootScope.currentUser==undefined)
			$rootScope.currentUser=$cookieStore.get("currentUser")
			
			
			$rootScope.userLogout=function(){

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

	
	
