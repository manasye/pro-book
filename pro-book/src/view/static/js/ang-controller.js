let browseApp = angular.module('browseApp', []);

browseApp.controller('mainController', [
    '$scope',
    '$http',
    function($scope, $http) {
        $scope.searchInput = '';
        $scope.isLoading = false;
        $scope.searched = false;
        let baseURL = 'http://localhost:3333';
        $scope.searchBook = () => {
            $scope.isLoading = true;
            $scope.searched = false;
            $http
                .get(`${baseURL}?title=${$scope.searchInput}`)
                .success(res => {
                    console.log($scope.searchInput);
                    console.log(res);
                    $scope.books = res.bookList;
                    $scope.isLoading = false;
                    $scope.searched = true;
                })
                .error((data, status) => {
                    $scope.isLoading = false;
                    console.log(data);
                });
        };
    }
]);
