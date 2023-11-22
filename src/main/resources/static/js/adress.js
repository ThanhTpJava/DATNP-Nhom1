app.controller("cart-ctrl", function($scope, $http, $rootScope, $timeout, $window) {
	// quản lý giỏ hàng
	$scope.isPopupOpen = false;
	$scope.isPopupOpenErrors = false;
	$scope.PopupTitle = ""
	$scope.PopupMessage = ""
	$scope.iconUrlPopup = "/images/icons/tick.png"
	$scope.successIconUrl = "/images/icons/tick.png"
	$scope.errorIconUrl = "/images/icons/errors.png"
	$scope.checkOrder = false;

	$scope.closePopup = function() {
		$scope.isPopupOpen = false;
		if ($scope.checkOrder == true) {
			var delayTime = 750;
			var newUrl = '/order/detail/' + $scope.order.id;
			$timeout(function() {
				// Điều hướng đến URL mới
				$window.location.href = newUrl
				$window.location.replace(newUrl);
			}, delayTime);
		}
	};

	var $cart = $scope.cart = {
		items: [],
		add(id) { // thêm sản phẩm vào giỏ hàng
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			}
			else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		remove(id) { // xóa sản phẩm khỏi giỏ hàng
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() { // Xóa sạch các mặt hàng trong giỏ
			this.items = []
			this.saveToLocalStorage();
		},
		amt_of(item) {
			var originalPrice = item.price;
			var discountedPrice = originalPrice; // Giá sau khi áp dụng giảm giá

			// Tính giảm giá theo trường hợp giảm giá của sản phẩm
			if (item.sale) {
				discountedPrice = originalPrice * (1 - item.sale / 100); // Giảm giá theo phần trăm
			}

			// Kiểm tra xem $scope.order có được định nghĩa hay không và có thuộc tính 'voucher' hay không
			if ($scope.order && $scope.order.voucher && $scope.order.voucher.discount) {
				discountedPrice = discountedPrice * (1 - $scope.order.voucher.discount / 100); // Giảm giá theo phần trăm từ voucher
			}

			return discountedPrice * item.qty;
		},

		get count() { // tính tổng số lượng các mặt hàng trong giỏ
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		get amount() { // tổng thành tiền các mặt hàng trong giỏ
			return this.items
				.map(item => this.amt_of(item))
				.reduce((total, amt) => total += amt, 0);
		},
		saveToLocalStorage() { // lưu giỏ hàng vào local storage
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() { // đọc giỏ hàng từ local storage
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$cart.loadFromLocalStorage();
	var orderElement = angular.element(document.getElementById('order-id'));
	
	// Lấy văn bản trong phần tử
	var orderText = orderElement.text();
	
	// In ra kết quả
	
	 
	// Đặt hàng
	$scope.order = {
		id: orderText,
		get account() {
			return { username: $auth.username }
		},
		createDate: new Date(),

		address: "",
		totalAmount: $cart.amount,
		get orderDetails() {
			return $cart.items.map(item => {
				console.log(item); // Check if 'price' exists in 'item'
				return {
					product: { id: item.id },
					subtotal: item.price,
					quantity: item.qty

				}
			});
		},

		purchase() {
			var order = angular.copy(this);
			console.log(order)
			$scope.order.address = $auth.delivery_address
			console.log("address: ", $scope.order.address)
			if ($scope.order.address == null || $scope.order.address == '') {
				$scope.iconUrlPopup = $scope.errorIconUrl
				$scope.PopupTitle = "Lỗi!!!"
				$scope.PopupMessage = "Nhập đầy đủ địa chỉ giao hàng"
				$scope.isPopupOpen = true;
				return
			} else {
				console.log("Suscess")
				$scope.iconUrlPopup = $scope.successIconUrl
				$scope.PopupTitle = "Thành công!"
				$scope.PopupMessage = "Đơn hàng của bạn đã được tạo"
				$scope.isPopupOpen = true;
				$scope.checkOrder = true;
				var order = angular.copy(this);
				console.log(order)
				// Thực hiện đặt hàng
				$http.post("/rest/orders", order).then(resp => {

					console.log($scope.order.id)
					$scope.iconUrlPopup = $scope.successIconUrl
					$scope.PopupTitle = "Thành công!"
					$scope.PopupMessage = "Đơn hàng của bạn đã được tạo"
					$scope.isPopupOpen = true;
					$cart.clear();
					
				}).catch(error => {
					alert("Đặt hàng lỗi!")
					console.log(error)
				})
			}

		}
	}

	$http.get('/json/address.json').then(function(response) {
		// Xử lý dữ liệu JSON trước khi gán nó cho biến đối tượng
		$scope.addresses = response.data; // Biến addresses phải được khai báo trước đó là một đối tượng
		console.log("Address: ", $scope.addresses)
	});

	// 1. what is API
	// 2. How do I call API
	// 3. Explain code
	const host = "https://provinces.open-api.vn/api/";
	var callAPI = (api) => {
		return axios.get(api)
			.then((response) => {
				renderData(response.data, "province");
			});
	}
	callAPI('https://provinces.open-api.vn/api/?depth=1');
	var callApiDistrict = (api) => {
		return axios.get(api)
			.then((response) => {
				renderData(response.data.districts, "district");
			});
	}
	var callApiWard = (api) => {
		return axios.get(api)
			.then((response) => {
				renderData(response.data.wards, "ward");
			});
	}

	var renderData = (array, select) => {
		let row = ' <option disable value="">chọn</option>';
		array.forEach(element => {
			row += `<option value="${element.code}">${element.name}</option>`
		});
		document.querySelector("#" + select).innerHTML = row
	}

	$("#province").change(() => {
		callApiDistrict(host + "p/" + $("#province").val() + "?depth=2");
		printResult();
	});
	$("#district").change(() => {
		callApiWard(host + "d/" + $("#district").val() + "?depth=2");
		printResult();
	});
	$("#ward").change(() => {
		printResult();
	})


	var printResult = () => {
		let provinceText = $("#province option:selected").text() ;
		let districtText = $("#district option:selected").text() + ",";
		let wardText = $("#ward option:selected").text() + ",";
		let numberHourAndstreetName = ($("#numberHourAndstreetName").val() === '') ? 'Tên đường và số nhà,' : $("#numberHourAndstreetName").val();

		/*if (provinceText == "" || districtText == "" || wardText == "" || numberHourAndstreetName == "") {
			$scope.order.address = null
		} else */
			let result = numberHourAndstreetName + " " + wardText + " " + districtText + " " + provinceText ;
			// let idValue = order.address.replace(/\W/g, '_');
			$scope.$apply(function() {
				$auth.delivery_address = result;
			});
		
		/*	console.log("Address - :", $scope.order.address)*/

	};

	// Call printResult when the input values change
	$("#numberHourAndstreetName").on("input", printResult);

})