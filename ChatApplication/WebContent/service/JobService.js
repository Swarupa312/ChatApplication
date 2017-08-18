/**
 * 
 */
app.factory('JobService',function($http){
	var jobservice={};
	var MAINURL="http://localhost:8087/ChatAppBackend";
	jobservice.saveJob=function(job){
		
		 return $http.post(MAINURL+"/savejob",job)
	 }
	
	jobservice.showJob=function(){
		 return $http.get(MAINURL+"/showjob")
	 }
	
	jobservice.getJobById=function(jid){
		
		return $http.get(MAINURL+"/getjobbyid/"+jid)
	}
	return jobservice;
})