
const labels = ['2023-11-07', '2023-11-08', '2023-11-09', '2023-11-10', '2023-11-11']

const data = {
	labels:labels,
	datasets : [
		{
			label: 'Lượt truy cập',
			backgroundColor: "blue",
			borderColor: 'blue',
			data:[10,27,56,34,24]
		},
	],
}

const config = {
	type: 'line',
	data: data,
}

const canvas = document.getElementById('canvas')
const chart = new Chart(canvas, config)

