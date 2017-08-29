/**
 * 
 */
app.factory('BlogPostService',function($http){
	var blogpostservice={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	blogpostservice.saveBlog=function(blog){
		return $http.post(MAINURL+"/saveblog",blog)
	}
	
	blogpostservice.getBlogPostWaiting=function(){

		return $http.get(MAINURL+"/getblog/"+0)
	}
	
	blogpostservice.getBlogPostapproved=function(){
		return $http.get(MAINURL+"/getblog/"+1)
	}
	blogpostservice.getBlogById=function(blogid){
		return $http.get(MAINURL+"/getblogbyid/"+blogid)
	}
	
	blogpostservice.updateBlog=function(blog){
		alert("in service")
		return $http.put(MAINURL+"/updateblog",blog)
	}
	
	
	return blogpostservice;
})