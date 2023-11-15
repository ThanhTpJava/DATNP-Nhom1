app.controller("cart-ctrl", function($scope, $http) {
	// quản lý giỏ hàng
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
		status: 0,
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
			console.log(orderText);
			var order = angular.copy(this);
			// console.log(order)
			// Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công!");
				$cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
				console.log(error)
			})
		}
	}
})