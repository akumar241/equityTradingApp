<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<spring:url value="./CSS/styles.css" var="mainCss" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogOut</title>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache");
%>
 <script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script> 
<link rel="stylesheet" href="${mainCss}">
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">

	<div id="first">
		<form action="welcome" method="get">
			<h1>You are logged out.</h1>
			<div id='cssbutton'>
				<ul>
					<li><input type=submit class="button" value="Login"></li>
				</ul>
			</div>
		</form>
	</div>

</body>
</html>