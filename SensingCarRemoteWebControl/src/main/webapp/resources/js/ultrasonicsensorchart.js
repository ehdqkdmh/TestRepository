var ultrasonicSensorChart;
$(function() {
	ultrasonicSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"ultrasonicSensorChartContainer",
			type:"spline",
			events: {
				load: requestUltrasonicSensorData
			}
		},
		colors: ['red'],
		title: {
			text: "UltrasonicSensor(거리센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "거리",
				margin: 30
			}
		},
		series: [{
			name: "거리",
			data: []
		}]
	});
});

function requestUltrasonicSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/ultrasonicsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = ultrasonicSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.distance], true, shift);
	};
}






