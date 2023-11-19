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



        getDetailShip(username) {
            $scope.accDetail.userData = [],

                $scope.accDetail.listUpdateUserRole.username = "";

            $http.get(`/admin/accounts/detail/${username}`).then(resp => {

                if (resp.data.photo == null) {
                    if (resp.data.gender == 'UNKNOW'
                        || resp.data.gender == 'MALE') {
                        $scope.avatar = $scope.url + 'default-avatar-male.png';
                        resp.data.photo = 'default-avatar-male.png'
                    } else {
                        $scope.avatar = $scope.url + 'default-avatar-female.png';
                        resp.data.photo = 'default-avatar-female.png'
                    }

                }

                $scope.accDetail.userData.push(resp.data);



                console.log("Data: ", resp.data)
                console.log("User data: ", $scope.accDetail.userData);

                $scope.accDetail.userData[0].dateOfBirth = this.dateFormat()

                $scope.accDetail.listUpdateUserRole.username
                    = resp.data.username;
                $scope.isCheckAdmin = false;
                $scope.isCheckSale = false;
                $scope.isCheckStock = false;

                $scope.accDetail.userData[0].roleName.forEach(function(role) {
                    // Kiểm tra mỗi giá trị role ở đây và thực hiện hành động dựa trên giá trị role
                    if (role === "Admin") {
                        $scope.isCheckAdmin = true;
                        $scope.accDetail.listUpdateUserRole.roles.ROLE_ADMIN = $scope.isCheckAdmin;
                        console.log("check admin")
                    } else if (role === "Sale") {
                        $scope.isCheckSale = true;
                        $scope.accDetail.listUpdateUserRole.roles.ROLE_SALE = $scope.isCheckSale;
                        console.log("check Sale")
                    } else if (role === "Stock") {
                        $scope.isCheckStock = true;
                        $scope.accDetail.listUpdateUserRole.roles.ROLE_STOCK = $scope.isCheckStock;
                        console.log("check stock")
                    }
                });
                /*console.log("Username : ", $scope.accDetail.listUpdateUserRole.username)
                console.log("Admin: ", $scope.isCheckAdmin)
                console.log("Stock: ", $scope.isCheckStock)
                console.log("Sale: ", $scope.isCheckSale)*/
                $scope.isOpenAccDetail = true;
            })
        },

        changeUserRole(role) {
            // Dựa trên trạng thái của các biến isCheckAdmin, isCheckSale, và isCheckStock
            // Cập nhật mảng UpdateUserRole tương ứng
            if (role === 'ROLE_ADMIN') {
                $scope.accDetail.listUpdateUserRole.roles.ROLE_ADMIN = $scope.isCheckAdmin;
                console.log("change role admin")
            } else if (role === 'ROLE_SALE') {
                $scope.accDetail.listUpdateUserRole.roles.ROLE_SALE = $scope.isCheckSale;
                console.log("change role sale")
            } else if (role === 'ROLE_STOCK') {
                $scope.accDetail.listUpdateUserRole.roles.ROLE_STOCK = $scope.isCheckStock;
                console.log("change role stock")
            }

            /*console.log("Admin: ", $scope.isCheckAdmin)
            console.log("Stock: ", $scope.isCheckStock)
            console.log("Sale: ", $scope.isCheckSale)*/
        },

        updateRoleUser() {
            // Gửi mảng UpdateUserRole lên server
            $scope.isAuthPopupOpen = false;

            $http({
                method: 'POST',
                url: '/admin/accounts/updaterole',
                data: JSON.stringify($scope.accDetail.listUpdateUserRole), // Chuyển đổi dữ liệu thành chuỗi JSON
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((response) => {
                $scope.isPopupOpen = true;
                $timeout(function() {
                    $scope.isPopupOpen = false; // Ẩn popup
                }, 4000);
                console.log('Cập nhật quyền thành công');

            }).catch((error) => {
                console.error('Lỗi khi cập nhật quyền: ', error);
            });
        },

        updateDataAccount() {
            $scope.isUpdatePopupOpen = false;

            var dataUpdate = angular.copy($scope.accDetail.userData[0])

            // Chuyển đổi ngày tháng năm từ dateOfBirth thành chuỗi "YYYY-DD-MM"
            /*var dateOfBirth = new Date(dataUpdate.dateOfBirth);
            var year = dateOfBirth.getFullYear();
            var day = dateOfBirth.getDate();
            var month = dateOfBirth.getMonth() + 1; // Tháng bắt đầu từ 0
            var formattedDate = year + '-' + day + '-' + (month < 10 ? '0' : '') + month;*/
            var formattedDate;
            if(dataUpdate.dateOfBirth != null){
                formattedDate = moment(dataUpdate.dateOfBirth).format('YYYY-MM-DD');
            }else{
                formattedDate = null;
            }

            console.log(formattedDate)

            dataUpdate.dateOfBirth = formattedDate;

            console.log("Data Update : ", dataUpdate)

            $http.post(`/auth/account/detail/update`, dataUpdate).then(resp => {
                $scope.isPopupOpen = true;
                $timeout(function() {
                    $scope.isPopupOpen = false; // Ẩn popup
                }, 4000);
                console.log("Update Success")
            }).catch(error => {
                console.log("Update error")
            })
        },

        dateFormat() {
            var dateConvert = $scope.accDetail.userData[0].dateOfBirth
            if (dateConvert != null) {
                dateConvert = new Date($scope.accDetail.userData[0].dateOfBirth);
            } else {
                dateConvert = null
            }

            return dateConvert
        }

    }



})