/**
 * 
 */
app.factory('BlogPostService',function($http){
	var blogpostservice={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	blogpostservice.saveBlog=function(blog){
		return $http.post(MAINURL+"/saveblog",blog)
	}
	return blogpostservice;
})