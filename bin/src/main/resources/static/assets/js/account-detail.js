var app = angular.module("app-account", []);
app.controller("ctrl-account", function($scope, $http, $timeout, $window) {

	$scope.usernameforuser = angular.element(document.getElementById('user-name')).text();
	console.log($scope.usernameforuser)

	$scope.url = '/images/';
	$scope.avatar = '';
	$scope.isPopupOpen = false;
	$scope.isUpdatePopupOpen = false;

	$scope.UpdatePopupClose = function() {
		$scope.isUpdatePopupOpen = false;

	}

	$scope.UpdatePopupOpen = function() {
		$scope.isUpdatePopupOpen = true;
	}

	$scope.closePopup = function() {
		$scope.isPopupOpen = false;
		$scope.refreshPage()
	}

	$scope.refreshPage = function() {
		 $window.location.reload();
	};

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

		updateDataAccount() {
			$scope.isUpdatePopupOpen = false;
			var dataUpdate = angular.copy($scope.accDetail.userData[0])

			var formattedDate;
			if (dataUpdate.dateOfBirth != null) {
				formattedDate = moment(dataUpdate.dateOfBirth).format('YYYY-MM-DD');
			} else {
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
		},

		getDetailAccountForUser(usernameforuser) {
			$scope.accDetail.userData = [],

				$scope.accDetail.listUpdateUserRole.username = "";

			$http.get(`/auth/account/detail/${usernameforuser}`).then(resp => {

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
				$scope.accDetail.listUpdateUserRole.username
					= resp.data.username;


				console.log("Data: ", resp.data)
				console.log("User data: ", $scope.accDetail.userData);

				$scope.accDetail.userData[0].dateOfBirth = this.dateFormat()


				/*console.log("Username : ", $scope.accDetail.listUpdateUserRole.username)
				console.log("Admin: ", $scope.isCheckAdmin)
				console.log("Stock: ", $scope.isCheckStock)
				console.log("Sale: ", $scope.isCheckSale)*/
				$scope.isOpenAccDetail = true;
			})
		}

	}

	$scope.accDetail.getDetailAccountForUser($scope.usernameforuser);
})


