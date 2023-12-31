// document.querySelector('select[name="role"]').addEventListener('change', function() {
//     localStorage.setItem('selectedRole', this.value);
// });

// Đọc giá trị từ Local Storage khi trang được tải lại
// var selectedRole = localStorage.getItem('selectedRole');
// if (selectedRole) {
//     document.querySelector('select[name="role"]').value = selectedRole;
// }

var app = angular.module("app", []);


app.controller("ctrl", function($scope, $http, $timeout, $window) {

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

    $scope.reload = function() {
        $scope.isOpenAccDetail = false;
        $window.location.reload();
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
                $scope.shipDetail.userData = [resp.data];
            })
            $scope.isOpenAccDetail = true;

        },
        
        updateStatus(orderId, statusId, amount, username){
            $http.put(`/rest/orders/updateStatus/${orderId}/${statusId}`).then(resp =>{
                console.log(resp.data)
                $scope.shipDetail.userData = [resp.data];
                if(statusId == 4){
                    $http.put(`/auth/updateSpinCount/${amount}/${username}`).then(resp2 =>{
                        console.log(resp2.data);
                    })
                }
                this.getDetailShip(orderId);
            })
        },

        reload(){
            $window.location.reload();
        }

    }



})