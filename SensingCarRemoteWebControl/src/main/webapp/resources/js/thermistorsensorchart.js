var thermistorSensorChart;
$(function() {
	thermistorSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"thermistorSensorChartContainer",
			type:"spline",
			events: {
				load: requestThermistorSensorData
			}
		},
		colors: ['cyan'],
		title: {
			text: "ThermistorSensor(온도센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "온도",
				margin: 30
			}
		},
		series: [{
			name: "온도",
			data: []
		}],
		//마커(점)이 없어지는 현상 방지
		plotOptions: {
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    }
	});
	console.log(thermistorSensorChart);
});

function requestThermistorSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/thermistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = thermistorSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.temperature], true, shift);
	};
}






