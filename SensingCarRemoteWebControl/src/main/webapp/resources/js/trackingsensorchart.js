var trackingSensorChart;
$(function() {
	trackingSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"trackingSensorChartContainer",
			type:"spline",
			events: {
				load: requestTrackingSensorData
			}
		},
		colors: ['green'],
		title: {
			text: "TrackingSensor(라인감지센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "감지",
				margin: 30
			}
		},
		series: [{
			name: "감지",
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

function requestTrackingSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/trackingsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = trackingSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.tracking], true, shift);
	};
}






