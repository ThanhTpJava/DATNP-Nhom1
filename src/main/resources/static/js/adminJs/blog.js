app.controller('BlogController', function($scope, $http) {
    $scope.posts = [];

    $http.get('/api/posts').then(function(response) {
        $scope.posts = response.data;
    });

    $scope.addPost = function(post) {
        $http.post('/api/posts', post).then(function(response) {
            $scope.posts.push(response.data);
        });
    };

    $scope.updatePost = function(post) {
        $http.put('/api/posts/' + post.id, post).then(function(response) {
            // handle response
        });
    };

    $scope.deletePost = function(post) {
        $http.delete('/api/posts/' + post.id).then(function(response) {
            const index = $scope.posts.indexOf(post);
            $scope.posts.splice(index, 1);
        });
    };
});
