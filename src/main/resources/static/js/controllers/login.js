var app = angular.module('mapp');

app.controller('login',function($scope,$http,store){

    $scope.login = function(){

        var token = btoa('public key');

        var ename = btoa($scope.name);
        var epass = btoa($scope.pass);

        var auth = {
            "email": ename,
            "password": epass
        };

        $http.post('http://localhost:3000/api/login',auth,{
                headers: {
                    "x-access-token": token
                }
            })
            .success(function(data,status,headers,config){
                var header = headers('mkey');

                store.set('token',header);

                console.log('login response header: '+header);
                console.log(data);
            })
            .error(function(data,status){
                console.log('login error occurred: '+data);
            });
    };

});