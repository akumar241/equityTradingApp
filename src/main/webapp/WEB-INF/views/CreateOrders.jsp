<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=''>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSS/stylepm.css">

<title>Create Order</title>
<script type="text/javascript" src="JS/script.js"></script>
</head>

<body>
	<div id='cssmenu'>
		<ul>
			<li><a href="#">Welcome <%=session.getAttribute("username")%>!
			</a></li>
			<li><a href='ViewPositionsbyportfolio?id=0'><span>View
						Positions</span></a></li>
			<li><a href='CreateOrdershome'><span>Create Orders</span></a></li>
			<li><a href='ViewOrdershome?page=page1'><span>View
						Orders</span></a></li>
			<li class='last'><a href='logout'><span>Logout</span></a></li>
		</ul>
	</div>
	<div id="first">
		<h2>Create Order</h2>
		<form:form modelAttribute="createorder" action="CreateOrder"
			method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Portfolio:</label></td>

						<td style="padding-left: 44px;"><form:select id="select"
								path="portfolio.portfolioId" class="form-control"
								style="width: 150px;">
								<c:forEach var="listValue" items="${portfolio}">
									<form:option value="${listValue.portfolioId}"
										label="${listValue.portfolioName}">
									</form:option>
								</c:forEach>
							</form:select></td>
						<td><input type="button" class="button1"
							value="Add New Portfolio" onclick="addNew()"></td>
					</tr>
				</tbody>
			</table>
			<table>
				<tbody>
					<tr>
						<td><label>Security Symbol:</label></td>
						<td style="padding-left: 5px;">
							<div class="automplete-suggestion">
								<form:input type="text" path="security" class="form-control"
									id="security" name="Symbol" placeholder="Enter Equity Symbol"
									style="width: 140px;"></form:input>
							</div>
						</td>
						<td><form:input type="text" class="form-control"
								id="EquityLTP" name="ltp" placeholder=" LTP"
								path="security.lastTradedPrice" readOnly="true"
								style="width:50px;"></form:input></td>
					</tr>
				</tbody>
			</table>
			<table>
				<tbody>
					<tr>
						<td><label>Security Name: </label></td>
						<td><form:input type="text" class="form-control"
								id="EquityName" name="name" placeholder="Security Name"
								path="security.securityName" readOnly="true"></form:input></td>
					</tr>
					<tr>
						<td><label>Side:</label></td>
						<td><form:radiobutton path="side" name="EquitySide"
								value="BUY" checked="true"></form:radiobutton>BUY <form:radiobutton
								path="side" name="EquitySide" value="SELL"></form:radiobutton>SELL</td>
					</tr>
					<tr>
						<td><label>Order Type:</label></td>
						<td><form:select path="type" class="form-control" name="type"
								id="type" style="width: 207px;" onclick="selectType('type')">
								<form:option value="MARKET" selected="selected">Market Order</form:option>
								<form:option value="LIMIT">Limit Order</form:option>
								<form:option value="STOP">Stop Order</form:option>
							</form:select><br></td>
					</tr>
					<tr>
						<td><label>Order Qualifiers:</label></td>
						<td><form:select path="qualifiers" class="form-control"
								style="width: 207px;">
								<form:option value="DAYORDER" selected="selected">Day Order</form:option>
								<form:option value="GTC">GTC</form:option>
							</form:select></td>
					</tr>
					<tr>
						<td><label>Account Type:</label></td>
						<td><form:select path="accountType" class="form-control"
								style="width: 207px;">
								<form:option value="CASH" selected="selected">Cash</form:option>
								<form:option value="MARGIN">Margin</form:option>
							</form:select></td>
					</tr>
				</tbody>
			</table>

			<table>
				<tbody>
					<tr>
						<td><label>Trader:</label></td>
						<td style="padding-left: 31px;"><form:select
								path="equityTrader.id" class="form-control"
								style="width: 150px;">
								<form:option value="${createorder.getEquityTrader().getId()}"
									label="${createorder.getEquityTrader().getName()}"></form:option>
								<c:forEach var="listValue" items="${trader}">
									<form:option value="${listValue.id}" label="${listValue.name}">
									</form:option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td><label>Quantity:</label></td>
						<td style="padding-left: 32px;"><form:input type="number"
								class="form-control" id="EquityQty" name="qty"
								placeholder="Enter Quantity" required="true"
								path="totalQuantity"></form:input></td>
					</tr>
					<tr>
						<td><label>Limit Price:</label></td>
						<td style="padding-left: 32px;"><form:input type="number"
								class="form-control" id="limitPrice" name="limitPrice"
								placeholder="Enter Limit Price" path="limitPrice"
								disabled="true" onblur="checkLimit()"></form:input></td>
					</tr>
					<tr>
						<td><label>Stop Price:</label></td>
						<td style="padding-left: 32px;"><form:input type="number"
								class="form-control" id="stopPrice" name="stopPrice"
								placeholder="Enter Stop Price" path="stopPrice" disabled="true"
								onblur="checkStop()"></form:input></td>
					</tr>
					<tr>
						<td><label>Comments:</label></td>
						<td style="padding-left: 32px;"><textarea
								style="width: 200px; height: 50px;" class="form-control"></textarea></td>
					</tr>
				</tbody>
			</table>
			<br>
			<input type="submit" class="button" value="Submit" />
			<input type="reset" class="button" value="Reset!"
				style="float: right; margin-right: 30px;" />
		</form:form>
	</div>
</body>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	var securityName = "";
	document.getElementById("EquityName").placeholder = securityName;
	$("#security").autocomplete({
		source : "symbol_lookup2",
		minLength : 2,
		select : function(event, ui) {
			securityName = ui.item.name;
			document.getElementById("EquityName").value = securityName;
			document.getElementById("EquityLTP").value = ui.item.ltp + " Rs.";
		}
	});
</script>
<script type="text/javascript">
	function checkLimit() {
		var first = document.getElementById("EquityLTP");
		var second = document.getElementById("limitPrice");
		if (first.value > second.value) {
			alert("Invalid Limit Price");
			second.value = "";
			second.focus();
		}
	}
</script>
<script type="text/javascript">
	function checkStop() {
		var ltp = document.getElementById("EquityLTP");
		var stop = document.getElementById("stopPrice");
		var buy = document.getElementById("side1");
		var sell = document.getElementById("side2");
		
	
	sell.checked=true;
	
	}
</script>
</html>