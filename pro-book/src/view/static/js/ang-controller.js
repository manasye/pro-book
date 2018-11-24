let browseApp = angular.module('browseApp', []);

browseApp.controller('mainController', [
    '$scope',
    '$http',
    function($scope, $http) {
        $scope.searchInput = '';
        let baseURL = 'http://cube-api.apps.playcourt.id/';
        $scope.searchBook = () => {
            $http
                .get(`${baseURL}activities/content`)
                .success(res => {
                    console.log($scope.searchInput);
                    console.log(res);
                    $scope.books = res;
                })
                .error((data, status) => {
                    console.log(data);
                });
        };
    }
]);
