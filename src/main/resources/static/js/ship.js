// document.querySelector('select[name="role"]').addEventListener('change', function() {
//     localStorage.setItem('selectedRole', this.value);
// });

// Đọc giá trị từ Local Storage khi trang được tải lại
// var selectedRole = localStorage.getItem('selectedRole');
// if (selectedRole) {
//     document.querySelector('select[name="role"]').value = selectedRole;
// }

var app = angular.module("app", []);


app.controller("ctrl", function($scope, $http, $timeout) {

    $scope.isOpenAccDetail = false;

    $scope.isCheckAdmin = false;
    $scope.isCheckSale = false;
    $scope.isCheckStock = false;

    $scope.isPopupOpen = false;

    $scope.isAuthPopupOpen = false;

    $scope.isUpdatePopupOpen = false;

    $scope.CloseAccDetail = function() {
        $scope.isOpenAccDetail = false;
    }

    $scope.open = function() {
        $scope.isOpenAccDetail = true;
    }

    $scope.AuthPopupOpen = function() {
        $scope.isAuthPopupOpen = true;
    }

    $scope.AuthPopupClose = function() {
        $scope.isAuthPopupOpen = false;
    }

    $scope.closePopup = function() {
        $scope.isPopupOpen = false;
    }

    $scope.UpdatePopupClose = function() {
        $scope.isUpdatePopupOpen = false;
    }

    $scope.UpdatePopupOpen = function() {
        $scope.isUpdatePopupOpen = true;
    }

    $scope.url = '/images/';
    $scope.avatar = '';

    $scope.shipDetail = {

        userData: [],

        getDetailShip(id) {
            $http.get(`/rest/orders/ship/${id}`).then(resp => {
                console.log(resp.data)
                $scope.shipDetail.userData.push(resp.data);
            })
            $scope.isOpenAccDetail = true;

        }

    }



})