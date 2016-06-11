var app = angular.module('mapp');

app.controller('signup',function($scope,$http){

    $scope.signup = function(){
        var ename = btoa($scope.name);
        var epass = btoa($scope.pass);

        var auth = {
            "email": ename,
            "password": epass
        };

        $http.post('https://pocketapp.herokuapp.com/api/signup',auth)
            .success(function(data,status){
                console.log(data);
            })
            .error(function(data,status){
                console.log('login error occurred: '+data);
            });
    };

});
