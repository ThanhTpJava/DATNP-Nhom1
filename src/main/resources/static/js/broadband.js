<!-- Thêm thư viện Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


    // Lấy dữ liệu từ server, có thể sử dụng Ajax hoặc Fetch API
    // Dưới đây là một ví dụ giả sử có sẵn một mảng data chứa giá trị year, month, day

    // Mảng data giả định
    var data = [
    { label: "Year", value: 2023 },
    { label: "Month", value: 11 },
    { label: "Day", value: 23 }
    ];

    // Tạo biểu đồ
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
    labels: data.map(item => item.label),
    datasets: [{
    label: 'Revenue',
    data: data.map(item => item.value),
    backgroundColor: [
    'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    ],
    borderColor: [
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    ],
    borderWidth: 1
}]
},
    options: {
    scales: {
    y: {
    beginAtZero: true
}
}
}
});

