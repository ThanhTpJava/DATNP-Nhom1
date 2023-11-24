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

	$scope.isLoading = false;
	$scope.form = {}
	$scope.errors = {};
	$scope.usernameExists = false;
	$scope.emailExists = false;
	$scope.isPopupOpen = false;
	$scope.isPopupOpenErrors = false;
	$scope.isResetPasswordPopupOpen = false;
	$scope.isPopupOpenOTP = false;

	$scope.isPopupOpenVer2 = false;
	$scope.PopupTitle = ""
	$scope.PopupMessage = ""
	$scope.iconUrlPopup = "/images/icons/tick.png"
	$scope.successIconUrl = "/images/icons/tick.png"
	$scope.errorIconUrl = "/images/icons/errors.png"
	$scope.iconUrlotp = "/images/icons/otp.png"


	$scope.usernameReset = "";
	$scope.passReset = "";
	$scope.passwordResetConfirm = "";

	$scope.getValueReset = function() {
		var usernameResetPassElement = angular.element(document.getElementById('username-reset-password'));
		var passResetElement = angular.element(document.getElementById('password-reset'));
		var passwordResetConfirmElement = angular.element(document.getElementById('password-reset-confirm'));

		$scope.usernameReset = usernameResetPassElement.val();
		$scope.passReset = passResetElement.val();
		$scope.passwordResetConfirm = passwordResetConfirmElement.val();
	}

	$scope.resetPassword = function() {

		$scope.getValueReset();
		console.log($scope.usernameReset)
		if ($scope.passReset == '') {
			$scope.iconUrlPopup = $scope.errorIconUrl
			$scope.PopupTitle = "Lỗi!!!"
			$scope.PopupMessage = "Mật khẩu không được để trống!"
			$scope.isPopupOpenVer2 = true;
		}
		else if ($scope.passReset !== $scope.passwordResetConfirm) {
			$scope.iconUrlPopup = $scope.errorIconUrl
			$scope.PopupTitle = "Lỗi!!!"
			$scope.PopupMessage = "Mật khẩu xác nhận không khớp!"
			$scope.isPopupOpenVer2 = true;
		} else {
			var url = `${host}/forgotpassword/${$scope.usernameReset}`;
			$scope.isResetPasswordPopupOpen = false;
			$scope.isLoading = true;
			$http.get(url)
				.then(function(response) {
					if (response.status === 200) {
						$scope.isLoading = false;
						$scope.PopupTitle = "Xác Nhận OTP"
						$scope.PopupMessage = "Vui lòng kiểm tra email của bạn\nMã OTP thường được gửi từ 1 - 5 phút"
						$scope.iconUrlPopup = $scope.iconUrlotp
						$scope.isPopupOpenOTP = true;
						console.log("Success", response)
					}
				})
				.catch(function(error) {
					if (error.status === 404) {
						$scope.isLoading = false;
						console.log("Not found");
						$scope.iconUrlPopup = $scope.errorIconUrl
						$scope.PopupTitle = "Lỗi!!!"
						$scope.PopupMessage = "Tài khoản không tồn tại!"
						$scope.isPopupOpenVer2 = true;
					} else {
						console.error("Error:", error);
					}
				});
		}

	}

	$scope.getOtpValue = function() {
		var otpInputValue = '';

		// Lấy tất cả các input có class 'input-otp'
		var inputElements = document.querySelectorAll('.input-otp');

		// Chuyển đổi NodeList thành mảng
		var inputArray = Array.from(inputElements);

		// Sắp xếp mảng theo thứ tự xuất hiện trong DOM
		inputArray.sort(function(a, b) {
			return Array.from(a.parentElement.children).indexOf(a) - Array.from(b.parentElement.children).indexOf(b);
		});

		// Nối giá trị của các input lại thành chuỗi
		inputArray.forEach(function(element) {
			otpInputValue += element.value;
		});

		$scope.clearOtpInputs()
		return otpInputValue
	};

	$scope.clearOtpInputs = function() {
		// Lấy tất cả các input có class 'input-otp'
		var inputElements = document.querySelectorAll('.input-otp');

		// Chuyển đổi NodeList thành mảng
		var inputArray = Array.from(inputElements);

		// Thiết lập giá trị của từng input về rỗng
		inputArray.forEach(function(element) {
			element.value = "";
		});
	};


	$scope.comfirmOTP = function() {
		var otp = $scope.getOtpValue()
		$scope.isPopupOpenOTP = false;
		console.log(otp);

		var data = {
			OTP: otp,
			username: $scope.usernameReset,
			newPassword: $scope.passReset
		};
		
		var url = `${host}/forgotpassword/confirmotp`;
		$http.post(url, data).then((response) => {
			console.log("good", response);
			$scope.iconUrlPopup = $scope.successIconUrl
			$scope.PopupTitle = "Thành công!"
			$scope.PopupMessage = "Mật khẩu của bạn đã được cập nhật"
			$scope.isPopupOpenVer2 = true;

		}).catch(error => {
			console.log("bad", error);
			$scope.iconUrlPopup = $scope.errorIconUrl
			$scope.PopupTitle = "Lỗi!!!"
			$scope.PopupMessage = "Mã OTP sai, vui lòng kiểm tra lại"
			$scope.isPopupOpenVer2 = true;
		});
	}

	$scope.openResetPassPopup = function() {
		$scope.isResetPasswordPopupOpen = true;
	}

	$scope.closeResetPassPopup = function() {
		$scope.isResetPasswordPopupOpen = false;
	}

	$scope.closePopup = function() {
		$scope.isPopupOpen = false;
	}

	$scope.closePopupVer2 = function() {
		$scope.isPopupOpenVer2 = false;
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
			console.log("Error", error)
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