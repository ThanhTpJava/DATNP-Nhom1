$.ajax({
	url: 'thongke',
	success: function(result){
		var date = JSON.parse(result).date;
		var counter = JSON.parse(result).counter;
		drawLineChart(date, counter);
	}
})


function drawLineChart(date, counter){
	Highcharts.chart('container', {
		chart: {
			type: 'line',
			width: 500
		},
		title: {
			text: 'Thống Kê Lượt Truy Cập Vào Website'
		},
		xAxis: {
			categories: date
		},
		tooltip: {
			formatter: function(){
				return '<strong>'+this.x+': </strong>'+this.y
			}
		},
		series: [{
			data: counter
		}]
	});
}
