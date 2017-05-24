<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=''>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSS/stylepm.css">
<title>View Holdings</title>
<script type="text/javascript" src="JS/script.js"></script>
<script type="text/javascript" src="JS/sorttable.js"></script>
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
	<br>
	
	<form:form modelAttribute="portfolio">
		<form:select path="" id="select" class="form-control" name="select"
			style="margin-left: 2%; height: 30px; width: 250px;" onchange="doSubmit()">
			<form:option label="Select Portfolio" value=""></form:option>
			<form:option label="All" value="0"></form:option>
			<c:forEach var="listValue" items="${portfolio}">
				<form:option value="${listValue.portfolioId}"
					label="${listValue.portfolioName}">
				</form:option>
			</c:forEach>
		</form:select>
	</form:form>

	<div class="CSSTable">
		<table class="sortable">
			<caption>Holdings</caption>
			<thead>
				<tr class="filters">
					<th>Portfolio Name</th>
					<th>Security Symbol</th>
					<th>Quantity</th>
					<th>Average Price</th>

				</tr>
				</thead>
				<tbody>
				<c:forEach var="hold" items="${holdingsbyportfolio}">
					<tr>
						<td><c:out value="${hold.getPortfolio().getPortfolioName()}" /></td>
						<td><c:out value="${hold.getSecurity().getSecuritySymbol()}" /></td>
						<td><c:out value="${hold.getQuantity()}" /></td>
						<td><c:out value="${hold.getAveragePrice()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>