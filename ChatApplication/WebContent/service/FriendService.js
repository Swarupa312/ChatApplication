/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	
	friendService.getSuggestedUsers=function()
	{	
		return $http.get(MAINURL+"/getsuggestedusers")
	}
	friendService.friendRequest=function(toId)
	{
		return $http.post(MAINURL+"/friendrequest/"+toId)	
	}
	
	friendService.getPendingRequests=function()
	{
		return $http.get(MAINURL+"/pendingrequests")
	}
	
	friendService.updateRequest=function(request)
	{
		return $http.post(MAINURL+"/updaterequest",request)
	}
	
	friendService.getUserDetails=function(fromId)
	{
		
		return $http.get(MAINURL+"/getuserdetails/"+fromId)
	}
	friendService.getlistOfFriends=function()
	{	
		return $http.get(MAINURL+"/getfriendlist")
	}
	
	return friendService;
})