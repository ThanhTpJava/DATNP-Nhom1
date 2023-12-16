
app.controller("luckyspin-ctrl", function($scope, $http, $q) {
	$scope.timeRotate = 7000;
	$scope.currentRotate = 0;
	$scope.isRotating = false;
	$scope.listGift = [];


	$scope.size = $scope.listGift.length;
	$scope.rotate = 360 / $scope.size;
	$scope.skewY = 90 - $scope.rotate;

	 var deferred = $q.defer();
	$http.get(`/rest/luckyspin/getvoucher`).then(resp => {
		$scope.listGift = resp.data.map(item => {
			return {
				voucher_code: item.voucher_code,
				review: item.review,
				description : item.description,
				rate: item.rate/100
			};
		});

		
		// Xử lý dữ liệu khi thành công
		console.log($scope.listGift);
		$scope.size = $scope.listGift.length;
        $scope.rotate = 360 / $scope.size;
        $scope.skewY = 90 - $scope.rotate;
        
        deferred.resolve();
	}).catch(error => {
		console.error('Error:', error);
		 deferred.reject(error);
		// Xử lý lỗi ở đây
	});
	$scope.httpPromise = deferred.promise;
})


app.directive('luckyWheel', function() {
    return {
        scope: {
            listGift: '=' // Truyền danh sách phần thưởng từ Controller
        },
        link: function(scope, element, attrs) {
            // Sử dụng $scope.$watch để theo dõi thay đổi của listGift
            scope.$watch('listGift', function(newListGift, oldListGift) {
                if (newListGift) {
					console.log("list gift: ", newListGift)
					element.empty();
					scope.size = newListGift.length;
                    // Các logic tạo các phần tử li và thiết lập transform
                    for (var index = 0; index < newListGift.length; index++) {
						var rotate = 360 / scope.size;
						
                        var skewY = 90 - rotate;
						
                        // Tạo phần tử li
                        var elm = angular.element('<li></li>');

                        // Thiết lập transform
                        elm.css('transform', 'rotate(' + rotate * index + 'deg) skewY(-' + skewY + 'deg)');

                        // Thêm background-color và căn giữa cho các phần tử text
                        var textClass = index % 2 === 0 ? 'text-1' : 'text-2';
                        elm.html('<p style="transform: skewY(' + skewY + 'deg) rotate(' + rotate / 2 + 'deg);" class="text ' + textClass + '"><b>' + newListGift[index].review + '</b></p>');

                        // Thêm vào ul
                        element.append(elm);
                    }
                }
            });
        }
    };
});
