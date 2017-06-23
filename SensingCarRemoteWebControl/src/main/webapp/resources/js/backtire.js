function backtire(command, direction, speed) {
	var json = {"command":command, "direction":direction, "speed":speed};

	$.ajax({
		url:"http://" + location.host + "/SensingCarRemoteWebControl/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#backtireStatus").html("direction=" + data.direction + "; speed=" + data.speed);

				$("#btnForward").attr("onclick", "backtire('change', 'forward', '" + data.speed + "')");
				$("#btnBackward").attr("onclick", "backtire('change', 'backward', '" + data.speed + "')");
				
				$("#btnSpeed0").attr("onclick", "backtire('change', '" + data.direction + "', '0')");
				$("#btnSpeed1").attr("onclick", "backtire('change', '" + data.direction + "', '2295')");
				$("#btnSpeed2").attr("onclick", "backtire('change', '" + data.direction + "', '2495')");
				$("#btnSpeed3").attr("onclick", "backtire('change', '" + data.direction + "', '2695')");
				$("#btnSpeed4").attr("onclick", "backtire('change', '" + data.direction + "', '2895')");
				$("#btnSpeed5").attr("onclick", "backtire('change', '" + data.direction + "', '3095')");
				$("#btnSpeed6").attr("onclick", "backtire('change', '" + data.direction + "', '3295')");
				$("#btnSpeed7").attr("onclick", "backtire('change', '" + data.direction + "', '3495')");
				$("#btnSpeed8").attr("onclick", "backtire('change', '" + data.direction + "', '3695')");
				$("#btnSpeed9").attr("onclick", "backtire('change', '" + data.direction + "', '3895')");
				$("#btnSpeed10").attr("onclick", "backtire('change', '" + data.direction + "', '4095')");
			}
		}
	});
}