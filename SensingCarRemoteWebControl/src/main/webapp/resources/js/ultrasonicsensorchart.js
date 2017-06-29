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
		colors: ['white'],
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
});

function requestUltrasonicSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/ultrasonicsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = ultrasonicSensorChart.series[0];
		var shift = series.data.length > 20;	
		
		series.addPoint([data.time, data.distance], true, shift);		
		
		//특정 범위의 값일 경우 점의 색상을 변경
		var length = series.points.length;
		if(data.distance < 10) {
			series.points[length-1].color = "red";
		} else {
			series.points[length-1].color = "white";
		}		
	};
}






