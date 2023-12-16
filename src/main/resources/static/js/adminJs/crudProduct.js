let host = "/rest";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $filter) {
    $scope.form = {}
    $scope.items = []
    $scope.categoryItems = []

    $scope.currentPage = 0;
    $scope.pageSize = 4;
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;

    $scope.numberOfPages = function () {
        return Math.ceil($scope.items.length / $scope.pageSize);
    }
    $scope.range = function (start, end) {
        var ret = [];
        if (!end) {
            end = start;
            start = 0;
        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        return ret;
    };
    var searchMatch = function (haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.indexOf(needle) !== -1;
    };

    // calculate page in place
    $scope.groupToPages = function () {
        $scope.pagedItems = [];

        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % $scope.pageSize === 0) {
                $scope.pagedItems[Math.floor(i / $scope.pageSize)] = [ $scope.filteredItems[i] ];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.pageSize)].push($scope.filteredItems[i]);
            }
        }
    };

    $scope.searchText = '';

    $scope.search = function() {
        // Kiểm tra nếu giá trị tìm kiếm không rỗng
        if ($scope.searchText.trim() !== '') {
            // Thực hiện tìm kiếm và gọi API
            var url = `${host}/products/find/${$scope.searchText}`;
            $http.get(url).then(resp => {
                $scope.items = resp.data;
                console.log('Search results:', $scope.items);
            }).catch(error => {
                console.log('Error during search:', error);
            });
        } else {
            // Nếu giá trị tìm kiếm rỗng, load tất cả sản phẩm
            $scope.load_all();
        }
    };



    $scope.reset = function () {
        $scope.form = {};
        //$scope.form.category = "default";
    }

    $scope.setPage = function () {
        $scope.currentPage = this.n;
    };

    $scope.load_all = function () {
        var url = `${host}/products`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })
        //fill category select
        var url = `${host}/categories`;
        $http.get(url).then(resp => {
            $scope.categoryItems = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.detail = function (id) {
        var url = `${host}/products/${id}`;
        $http.get(url).then(resp => {
            resp.data.date_import = new Date(resp.data.date_import)
            $scope.form = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/products`;
        $http.post(url, item).then(resp => {
            // item.available = item.quantity>0?'true':'false';
            $scope.items.push(item);
            $scope.load_all();
            $scope.reset();
            console.log("Success", resp)
            alert("Create successfully!");
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/products/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.id == $scope.form.id);
            $scope.items[index] = resp.data;
            $scope.load_all();
            // $scope.items[index].available = item[index].quantity>0?'true':'false';
            alert("Update successfully!");
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.delete = function (id) {
        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this product?") == true) {
            var url = `${host}/products/${id}`;
            $http.delete(url).then(resp => {
                var index = $scope.items.findIndex(item => item.id == $scope.form.id);
                $scope.items.splice(index, 1);
                $scope.load_all();
                console.log("Success", resp)
                alert("Delete successfully!");
            }).catch(error => {
                console.log("Error", error)
            });
        }
    }
    //load data to table
    $scope.load_all();
    $scope.reset();

});
// ctrl.$inject = ['$scope', '$filter'];
//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function () {
    return function (input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});