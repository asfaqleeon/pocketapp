var app = angular.module('mapp');

app.directive('toolbar',function () {
    return {
        templateUrl: '/partial/toolbar.html',
        controller: toolbar
    }
});

