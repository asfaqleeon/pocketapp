var app = angular.module('mapp');

app.controller('allLink',function($scope,$http,$location,store){
    grabAllByPage();
    
    function grabAllByPage(){
        $http.get('https://pocketapp.herokuapp.com/api/list',{
                headers: {
                    "x-access-token": store.get('token')
                }
            })
            .success(function(data){
                $scope.data = data;
            })
            .error(function(err){
                console.log(err);
            });
    }

    $scope.deleteItem = function (id){
        console.log(id);
        $http.delete('https://pocketapp.herokuapp.com/api/delete',{
                params: {
                    "id": id
                }
            })
            .success(function(data){
                console.log(data);
                grabAllByPage();
            })
            .error(function(err){
                console.log(err);
            });
    }

});
