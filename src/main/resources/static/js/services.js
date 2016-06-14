var app = angular.module('mapp');

app.service('dataService',function ($http,store) {
    var prodUrl = 'https://pocketapp.herokuapp.com';
    var devUrl = 'http://localhost:3000';

    var mode = 'prod'; // dev / prod
    var url;

    if(mode == 'dev'){
        url = devUrl;
    }else if(mode == 'prod'){
        url = prodUrl;
    }

    this.loginService = function (auth,token) {

        $http.post(url+'/api/login',auth,{
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

    this.signupService = function (auth) {
        $http.post(url+'/api/signup',auth)
            .success(function(data,status){
                console.log(data);
            })
            .error(function(data,status){
                console.log('login error occurred: '+data);
            });

    };

    this.addLinkService = function (linkUrl) {
        $http.post(url+'/api/add',linkUrl,{
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


    this.showAllLinks = function () {
        var links = [];
        $http.get(url+'/api/list',{
                headers: {
                    "x-access-token": store.get('token')
                }
            })
            .success(function(data){
                data.forEach(function (d) {
                    links.push(d);
                });
            })
            .error(function(err){
                console.log(err);
            });

        return links;
    };
    
    this.deleteLink = function (id) {
        $http.delete(url+'/api/delete',{
                params: {
                    "id": id
                }
            })
            .success(function(data){
                console.log(data);
            })
            .error(function(err){
                console.log(err);
            });
    };

});