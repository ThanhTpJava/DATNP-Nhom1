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

function updateDays() {
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var daysInMonth = new Date(year, month, 0).getDate();

    var daySelect = document.getElementById("day");
    daySelect.innerHTML = '';

    for (var i = 1; i <= daysInMonth; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = i;
        daySelect.add(option);
    }
}

// Initialize years and days based on the current year and month
updateYears();
updateDays();