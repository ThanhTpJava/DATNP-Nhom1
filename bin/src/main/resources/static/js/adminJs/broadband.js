//
// var app = angular.module('app', []);
//
// app.controller('ctrl', function($scope, $http) {
//     $http.get("/rest/orders/deliveredOrdersByMonth")
//         .then(function(response) {
//             $scope.ordersByMonth = response.data;
//             console.log($scope.ordersByMonth);
//             google.charts.load("current", {packages:['corechart']});
//             google.charts.setOnLoadCallback(drawChart);
//             function drawChart() {
//                 var data = new google.visualization.DataTable();
//                 data.addColumn('string', 'Day');
//                 data.addColumn('number', 'TotalAmount');
//                 for (var i = 0; i < $scope.ordersByMonth.length; i++) {
//                     data.addRow([$scope.ordersByMonth[i][0], $scope.ordersByMonth[i][1]]);
//                 }
//
//                 var view = new google.visualization.DataView(data);
//                 view.setColumns([0, 1,
//                     {
//                         calc: "stringify",
//                         sourceColumn: 1,
//                         type: "string",
//                         role: "annotation"
//                     },
//                     2
//                 ]);
//
//                 var options = {
//                     title: "Total Amount by Day in November",
//                     width: 600,
//                     height: 400,
//                     bar: {groupWidth: "75%"},
//                     legend: { position: "none" },
//                 };
//
//                 var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
//                 chart.draw(view, options);
//             }
//         });
// });

// Define the AngularJS app
var app = angular.module('app', []);

// Define the AngularJS controller
app.controller('ctrl', function ($scope, $http) {
    google.charts.load("current", { packages: ['corechart'] });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        // Use AngularJS $http service to fetch data
        $http.get('/rest/orders/deliveredOrdersByMonth')
            .then(response => {
                // Process the data and update the chart
                var dataTable = google.visualization.arrayToDataTable(response.data);

                var view = new google.visualization.DataView(dataTable);
                view.setColumns([
                    0, 1,
                    {
                        calc: "stringify",
                        sourceColumn: 1,
                        type: "string",
                        role: "annotation"
                    },
                    2
                ]);

                var options = {
                    title: "Delivered Orders in November",
                    width: 600,
                    height: 400,
                    bar: { groupWidth: "75%" },
                    legend: { position: "none" },
                };

                var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                chart.draw(view, options);
            })
            .catch(error => console.error('Error fetching data:', error));
    }
});