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

app.controller('login',function($scope,$http,store,dataService){

    $scope.login = function(){

        var token = btoa('public key');

        var ename = btoa($scope.name);
        var epass = btoa($scope.pass);

        var auth = {
            "email": ename,
            "password": epass
        };

        dataService.loginService(auth,token);
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

    function grabAllByPage(){
        $scope.data = dataService.showAllLinks();
    }

    $scope.scrolling = grabAllByPage();

    $scope.deleteItem = function (id){
        console.log(id);
        dataService.deleteItem(id);
        grabAllByPage();
    }

});
