const app = angular.module("app-payment-return", []);


app.controller("ctrl-payment-return", function($scope, $http, $window) {
	
	var orderElement = angular.element(document.getElementById('orderId'));	
	var orderId = orderElement.text();
	
	var usernameElement = angular.element(document.getElementById('user-name'));
	var username = usernameElement.text();
	
    $scope.redirectUrl = function(){
		
        var newUrl = '/order/detail/' + orderId;
        $window.location.replace(newUrl);
    }
    
    $scope.clearCart = function() { // lưu giỏ hàng vào local storage
    		items = [];
			var json = JSON.stringify(angular.copy(items));
			localStorage.setItem("cart_" + username, json);
			console.log("cart_" + username)
		}
		
	$scope.clearCart();
})