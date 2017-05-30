<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>블록 및 인라인 태그</h1>
		
		<h3>블록 태그</h3>
		<div style="background-color:yellow; border:1px solid black; height: 50px">div 태그</div>
		<p style="background-color:skyblue; border:1px solid black; height: 50px">p 태그</p>
		<h4 style="background-color:coral; border:1px solid black; height: 50px">h 태그</h4>
		<form style="background-color:gold; border:1px solid black; height: 50px">form 태그</form>
		<table style="background-color: blanchedalmond; border:1px solid black; height: 50px; width:100%;">
			<tr>
				<td>table 태그</td>
			</tr>
		</table>
		<table style="background-color: blanchedalmond; border:1px solid black; height: 50px; width:100%;">
			<tr>
				<td>table 태그</td>
			</tr>
		</table>
		
		<h3>인라인 태그</h3>
		<span style="background-color:yellow; border:1px solid black; height: 50px">span 태그</span>
		<span style="background-color:skyblue; border:1px solid black; height: 50px">span 태그</span>
		<img style="border:1px solid black;" src="/WebApplication/resources/image/spring.png" height="50px"/>
		<a style="border:1px solid black;" href="/html">a 태그</a>
		<button>button 태그</button>
		
		<h3>블록 -> 인라인</h3>
		<div style="background-color:yellow; border:1px solid black; height: 50px; display:inline;">div 태그</div>
		<div style="background-color:skyblue; border:1px solid black; height: 50px; display:inline;">div 태그</div>
	</body>
</html>
