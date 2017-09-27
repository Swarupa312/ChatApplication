/**
 * 
 */
app.controller('JobController', function(JobService,$scope,$location,$rootScope){
	$scope.showjob=false
	function showJob(){
		
		JobService.showJob().then(function(response){
		$scope.jobs=response.data;
		
		},function(response){
		console.log(response.status)
		$location.path('/home')
		error=response.data
		alert(error.message)
		})
	}
	
	
	
	$scope.saveJob=function(){
		
		JobService.saveJob($scope.job).then(function(response){
			console.log(response.status)
			$location.path('/home')
			alert("Successfully Inserted")
		},function(response){
			console.log(response.status)
			console.log(response.data)
			error=response.data
			alert(error.message)
			$location.path('/login')
		})
}
	
	$scope.getJobById=function(jid){
		$scope.showjob=true 	//to show the job details
		JobService.getJobById(jid).then(function(response){
			console.log(response.data)
			$scope.jobid=response.data
			$location.path('/showjob')
		},function(response){
			error=response.data
			alert(error.message)
		})
		
	}
	
	
	showJob()
})