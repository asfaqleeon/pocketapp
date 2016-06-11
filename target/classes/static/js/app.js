var app = angular.module('mapp',['ui.bootstrap','ui.router','ngAnimate','mgcrea.ngStrap','angular-storage']);

app.config(function($stateProvider,$urlRouterProvider){
    $urlRouterProvider.otherwise('login');

    $stateProvider
        .state('signup',{
            url: '/signup',
            templateUrl:'/partial/signup.html',
            controller: 'signup'
        })
        .state('login',{
            url: '/login',
            templateUrl:'/partial/login.html',
            controller: 'login'
        })
        .state('logout',{
            url: '/login',
            templateUrl:'/partial/login.html',
            controller: function(store){
                store.remove('token');
            }
        })
        .state('addLink',{
            url: '/addLink',
            templateUrl:'/partial/add-link.html',
            controller: 'addLink'
        })
        .state('list',{
            url: '/list',
            templateUrl:'/partial/link.html',
            controller: 'allLink'
        })

});