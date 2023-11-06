document.querySelector('select[name="role"]').addEventListener('change', function() {
	localStorage.setItem('selectedRole', this.value);
});

// Đọc giá trị từ Local Storage khi trang được tải lại
var selectedRole = localStorage.getItem('selectedRole');
if (selectedRole) {
	document.querySelector('select[name="role"]').value = selectedRole;
}

var app = angular.module("app2", []);


app.controller("ctrl", function($scope, $http, $timeout) {

	$http.get("/rest/authorities").then(resp => {
		$scope.db = resp.data;
		console.log(resp.data)
	})

	$scope.index_of = function(username, role) {
		return $scope.db.authorities.findIndex(a => a.account.username == username && a.role.id == role);
	}

	$scope.update = function(username, role, id) {
		var index = $scope.index_of(username, role);
		if (index >= 0) {
			var id = $scope.db.authorities[index].id;

			$http.delete(`/rest/authorities/${id}`).then(resp => {
				$scope.db.authorities.splice(index, 1);
			})
		}
		else {
			var authority = {
				account: { username: username },
				role: { id: role }
			};
			$http.post('/rest/authorities', authority).then(resp => {
				$scope.db.authorities.push(resp.data);
			})
		}
	}

	$scope.isOpenAccDetail = false;

	$scope.isCheckAdmin = false;
	$scope.isCheckSale = false;
	$scope.isCheckStock = false;

	$scope.isPopupOpen = false;

	$scope.isAuthPopupOpen = false;

	$scope.CloseAccDetail = function() {
		$scope.isOpenAccDetail = false;
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

	$scope.accDetail = {

		userData: [],

		listUpdateUserRole:
		{
			username: "",
			roles: {
				"ROLE_ADMIN": false,
				"ROLE_SALE": false,
				"ROLE_STOCK": false
			}
		},

		getDetailAccount(username) {
			$scope.accDetail.userData = [],
				$scope.accDetail.listUpdateUserRole.username = "";

			$http.get(`/admin/accounts/detail/${username}`).then(resp => {
				$scope.accDetail.userData.push(resp.data);
				console.log("Data: ", resp.data)
				console.log("User data: ", $scope.accDetail.userData);

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
		}


	}
})