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
<title>View Orders</title>
<script type="text/javascript" src="JS/script.js"></script>
<script type="text/javascript" src="JS/sorttable.js"></script>

</head>

<body onload="javascript:onBodyLoad('${pageToActivate}')">
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

	<div class="tabs">
		<ul class="tab-links">
			<li><a href="javascript:activateTab('page1')" class="button">New
					Orders</a></li>
			<li><a href="javascript:activateTab('page2')" class="button">Open
					Orders</a></li>
			<li><a href="javascript:activateTab('page3')" class="button">Partially
					Executed Orders</a></li>
			<li><a href="javascript:activateTab('page4')" class="button">Closed
					Orders</a></li>
			<li><a href="javascript:activateTab('page5')" class="button">Cancelled
					Orders</a></li>
		</ul>
		<div id="tabCtrl">
			<div id="page1" style="display: none;">
				<form>
					<table style="margin-left:30px">
						<tr>
							<td><input id="orderId" placeholder="Order ID" type="number"
								style="width: 150px; float: left; margin-left: 30px"
								class="form-control" /></td>
							<td><input class="button" type="button"
								onclick="getordersById('page1')" value="Filter" /></td>
						     <td style="float:left;margin-left:50px"><font color="red">X</font><a href="ViewOrdershome?page=page1"><b><font
                            color="red">Clear Filters</font></b></a></td>
						</tr>
					</table>
				</form>
				<form:form method="get" modelAttribute="neworders">
					<div class="CSSTable">
						<table id="mytable1" class="sortable">
							<caption>New Orders</caption>

							<thead>
								<tr>
									<th>Select</th>
									<th>Order ID</th>
									<th>Portfolio Name</th>
									<th>Security Name</th>
									<th>Side</th>
									<th>Type</th>
									<th>Qualifier</th>
									<th>Account Type</th>
									<th>Total Quantity</th>
									<th>Stop Price</th>
									<th>Limit Price</th>
									<th>Trader Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${neworders}" var="order">
									<tr>
										<td><input type="radio" name="orderid"
											id="${order.getOrderId()}" value="${order.getOrderId()}"></td>
										<td><c:out value="${order.getOrderId()}" /></td>
										<td><c:out
												value="${order.getPortfolio().getPortfolioName()}" /></td>
										<td><c:out
												value="${order.getSecurity().getSecuritySymbol()}" /></td>
										<td><c:out value="${order.getSide()}" /></td>
										<td><c:out value="${order.getType()}" /></td>
										<td><c:out value="${order.getQualifiers()}" /></td>
										<td><c:out value="${order.getAccountType()}" /></td>
										<td><c:out value="${order.getTotalQuantity()}" /></td>
										<td><c:out value="${order.getStopPrice()}" /></td>
										<td><c:out value="${order.getLimitPrice()}" /></td>
										<td><c:out
												value="${order.getEquityTrader().getUsername()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<div id='cssbutton'>
						<ul>
							<li><input type="button" class="button" value="Edit"
								name="edit" onclick="getvalue('orderid','edit')"></li>
							<li><input type="button" class="button" value="Cancel"
								name="Cancel" onclick="cancelOrder('orderid')"></li>
							<li><input type="button" class="button"
								value="Send To Trader" name="Send To Trader"
								onclick="checkTrader('orderid')"></li>
						</ul>
					</div>
					<div align="center" style="color: red">
						<c:out value="${message}" />
					</div>
				</form:form>

			</div>
			<div id="page2" style="display: none;">
			     <form>
                    <table style="margin-left:30px">
                        <tr>
                            <td><input id="orderId2" placeholder="Order ID" type="number"
                                style="width: 150px; float: left; margin-left: 30px"
                                class="form-control" /></td>
                            <td><input class="button" type="button"
                                onclick="getordersById2('page2')" value="Filter" /></td>
                             <td style="float:left;margin-left:50px"><font color="red">X</font><a href="ViewOrdershome?page=page2"><b><font
                            color="red">Clear Filters</font></b></a></td>
                        </tr>
                    </table>
                 </form>
			
				<div class="CSSTable">
					<form:form method="get" modelAttribute="openorders">
						<table id="mytable2" class="sortable">
							<caption>Open Orders</caption>
							<thead>
								<tr>
									<th>Select</th>
									<th>Order ID</th>
									<th>Portfolio Name</th>
									<th>Security Name</th>
									<th>Side</th>
									<th>Type</th>
									<th>Qualifier</th>
									<th>Account Type</th>
									<th>Total Quantity</th>
									<th>Stop Price</th>
									<th>Limit Price</th>
									<th>Trader Name</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${openorders}" var="order">
									<tr>
										<td><input type="radio" name="orderid"
											value="${order.getOrderId()}"></td>
										<td><c:out value="${order.getOrderId()}" /></td>
										<td><c:out
												value="${order.getPortfolio().getPortfolioName()}" /></td>
										<td><c:out
												value="${order.getSecurity().getSecuritySymbol()}" /></td>
										<td><c:out value="${order.getSide()}" /></td>
										<td><c:out value="${order.getType()}" /></td>
										<td><c:out value="${order.getQualifiers()}" /></td>
										<td><c:out value="${order.getAccountType()}" /></td>
										<td><c:out value="${order.getTotalQuantity()}" /></td>
										<td><c:out value="${order.getStopPrice()}" /></td>
										<td><c:out value="${order.getLimitPrice()}" /></td>
										<td><c:out
												value="${order.getEquityTrader().getUsername()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</form:form>
				</div>

				<div id='cssbutton'>
					<ul>
						<li><input type="button" class="button" value="Amend"
							name="amend" onclick="getvalue('orderid','amend')"></li>
						<li><input type="button" class="button" value="Cancel"
							name="Cancel" onclick="cancelOrder('orderid')"></li>
					</ul>
				</div>
			</div>
			<div id="page3" style="display: none;">
			  <form>
                    <table style="margin-left:30px">
                        <tr>
                            <td><input id="orderId3" placeholder="Order ID" type="number"
                                style="width: 150px; float: left; margin-left: 30px"
                                class="form-control" /></td>
                            <td><input class="button" type="button"
                                onclick="getordersById3('page3')" value="Filter" /></td>
                             <td style="float:left;margin-left:50px"><font color="red">X</font><a href="ViewOrdershome?page=page3"><b><font
                            color="red">Clear Filters</font></b></a></td>
                        </tr>
                    </table>
                 </form>
			 
				<div class="CSSTable">
					<form:form method="get" modelAttribute="partialorders">
						<table id="mytable3" class="sortable">
							<caption>Partially Executed Orders</caption>
							<thead>
								<tr>
									<th>Order ID</th>
									<th>Portfolio Name</th>
									<th>Security Name</th>
									<th>Side</th>
									<th>Type</th>
									<th>Qualifier</th>
									<th>Account Type</th>
									<th>Total Quantity</th>
									<th>Executed Quantity</th>
									<th>Stop Price</th>
									<th>Limit Price</th>
									<th>Trader Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${partialorders}" var="order">
									<tr>
										<td><c:out value="${order.getOrderId()}" /></td>
										<td><c:out
												value="${order.getPortfolio().getPortfolioName()}" /></td>
										<td><c:out
												value="${order.getSecurity().getSecuritySymbol()}" /></td>
										<td><c:out value="${order.getSide()}" /></td>
										<td><c:out value="${order.getType()}" /></td>
										<td><c:out value="${order.getQualifiers()}" /></td>
										<td><c:out value="${order.getAccountType()}" /></td>
										<td><c:out value="${order.getTotalQuantity()}" /></td>
										<td><c:out value="${order.getExecutedQuantity()}" /></td>
										<td><c:out value="${order.getStopPrice()}" /></td>
										<td><c:out value="${order.getLimitPrice()}" /></td>
										<td><c:out
												value="${order.getEquityTrader().getUsername()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="page4" style="display: none;">
			     <form>
                   <table style="margin-left:30px">
                        <tr>
                            <td><input id="orderId4" placeholder="Order ID" type="number"
                                style="width: 150px; float: left; margin-left: 30px"
                                class="form-control" /></td>
                            <td><input class="button" type="button"
                                onclick="getordersById4('page4')" value="Filter" /></td>
                             <td style="float:left;margin-left:50px"><font color="red">X</font><a href="ViewOrdershome?page=page4"><b><font
                            color="red">Clear Filters</font></b></a></td>
                        </tr>
                    </table>
                 </form>
				<div class="CSSTable">
					<form:form method="get" modelAttribute="completedorders">
						<table id="mytable4" class="sortable">
							<caption>Completed Orders</caption>
							<thead>
								<tr>
									<th>Order ID</th>
									<th>Portfolio Name</th>
									<th>Security Name</th>
									<th>Side</th>
									<th>Type</th>
									<th>Qualifier</th>
									<th>Account Type</th>
									<th>Total Quantity</th>
									<th>Stop Price</th>
									<th>Limit Price</th>
									<th>Trader Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${completedorders}" var="order">
									<tr>
										<td><c:out value="${order.getOrderId()}" /></td>
										<td><c:out
												value="${order.getPortfolio().getPortfolioName()}" /></td>
										<td><c:out
												value="${order.getSecurity().getSecuritySymbol()}" /></td>
										<td><c:out value="${order.getSide()}" /></td>
										<td><c:out value="${order.getType()}" /></td>
										<td><c:out value="${order.getQualifiers()}" /></td>
										<td><c:out value="${order.getAccountType()}" /></td>
										<td><c:out value="${order.getTotalQuantity()}" /></td>
										<td><c:out value="${order.getStopPrice()}" /></td>
										<td><c:out value="${order.getLimitPrice()}" /></td>
										<td><c:out
												value="${order.getEquityTrader().getUsername()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="page5" style="display: none;">
			      <form>
                   <table style="margin-left:30px">
                        <tr>
                            <td><input id="orderId5" placeholder="Order ID" type="number"
                                style="width: 150px; float: left; margin-left: 30px"
                                class="form-control" /></td>
                            <td><input class="button" type="button"
                                onclick="getordersById5('page5')" value="Filter" /></td>
                             <td style="float:left;margin-left:50px"><font color="red">X</font><a href="ViewOrdershome?page=page5"><b><font
                            color="red">Clear Filters</font></b></a></td>
                        </tr>
                    </table>
                 </form>
				<div class="CSSTable">
					<form:form method="get" modelAttribute="cancelledorders">
						<table id="mytable5" class="sortable">
							<caption>Cancelled Orders</caption>
							<thead>
								<tr>
									<th>Order ID</th>
									<th>Portfolio Name</th>
									<th>Security Name</th>
									<th>Side</th>
									<th>Type</th>
									<th>Qualifier</th>
									<th>Account Type</th>
									<th>Total Quantity</th>
									<th>Stop Price</th>
									<th>Limit Price</th>
									<th>Trader Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cancelledorders}" var="order">
									<tr>
										<td><c:out value="${order.getOrderId()}" /></td>
										<td><c:out
												value="${order.getPortfolio().getPortfolioName()}" /></td>
										<td><c:out
												value="${order.getSecurity().getSecuritySymbol()}" /></td>
										<td><c:out value="${order.getSide()}" /></td>
										<td><c:out value="${order.getType()}" /></td>
										<td><c:out value="${order.getQualifiers()}" /></td>
										<td><c:out value="${order.getAccountType()}" /></td>
										<td><c:out value="${order.getTotalQuantity()}" /></td>
										<td><c:out value="${order.getStopPrice()}" /></td>
										<td><c:out value="${order.getLimitPrice()}" /></td>
										<td><c:out
												value="${order.getEquityTrader().getUsername()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function getordersById(page){
    var id = document.getElementById("orderId").value;
    console.log("Order ID = " + id);
    location.href="getAvailableOrdersById?orderId=" + id+"&page="+page;
}
function getordersById2(page){
    var id = document.getElementById("orderId2").value;
    console.log("order ID=" + id);
    location.href="getAvailableOrdersById?orderId=" + id+"&page="+page;
}
function getordersById3(page){
    var id = document.getElementById("orderId3").value;
    console.log("order ID=" + id);
    location.href="getAvailableOrdersById?orderId=" + id+"&page="+page;
}
function getordersById4(page){
    var id = document.getElementById("orderId4").value;
    console.log("order ID=" + id);
    location.href="getAvailableOrdersById?orderId=" + id+"&page="+page;
}
function getordersById5(page){
    var id = document.getElementById("orderId5").value;
    console.log("order ID=" + id);
    location.href="getAvailableOrdersById?orderId=" + id+"&page="+page;
}
</script>
</html>