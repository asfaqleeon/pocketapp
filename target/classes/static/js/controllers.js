var app = angular.module('mapp');

function toolbar($scope,store) {
    $scope.isAuth = function () {
        if(store.get('token')){
            return true;
        }else{
            return false;
        }

    };

};

app.controller('login',function($scope,$http,store,dataService,$http,$location){

    $scope.login = function(){
        $scope.errs = false;

        var token = btoa('public key');

        var ename = btoa($scope.name);
        var epass = btoa($scope.pass);

        var auth = {
            "email": ename,
            "password": epass
        };

        var promise = dataService.loginService(auth,token);
        promise.then(function(resolve){
            //console.log('in ctrl: '+resolve);
            if(resolve!= 200){
                $scope.errs = true;
            }
            if(resolve == 200){
                $location.path('list');
            }
        },function(err){

        })
    };

});

app.controller('logout',function($scope,store,$location){
    store.remove('token');
    $location.path('login');
});

app.controller('signup',function($scope,$http,dataService){

    $scope.signup = function(){
        var ename = btoa($scope.name);
        var epass = btoa($scope.pass);

        var auth = {
            "email": ename,
            "password": epass
        };

        dataService.signupService(auth);
    };

});

app.controller('addLink',function($scope,$http,store,dataService){

    $scope.submit = function(){
        //var date = new Date($scope.selectedDate)
        //console.log(date);
        var linkUrl = {
            "url": $scope.url,
            "category": $scope.category
        };

        dataService.addLinkService(linkUrl);

    };

});

app.controller('allLink',function($scope,$http,$location,store,dataService){

    $scope.data = [];
    $scope.last=0;

    function grabAllByPage(){
        var promise = dataService.showAllLinks($scope.last);
        promise.then(function(resolve){
            $scope.data = $scope.data.concat(resolve);
            //console.log($scope.data);
        },function(err){
            //console.log(err);
        });
        $scope.last++;

    }

    grabAllByPage();

    $scope.scrolling = function(){
        grabAllByPage();
    };

    $scope.deleteItem = function (id){
        console.log(id);
        dataService.deleteLink(id);
        grabAllByPage();
    }

});
