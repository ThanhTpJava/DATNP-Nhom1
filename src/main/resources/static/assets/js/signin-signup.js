const sign_in_btn = document.querySelector("#sign-in-btn")
const sign_up_btn = document.querySelector("#sign-up-btn")
const container_loginform = document.querySelector(".container-loginform")

sign_up_btn.addEventListener('click', () => {
	container_loginform.classList.add("sign-up-mode")
})

sign_in_btn.addEventListener('click', () => {
	container_loginform.classList.remove("sign-up-mode")
})

let popup = document.getElementById("popup")

function closePopup() {
	popup.classList.remove("open-popup")
}

var currentURL = window.location.href;

// Kiểm tra nếu URL chứa "error"
if (currentURL.includes("/login/error")) {
	// Thay đổi class của phần tử có ID "popup" để hiển thị
	var popupElement = document.getElementById("popup");
	if (popupElement) {
		popupElement.classList.add("open-popup");
	}
}

/*if (window.location.pathname.includes('/login/error')) {
	window.history.replaceState(null, null, '/qcshop/login');
}*/

let host = "http://localhost:8080/auth"

const app = angular.module("app-sign", []);
app.controller("ctrl", function($scope, $http, $timeout) {

	$scope.form = {}
	$scope.errors = {};
	$scope.usernameExists = false;
	$scope.emailExists = false;
	$scope.isPopupOpen = false;
	$scope.isPopupOpenErrors = false;

	$scope.closePopup = function() {
		$scope.isPopupOpen = false;
	}

	$scope.create = function() {
		var item = angular.copy($scope.form)
		console.log(item)
		var url = `${host}/signup/newaccount`;
		
		if ($scope.errors.confirm) {
			// Nếu có lỗi xác nhận mật khẩu, không thực hiện POST
			return;
		}
		
		$http.post(url, item).then(resp => {
			console.log(resp.status)
			if (resp.status === 201) {
				console.log("Success");
				$scope.isPopupOpen = true;
				$scope.errors = {};
				$timeout(function() {
					$scope.isPopupOpen = false; // Ẩn popup
				}, 5000); // Thời gian tính bằng mili giây 
				// Thực hiện xử lý thành công ở đây
			}
		}).catch(error => {
			console.log("Error", error.data)
			$scope.isPopupOpenErrors = true;
			$timeout(function() {
				$scope.isPopupOpenErrors = false; // Ẩn popup
			}, 3000);
			$scope.errors = error.data;
		})
	}

	$scope.checkPasswordConfirmation = function() {
		if ($scope.form.password !== $scope.confirmPass) {
			$scope.errors.confirm = "Mật khẩu xác nhận không khớp";
		} else {
			$scope.errors.confirm = ""; // Đặt thông báo lỗi thành rỗng nếu trùng khớp
		}
	};

	$scope.checkUsername = function() {
		var username = $scope.form.username;

		// Gửi yêu cầu kiểm tra username lên máy chủ
		var url = `${host}/checkusernameexits/${username}`;
		$http.get(url)
			.then(function(response) {
				if (response.data.available) {
					$scope.usernameExists = false;
					if ($scope.errors || $scope.errors.username) {
						$scope.errors.username = "";
					}
				} else {
					// Tên người dùng đã tồn tại, hiển thị thông báo lỗi cho người dùng.
					console.log("Username exit")
					$scope.usernameExists = true;
					if ($scope.errors || $scope.errors.username) {
						$scope.errors.username = "Tên người dùng đã tồn tại";
					}
				}
			})
			.catch(function(error) {
				// Xử lý lỗi nếu có.
				console.log("Error username : ", error)
			});

	};

	$scope.checkEmail = function() {
		var email = $scope.form.email;

		// Gửi yêu cầu kiểm tra email lên máy chủ
		var url = `${host}/checkemailexits/${email}`;
		$http.get(url)
			.then(function(response) {
				if (response.data.available) {
					$scope.emailExists = false;
					if ($scope.errors || $scope.errors.email) {
						$scope.errors.email = "";
					}
				} else {
					// Email đã tồn tại, hiển thị thông báo lỗi cho người dùng.
					console.log("Email exit")
					$scope.emailExists = true;
					if ($scope.errors || $scope.errors.email) {
						$scope.errors.email = "Email này đã tồn tại";
					}
				}
			})
			.catch(function(error) {
				// Xử lý lỗi nếu có.
				console.log("Error email : ", error)
			});

	};


})