/**
 * 
 */
app.factory('ForumService',function($http){
	var forumservice={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	forumservice.saveForum=function(forum)
	{
		return $http.post(MAINURL+"/saveforum",forum)
	}
	
	forumservice.getforumsWaiting=function(){

		return $http.get(MAINURL+"/getforumstatus/"+0)
	}
	
	forumservice.getforumsapproved=function(){
		return $http.get(MAINURL+"/getforumstatus/"+1)
	}
	
	forumservice.updateforum=function(forumlist){
	
		return $http.put(MAINURL+"/updateforum",forumlist)
	}
return forumservice;
})
