const app = angular.module("app", []);

app.run(function($http, $rootScope){
    $http.get(`/rest/security/authentication`).then(resp => {
    	if(resp.data){
			
    		$auth = $rootScope.$auth = resp.data;
        	$http.defaults.headers.common["Authorization"] = $auth.token;
			
			const data = resp.data;
			
			/*console.log("$auth:", $auth);
			console.log("Data: ", data);
			console.log("$auth.token: ", $auth.token);*/
			
			cartGuest = []
			var json = localStorage.getItem("cart_guest");
			cartGuest = json ? JSON.parse(json) : [];
			
			//console.log("cart_guest : ", cartGuest)
			
			cartUser = []
			var json = localStorage.getItem("cart_" + $auth.username);
			cartUser = json ? JSON.parse(json) : [];
			
			//console.log("cart_user : ", cartUser)
			
			// Nối cartGuest vào cartUser và lưu vào cartUserMeger
            let cartUserMeger = cartUser.slice(); // Tạo một bản sao của cartUser

            cartGuest.forEach(itemGuest => {
                // Kiểm tra xem có sản phẩm có cùng ID trong cartUser không
                const existingItem = cartUserMeger.find(itemUser => itemUser.id === itemGuest.id);

                if (existingItem) {
                    // Nếu có, tăng số lượng
                    existingItem.quantity = (existingItem.quantity || 1) + 1;
                } else {
                    // Nếu không, thêm mới vào cartUserMeger
                    cartUserMeger.push({
                        ...itemGuest,
                        quantity: 1
                    });
                }
            });

            //console.log("cartUserMeger : ", cartUserMeger);
            
            localStorage.setItem("cart_"+ $auth.username, JSON.stringify(cartUserMeger))
            
            cartGuest = []
            localStorage.setItem("cart_guest", JSON.stringify(cartGuest))
    	}else{
			$auth = $rootScope.$auth = {
				username : "guest"		
			};
			//console.log("Chưa login");
		}
    });
})