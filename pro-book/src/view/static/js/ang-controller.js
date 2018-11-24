let browseApp = angular.module('browseApp', []);

browseApp.controller('mainController', [
    '$scope',
    '$http',
    function($scope, $http) {
        $scope.searchInput = '';
        $scope.isLoading = false;
        let baseURL = 'http://cube-api.apps.playcourt.id/';
        $scope.searchBook = () => {
            $scope.isLoading = true;
            $http
                .get(`${baseURL}mentorship`)
                .success(res => {
                    console.log($scope.searchInput);
                    console.log(res);
                    $scope.books = res;
                    $scope.isLoading = false;
                })
                .error((data, status) => {
                    $scope.isLoading = false;
                    console.log(data);
                });
        };
    }
]);
