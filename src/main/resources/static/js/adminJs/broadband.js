document.addEventListener('DOMContentLoaded', function () {


    // Replace 'yourApiEndpoint' with the actual endpoint of your Spring Boot application
    fetch('/rest/orders/calculateTotalRevenueByMonth?month=11&year=2023')
        .then(response => response.json())
        .then(data => {
            renderChart(data);
        })
        .catch(error => console.error('Error fetching data:', error));

    // Initialize years and days based on the current year and month
    updateYears();

    // Set default values for year and month
    document.getElementById("year").value = new Date().getFullYear();
    document.getElementById("month").value = 11;

    document.getElementById("year").addEventListener("change", updateChart);
    document.getElementById("month").addEventListener("change", updateChart);

    // Trigger the chart update with default values
    updateChart();
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
        .then(response => response.json())
        .then(data => {
            renderChart(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}


