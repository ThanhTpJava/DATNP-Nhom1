
updateYears();

let hasRunOnce = false;

function runOnceOnLoad() {
    if (!hasRunOnce) {
        document.getElementById("year").value = new Date().getFullYear();
        document.getElementById("month").value = 11;
        // Thực hiện các công việc bạn muốn chạy một lần ở đây
        updateChart();

        console.log('Đã chạy một lần khi khởi động trang web.');

        // Đặt biến flag thành true để không chạy lại lần nữa
        hasRunOnce = true;
    }
}

runOnceOnLoad();

document.addEventListener('DOMContentLoaded', function () {




    document.getElementById("year").addEventListener("change", updateChart);
    document.getElementById("month").addEventListener("change", updateChart);


});

function formatDate(dateString) {
    const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
}

function formatCurrency(value) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
}

function renderChart(data) {
    const labels = data.map(row => formatDate(row[1]));
    const values = data.map(row => row[0]);

    const ctx = document.getElementById('revenueChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Tổng doand thu theo tháng',
                data: values,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                barPercentage: 0.8 // Đặt giá trị này để điều chỉnh khoảng cách giữa các cột
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 50000, // Tăng khoảng cách giữa các giá trị trên trục y
                        callback: function (value, index, values) {
                            return formatCurrency(value);
                        }
                    }
                }
            }
        }
    });
}

function CountOrderChar(data) {
    const labels = data.map(row => formatDate(row[1]));
    const values = data.map(row => row[0]);

    const ctx = document.getElementById('CountOrderChar').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Tổng số hóa đơn theo tháng',
                data: values,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                barPercentage: 0.8 // Đặt giá trị này để điều chỉnh khoảng cách giữa các cột
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
}

function updateYears() {
    var currentYear = new Date().getFullYear();
    var yearSelect = document.getElementById("year");

    for (var i = 2010; i <= currentYear; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = i;
        yearSelect.add(option);
    }
}

function updateChart() {
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;



    // Update the chart with new data
    fetch(`http://localhost:8080/rest/orders/calculateTotalRevenueByMonth?month=${month}&year=${year}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (Array.isArray(data) && data.length > 0) {
                // Hàm renderChart chỉ được gọi khi mảng có dữ liệu
                renderChart(data);
            } else {
                console.log(year)
                console.log('The array is empty.');
                return renderChart([]);
            }
        })
        .catch(error => {
            renderChart([]);
            console.error('Error fetching data:', error);
        });



    fetch(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth?month=${month}&year=${year}`)
        .then(response => response.json())
        .then(data => {
            CountOrderChar(data);
        })
        .catch(error => console.error('Error fetching data:', error));

    // Update the chart with new data
    fetch(`http://localhost:8080/rest/orders/calculateTotalRevenueByYear?year=${year}`)
        .then(response => {
            // Check if the response is successful (status code 2xx)
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Update the content of <b> tag with the total revenue
            document.getElementById("year1").textContent = year;
            document.getElementById("sum1").textContent = data;
        })
        .catch(error => {
            document.getElementById("sum1").textContent = 0;
            document.getElementById("year1").textContent = year;
            return 0;
        });


    fetch(`http://localhost:8080/rest/orders/calculateTotalOrderByYear?year=${year}`)
        .then(response => response.json())
        .then(data => {
            // Update the content of <b> tag with the total order count
            document.getElementById("year2").textContent = year;
            document.getElementById("sum2").textContent = data;
        })
        .catch(error => console.error('Error fetching data:', error));
}


