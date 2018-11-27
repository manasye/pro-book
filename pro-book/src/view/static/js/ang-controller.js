let browseApp = angular.module('browseApp', []);

browseApp.controller('mainController', [
    '$scope',
    '$http',
    function($scope, $http) {
        $scope.searchInput = '';
        $scope.isLoading = false;
        $scope.searched = false;
        $scope.books = [];
        $scope.searchBook = () => {
            $scope.isLoading = true;
            $scope.searched = false;
            $http
                .get(`/search-book?title=${$scope.searchInput}`)
                .success(res => {
                    $scope.books = res.bookList;
                    $scope.isLoading = false;
                    $scope.searched = true;
                    $scope.books.forEach((part, index) => {
                        $scope.books[index].price = numeral(
                            $scope.books[index].price
                        ).format('0,0');
                    });
                })
                .error((data, status) => {
                    $scope.isLoading = false;
                });
        };
    }
]);
