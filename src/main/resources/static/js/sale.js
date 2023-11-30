var app = angular.module("app", []);

app.controller("ctrl", function($scope, $http, $timeout, $window) {
	$scope.isOpenAccDetail = false;

	$scope.isCheckAdmin = false;
	$scope.isCheckSale = false;
	$scope.isCheckStock = false;

	$scope.isPopupOpen = false;
	
	$scope.isAuthPopupOpen = false;

	$scope.isUpdatePopupOpen = false;
	$scope.orderIdValue = ""
	$scope.statusIdValue = 100;
	
	$scope.statusDisabled = false;
	
	$scope.CloseAccDetail = function() {
		$scope.isOpenAccDetail = false;
	}
	
	$scope.closePopup = function() {
		$scope.isPopupOpen = false;
		$scope.refreshPage()
	}

	$scope.refreshPage = function() {
		 $window.location.reload();
	};

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

	}

	$scope.UpdatePopupClose = function() {
		$scope.isUpdatePopupOpen = false;
	}

	$scope.UpdatePopupOpen = function(orderId, statusId) {
		$scope.isUpdatePopupOpen = true;
		$scope.orderIdValue = orderId
		$scope.statusIdValue = statusId;
		/*console.log("orderIdValue", $scope.orderIdValue)
		console.log("statusIdValue", $scope.statusIdValue)*/
	}
	
	
	
	$scope.saleDetail = {

        saleData: {},

        getDetailSale(id) {
            $http.get(`/rest/orders/ship/${id}`).then(resp => {
                console.log(resp.data)
                $scope.saleDetail.saleData = resp.data;
                if($scope.saleDetail.saleData.orderStatus == 3 || $scope.saleDetail.saleData.orderStatus == 4){
					$scope.statusDisabled = true;
					 /*console.log("statusDisabled", $scope.statusDisabled)*/
				}else{
					$scope.statusDisabled = false;
					/*console.log("statusDisabled", $scope.statusDisabled)*/
				}
            }).catch(error => {
				console.log("get error", error)
			})
            $scope.isOpenAccDetail = true;

        },
        
     	saleUpdateSatus(){
			 $scope.isUpdatePopupOpen = true;
			 var orderId = $scope.orderIdValue
			 var statusId = $scope.statusIdValue
			 var username =  angular.element(document.getElementById('user-name')).text();
			/* console.log("OrderId", orderId)
			 console.log("Username", username)
			 console.log("Status Id", statusId)*/
			$http.put(`/rest/sale/updates/status/${orderId}/${username}/${statusId}`).then(resp => {
                console.log("Update success", resp.data)
                $scope.saleDetail.saleData = resp.data;
                $scope.isUpdatePopupOpen = false;
                $scope.isPopupOpen = true;         
            }).catch(error => {
				console.log("Update error", error)
			})
		 }
    }
})