<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
<html>
<head>
<spring:url value="./CSS/styles.css" var="mainCss" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="${mainCss}">
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">

	<div class="tabs" style="position: absolute; left: 36%;">
		<ul class="tab-links">
			<li><a href="javascript:activateTab('page1')">Log In as PM</a></li>
			<li><a href="javascript:activateTab('page2')">Log In as
					Trader</a></li>
		</ul>
	</div>
	<div id="tabCtrl">
		<div id="page1" style="display: block;">
			<div id="first">
				<form action="loginpm" method="post" id="formetpm">
					<h1>Log In as PM</h1>
					<label>User ID :</label> <input id="userid" name="userid"
						placeholder="User ID" type="text" required class="txt"> <label>Password
						:</label> <input
						onkeydown="if (event.keyCode == 13) { this.form.submit(); return false; }"
						class="txt" id="password" name="password" placeholder="Password"
						type="password" required>
					<div id='cssbutton' align="right">
						<ul>
							<li><input type="button" class="button" value="Submit"
								onclick="doAjaxPostPM()"></li>
							<li><a class="button" href="reset">Reset Password</a></li>
						</ul>
					</div>
					<div id="pmLoginResult"></div>
				</form>
			</div>
		</div>

		<div id="page2" style="display: none;">
			<div id="first">
				<form action="login" method="post" id="formet">
					<h1>Log In as Trader</h1>
					<label>User ID :</label><input class="txt" id="traderUsername"
						name="userid" placeholder="User ID" type="text" required>
					<label>Password :</label> <input
						onkeydown="if (event.keyCode == 13) { this.form.submit(); return false; }"
						class="txt" id="traderPassword" name="password"
						placeholder="Password" type="password" required>
					<div id='cssbutton' align="right">
						<ul>
							<li><input type=button value="Submit" class="button"
								onclick="doAjaxPost()"></li>
							<li><a class="button" href="reset">Reset Password</a></li>
						</ul>
					</div>
					<div id="traderLoginResult"></div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
	<script type="text/javascript">
		function activateTab(pageId) {
			var tabCtrl = document.getElementById('tabCtrl');
			var pageToActivate = document.getElementById(pageId);
			for (var i = 0; i < tabCtrl.childNodes.length; i++) {
				var node = tabCtrl.childNodes[i];
				if (node.nodeType == 1) { /* Element */
					node.style.display = (node == pageToActivate) ? 'block'
							: 'none';
				}
			}
		}
	</script>
	<script type="text/javascript">
		function doAjaxPost() {
			var username = $("#traderUsername").val();
			var password = $('#traderPassword').val();
			console.log(username + "\t" + password);
			var form = document.getElementById("formet");
			console.log(form.value);
			$
					.getJSON(
							"/SpringMVCHibernate/verifyLogin",
							{
								username : username,
								password : password
							},
							function(data) {
								console.log("data came");
								console.log(data);
								if (data == true) {
									console.log("submiting");
									form.submit();
								} else {
									var div = document
											.getElementById("traderLoginResult");
									div.innerHTML = "<span style=\"font-color:red;\">Invalid Credentials</span>"
								}
							});
		}
	</script>
	<script type="text/javascript">
		function doAjaxPostPM() {
			var username = $("#userid").val();
			var password = $('#password').val();
			console.log(username + "\t" + password);
			var form = document.getElementById("formetpm");
			console.log(form.value);
			$
					.getJSON(
							"/SpringMVCHibernate/pmverifyLogin",
							{
								username : username,
								password : password
							},
							function(data) {
								console.log("data came");
								console.log(data);
								if (data == true) {
									console.log("submiting");
									form.submit();
								} else {
									var div = document
											.getElementById("pmLoginResult");
									div.innerHTML = "<span style=\"font-color:red;\">Invalid Credentials</span>"
								}

							});

		}
	</script>
</body>
</html>