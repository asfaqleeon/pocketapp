var app = angular.module('mapp');

app.controller('addLink',function($scope,$http,store){

    $scope.submit = function(){
        //var date = new Date($scope.selectedDate)
        //console.log(date);
        var linkUrl = {
            "url": $scope.url,
            "category": $scope.category
        };

        $http.post('http://localhost:3000/api/add',linkUrl,{
                headers: {
                    "x-access-token": store.get('token')
                }
            })
            .success(function(data,status,headers,config){
                console.log(data);
            })
            .error(function(data,status){
                console.log('add link error occurred: '+data);
            });

    };

});