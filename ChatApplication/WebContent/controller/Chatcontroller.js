/**
 * 
 */

app.controller('Chatcontroller', ['$rootScope' ,'$scope', 'socket', function($rootScope ,$scope, socket) {
    alert('entering chat controller')
    $scope.chats = [];
    $scope.stompClient = socket.stompClient;
    $scope.users=[]
    $scope.$on('sockConnected', function(event, frame) {
    	alert('sockconnected')
        $scope.userName=$rootScope.currentUser.username;
        $scope.stompClient.subscribe("/topic/join", function(message) //response from server of subscription
        		{
        	
            user = JSON.parse(message.body);	//Subscribed User name
            console.log(user)
           
            if(user != $scope.userName && $.inArray(user, $scope.users) == -1)  //If the user is in array or not
            {
                $scope.addUser(user);
                console.log(user)
                $scope.latestUser = user;
                $scope.$apply();
                $('#joinedChat').fadeIn(100).delay(10000).fadeOut(200);
            }
            
        });
        
        //To Subscribe with server
        $scope.stompClient.subscribe('/app/join/'+$scope.userName, function(message) {
        
            $scope.users = JSON.parse(message.body);	//To get the online Users list
        	
            $scope.$apply();
        });
        
    });

    $scope.sendMessage = function(chat) {
        chat.from = $scope.userName;
      
        $scope.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        $rootScope.$broadcast('sendingChat', chat);
        $scope.chat.message = '';

    };

    $scope.capitalize = function(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    };
 
    $scope.addUser = function(user) {
        $scope.users.push(user);
        $scope.$apply(); 	//To apply the changes
    };
 
    
    
    
    
    
    
    $scope.$on('sockConnected', function(event, frame) {
        $scope.userName=$rootScope.currentUser.username;
  
        $scope.user=$rootScope.currentUser.username;
       
        //The chat with particular user
        $scope.stompClient.subscribe( "/queue/chats/"+$scope.userName, function(message) {
        	
            $scope.processIncomingMessage(message, false);
        });
        
        //the chat with all users
        $scope.stompClient.subscribe("/queue/chats", function(message) {
        	
            $scope.processIncomingMessage(message, true);
        });
        
        
    });

    $scope.$on('sendingChat', function(event, sentChat) {
        chat = angular.copy(sentChat);
        chat.from = 'Me';
        chat.direction = 'outgoing';
        $scope.addChat(chat);
    });

    $scope.processIncomingMessage = function(message, isBroadcast) {
        message = JSON.parse(message.body);
        message.direction = 'incoming';
        if(message.from != $scope.userName) {
        	$scope.addChat(message);
            $scope.$apply(); // 
        }
    };

 
    $scope.addChat = function(chat) {
        $scope.chats.push(chat);
    };
 
 
}]);