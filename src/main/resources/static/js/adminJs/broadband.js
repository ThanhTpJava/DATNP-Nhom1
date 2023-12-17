
updateYears();
function formatCurrency(value) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
}
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




function renderChart(data) {
    // const labels = data.map(row => formatDate(row[1]));

    let yearInput = document.getElementById("year");
    let monthInput = document.getElementById("month");

    const labels = data.map(row => {
        // Lấy giá trị ngày, tháng và năm từ các nguồn khác nhau
        const day = row[1];
        const month = monthInput.value;
        const year = yearInput.value;

        // Tạo đối tượng Date từ thông tin thu được
        const date = new Date(`${year}-${month}-${day}`);

        // Sử dụng formatDate để định dạng ngày
        const formattedDate = formatDate(date);

        return formattedDate;
    });
    // const labels = data.map(row => row[1]);
    const values = data.map(row => row[0]);

    const ctx = document.getElementById('revenueChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Tổng doand thu theo tháng (đơn vị: VND)',
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
                    beginAtZero: true
                }
            }
        }
    });
}
console.log(formatCurrency(1000000)); // Replace 1000000 with a sample value

function CountOrderChar(status0, status1, status2, status3, status4, status5,  allStatus) {
    // const s0 = status4.map(row => formatDate(row.createDate));
    // const s4 = status0.map(row => formatDate(row.createDate));
    // const sall = allStatus.map(row => formatDate(row.createDate));

    const value0 = status0.map(row => row[0]);
    const value1 = status1.map(row => row[0]);
    const value2 = status2.map(row => row[0]);
    const value3 = status3.map(row => row[0]);
    const value4 = status4.map(row => row[0]);
    const value5 = status5.map(row => row[0]);
    const value = allStatus.map(row => row[0]);
    console.log(value)
    const ctx = document.getElementById('CountOrderChar').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [' '],
            datasets: [
                {
                    label: 'Đơn bị hủy',
                    data: value0,
                    backgroundColor: 'rgb(218, 0, 46)',
                    borderColor: 'rgb(236, 34, 76)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Đơn Đang xác nhận',
                    data: value1,
                    backgroundColor: 'rgb(0, 128, 0)',
                    borderColor: 'rgb(34, 139, 34)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Đơn đã xác nhận',
                    data: value2,
                    backgroundColor: 'rgb(255, 165, 0)',
                    borderColor: 'rgb(255, 140, 0)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Đơn đang giao',
                    data: value3,
                    backgroundColor: 'rgb(70, 130, 180)',
                    borderColor: 'rgb(0, 0, 128)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Đơn đã giao',
                    data: value4,
                    backgroundColor: 'rgb(18, 239, 239)',
                    borderColor: 'rgb(5, 41, 225)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Đơn bị trả',
                    data: value5,
                    backgroundColor: 'rgb(255, 69, 0)',
                    borderColor: 'rgb(255, 99, 71)',
                    borderWidth: 1,
                    barPercentage: 0.25
                },
                {
                    label: 'Tất cả đơn',
                    data: value,
                    backgroundColor: 'rgb(241, 241, 0)',
                    borderColor: 'rgba(245, 223, 98, 0.7)',
                    borderWidth: 1,
                    barPercentage: 0.25,
                    stack: 'stack1'
                }
            ]
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

    for (var i = 2020; i <= currentYear; i++) {
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

        // Fetch data for status 0
        const dataStatus0 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth0?month=${month}&year=${year}`);

        // Fetch data for status 1
        const dataStatus1 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth1?month=${month}&year=${year}`);

        // Fetch data for status 0
        const dataStatus2 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth2?month=${month}&year=${year}`);

        // Fetch data for status 0
        const dataStatus3 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth3?month=${month}&year=${year}`);

        // Fetch data for status 4
        const dataStatus4 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth4?month=${month}&year=${year}`);

        // Fetch data for status 5
        const dataStatus5 = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth5?month=${month}&year=${year}`);

        // Fetch data for all statuses
        const dataAllStatus = await fetchData(`http://localhost:8080/rest/orders/calculateTotalOrderByMonth?month=${month}&year=${year}`);

        // Update the charts for order count by status
        CountOrderChar(dataStatus0, dataStatus1, dataStatus2, dataStatus3, dataStatus4, dataStatus5, dataAllStatus);

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
