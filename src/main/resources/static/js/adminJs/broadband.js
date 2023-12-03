
updateYears();

let hasRunOnce = false;

function runOnceOnLoad() {
    if (!hasRunOnce) {
        document.getElementById("year").value = new Date().getFullYear();
        document.getElementById("month").value = 12;
        // Đặt biến flag thành true để không chạy lại lần nữa
        hasRunOnce = true;
    }
        updateChart();

}

runOnceOnLoad();

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
                backgroundColor: 'rgb(18,239,239)',
                borderColor: 'rgb(5,41,225)',
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

function CountOrderChar(status4, status0, allStatus) {
    // const labels = allStatus.map(row => formatDate(row.createDate));
    const customLabels = ['Label 1', 'Label 2', 'Label 3'];
    const value4 = status4.map(row => row[0]);
    const value0 = status0.map(row => row[0]);
    const value = allStatus.map(row => row[0]);
    console.log(value)
    const ctx = document.getElementById('CountOrderChar').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {

            datasets: [{
                label: 'Đơn đã giao',
                data: value4,
                backgroundColor: 'rgb(18,239,239)',
                borderColor: 'rgb(5,41,225)',
                borderWidth: 1,
                barPercentage: 0.25
            }, {
                label: 'Đơn bị hủy',
                data: value0,
                backgroundColor: 'rgb(218,0,46)',
                borderColor: 'rgb(236,34,76)',
                borderWidth: 1,
                barPercentage: 0.25
            }, {
                label: 'Tất cả trạng thái',
                data: value,
                backgroundColor: 'rgb(241,241,0)',
                borderColor: 'rgba(245,223,98,0.7)',
                borderWidth: 1,
                barPercentage: 0.25,
                stack: 'stack1'
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
// Define the fetchData function to handle fetch requests
async function fetchData(url) {
    const response = await fetch(url);

    if (!response.ok) {
        throw new Error(`Network response was not ok for ${url}`);
    }

    return response.json();
}

async function updateChart() {
    try {
        const year = document.getElementById("year").value;
        const month = document.getElementById("month").value;

        // Fetch data for total revenue by month
        const revenueData = await fetchData(`http://localhost:8080/rest/orders/calculateTotalRevenueByMonth?month=${month}&year=${year}`);
        renderChart(revenueData);

        // Fetch data for status 4
        const dataStatus4 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth4?month=${month}&year=${year}`);

        // Fetch data for status 0
        const dataStatus0 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth0?month=${month}&year=${year}`);

        // Fetch data for all statuses
        const dataAllStatus = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth?month=${month}&year=${year}`);

        // Update the charts for order count by status
        CountOrderChar(dataStatus4, dataStatus0, dataAllStatus);

        // Fetch data for total revenue by year
        const totalRevenueData = await fetchData(`http://localhost:8080/rest/orders/calculateTotalRevenueByYear?year=${year}`);
        document.getElementById("year1").textContent = year;
        document.getElementById("sum1").textContent = totalRevenueData;

        // Fetch data for total order count by year
        const totalOrderCountData = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByYear?year=${year}`);
        document.getElementById("year2").textContent = year;
        document.getElementById("sum2").textContent = totalOrderCountData;
    } catch (error) {
        console.error('Error fetching data:', error);
        // Handle errors or update UI as needed
    }
}

// Rest of your code remains unchanged
// ...

// Call the updateChart function on DOMContentLoaded
document.addEventListener('DOMContentLoaded', updateChart);

// Update the chart when year or month changes
document.getElementById("year").addEventListener("change", updateChart);
document.getElementById("month").addEventListener("change", updateChart);
