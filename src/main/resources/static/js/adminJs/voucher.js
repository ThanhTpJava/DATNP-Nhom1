let host = "/rest";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $filter) {
    $scope.form = {}
    $scope.items = []
    $scope.spins = []

    $scope.currentPage = 0;
    $scope.pageSize = 4;
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;

    $scope.isVoucherCodeExists = function(voucherCode) {
        // Check if voucherCode exists in the list of items
        return $scope.items.some(function(item) {
            return item.voucherCode === voucherCode;
        });
    };


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
        var url = `${host}/voucher`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })

    }

    $scope.detail = function (id) {
        var url = `${host}/voucher/${id}`;
        $http.get(url).then(resp => {
            resp.data.startDate = new Date(resp.data.startDate);
            resp.data.endDate = new Date(resp.data.endDate);
            $scope.form = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })
    }



    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/voucher`;
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
        var url = `${host}/voucher/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.id == $scope.form.id);
            $scope.items[index] = resp.data;
            $scope.load_all();
            // $scope.items[index].available = item[index].quantity>0?'true':'false';
            alert("Update successfully!");
        }).catch(error => {
            alert("Please delete the lucky spin voucher to update!");
            console.log("Error", error)
        });
    }

    $scope.delete = function (id) {
        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this product?") == true) {
            var url = `${host}/voucher/${id}`;
            $http.delete(url).then(resp => {
                var index = $scope.items.findIndex(item => item.id == $scope.form.id);
                $scope.items.splice(index, 1);
                $scope.load_all();
                console.log("Success", resp)
                alert("Delete successfully!");
            }).catch(error => {
                alert("Please delete the lucky spin voucher to update!");
                console.log("Error", error)
            });
        }
    }

    $scope.validateEndDate = function() {
        if (new Date($scope.form.endDate) <= new Date($scope.form.startDate)) {
            $scope.myForm.endDate.$setValidity('dateError', false);
        } else {
            $scope.myForm.endDate.$setValidity('dateError', true);
        }
    };

//Lucky Spin


    $scope.load_allSpin = function () {
        var url = `${host}/luckySpin/getAll`;
        $http.get(url).then(resp => {
            $scope.spins = resp.data;
            $scope.IsStatus();
            if($scope.IsStatus() === true){
                console.log("true");
                var url = `${host}/statusSpin/true`;
                $http.put(url).then(resp => {
                    $scope.statusSpinNow = true;
                    console.log(resp.data);
                });
            }
            else {
                var url = `${host}/statusSpin/false`;
                $http.put(url).then(resp => {
                    $scope.statusSpinNow = false;
                    console.log(resp.data);
                });
                console.log("false")
            }
            $scope.getCountVoucher= $scope.spins.length;

            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        })

    }

    $scope.createSpin = function (voucherCode) {
        var spin = { voucherCode: voucherCode }; // Include other properties if needed
        var url = `${host}/luckySpin/${voucherCode}`;
        $http.post(url, spin).then(resp => {
            $scope.spins.push(spin);
            $scope.load_allSpin();
            $scope.reset();
            console.log("Success", resp);
            alert("Create successfully!");
        }).catch(error => {
            alert('Duplicate voucher cannot be added')
            console.log("Error", error);
        });
    };



    $scope.updateSpin = function () {
        var spin = angular.copy($scope.form);
        var url = `${host}/luckySpin/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.spins.findIndex(item => spin.id == $scope.form.id);
            $scope.spins[index] = resp.data;
            $scope.load_allSpin();
            // $scope.items[index].available = item[index].quantity>0?'true':'false';
            alert("Update successfully!");
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.deleteSpin = function (id) {
        console.log("Deleting spin with id:", id);

        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this product?") == true) {
            var url = `${host}/luckySpin/${id}`;
            $http.delete(url).then(resp => {
                // var index = $scope.items.findIndex(spin => spins.id == $scope.form.id);
                // $scope.spins.splice(index, 1);
                $scope.load_allSpin();
                console.log("Success", resp)
                alert("Delete successfully!");
            }).catch(error => {
                console.log("Error", error)
            });
        }
    }

    $scope.IsStatus = function() {
        // Assuming $scope.spins contains the list of vouchers


        $scope.totalRate = $scope.spins.reduce(function(sum, spin) {
            return sum + (spin.rate || 0);
        }, 0);

        // Calculate the total percentage of vouchers
        var totalRate = $scope.spins.reduce(function(sum, spin) {
            return sum + (spin.rate || 0);
        }, 0);

        // Check if the total percentage is exactly 100%
        return totalRate === 100;

        if (!$scope.spins || $scope.spins.length < 4 || $scope.spins.length > 10) {
            // If the number of vouchers is not in the range [4, 10]
            console.log("ok")
            return false;
        }
    };

    $scope.totalRate = $scope.spins.reduce(function(sum, spin) {
        return sum + (spin.rate || 0);
    }, 0);

    $scope.rulesOpen = function() {
        $scope.isRulesOpen = true;
    }

    $scope.rulesClose = function() {
        $scope.isRulesOpen = false;
    }


    //load data to table
    $scope.load_all();
    $scope.load_allSpin();
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