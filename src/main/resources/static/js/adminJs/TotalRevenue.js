// Lấy dữ liệu từ API của bạn hoặc mock data
// Đảm bảo thay thế URL API thực sự trong fetch bên dưới
fetch('/rest/orders/calculateTotalRevenue')
    .then(response => response.json())
    .then(data => {
        createChart(data);
    })
    .catch(error => console.error('Error fetching data:', error));

function createChart(data) {
    var ctx = document.getElementById('TotalRevenueChart').getContext('2d');

    var myChart = new Chart(ctx, {
        type: 'pie', // Chuyển đổi thành biểu đồ cột
        data: {
            labels: ['Tổng doand thu'],
            datasets: [{
                data: [data], // Sử dụng trực tiếp giá trị doanh thu
                backgroundColor: 'rgba(75, 192, 192, 0.5)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Tổng doand thu'
            },
            scales: {
                xAxes: [{
                    display: false, // Ẩn trục x
                }],
                yAxes: [{
                    display: false, // Ẩn trục y
                }]
            }
        }
    });
}
