let host = "/rest";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $filter) {
    $scope.form = {}
    $scope.items = []

    $scope.currentPage = 0;
    $scope.pageSize = 10;
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

    // init the filtered items
    $scope.search = function () {
        $scope.filteredItems = $filter('filter')($scope.items, function (item) {
            for(var attr in item) {
                if (searchMatch(item[attr], $scope.query))
                    return true;
            }
            return false;
        });
        // take care of the sorting order
        // if ($scope.sortingOrder !== '') {
        //     $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
        // }
        $scope.currentPage = 0;
        // now group by pages
        $scope.groupToPages();
    };

    $scope.search();


    $scope.reset = function () {
        $scope.form = {};
        //$scope.form.category = "default";
    }

    $scope.setPage = function () {
        $scope.currentPage = this.n;
    };

    $scope.load_all = function () {
        var url = `${host}/orders`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })

    }

    $scope.detail = function (id) {
        var url = `${host}/orders/${id}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/orders`;
        $http.post(url, item).then(resp => {
            // item.available = item.quantity>0?'true':'false';
            $scope.items.push(item);
            $scope.load_all();
            console.log("Success", resp)
            alert("Create successfully!");
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/orders/${$scope.form.id}`;
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
        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this order?") == true) {
            var url = `${host}/order/${id}`;
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

    // Hàm updateStatus để cập nhật trạng thái
    $scope.updateStatus = function(order, newStatus) {
        // Gọi API hoặc thực hiện xử lý cập nhật trạng thái ở đây
        // Ví dụ:
        // Cập nhật trạng thái mới vào đối tượng đơn hàng
        order.status = newStatus;

        // Gọi API để cập nhật trạng thái trên máy chủ
        $http.put('/rest/orders/' + order.id, { status: newStatus })
            .then(function(response) {
                // Xử lý phản hồi sau khi cập nhật thành công (nếu cần)
                console.log('Cập nhật trạng thái thành công', response);
            })
            .catch(function(error) {
                // Xử lý lỗi nếu có
                console.error('Lỗi khi cập nhật trạng thái', error);
            });
    };

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