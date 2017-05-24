function activateTab(pageId) {
	var tabCtrl = document.getElementById('tabCtrl');
	var pageToActivate = document.getElementById(pageId);
	for (var i = 0; i < tabCtrl.childNodes.length; i++) {
		var node = tabCtrl.childNodes[i];
		if (node.nodeType == 1) { /* Element */
			node.style.display = (node == pageToActivate) ? 'block' : 'none';

			var form = document.createElement("form");
			form.method = 'get';
			form.action = 'ViewOrdershome';
			var input = document.createElement('input');
			input.type = "text";
			input.name = "page";
			input.value = pageId;
			form.appendChild(input);
			form.submit();
		}
	}
}
function onBodyLoad(pageId) {
	var tabCtrl = document.getElementById('tabCtrl');
	var pageToActivate = document.getElementById(pageId);
	for (var i = 0; i < tabCtrl.childNodes.length; i++) {
		var node = tabCtrl.childNodes[i];
		if (node.nodeType == 1) { /* Element */
			node.style.display = (node == pageToActivate) ? 'block' : 'none';
		}
	}
}
function getvalue(radio_name, button_name) {
	var oRadio = document.getElementsByName(radio_name);
	for (var i = 0; i < oRadio.length; i++) {
		if (oRadio[i].checked) {
			oRadio[i].checked = false;
			if (button_name == "edit") {
				location.href = "EditOrder?id=" + oRadio[i].value;
			} else {
				location.href = "AmendOrder?id=" + oRadio[i].value;
			}
		}
	}
	console.log("HeyHey");
}

function checkTrader(radio_name) {
	table = document.getElementById("mytable1");
	rows = table.rows;
	console.log(rows);
	console.log(rows.length);
	for (var i = 0; i < rows.length; i++) {
		row = rows[i];
		console.log(row);
		col = row.cells[0];
		console.log(col);
		if (col.firstChild.checked) {
			console.log(row);
			value = row.cells[11].innerHTML;
			console.log(value);
			if (value == "") {

				var txt = confirm("Trader can't be null!! \n Please select the 'trader name' field in edit order page");
				if (txt == true) {
					location.href = "EditOrder?id=" + col.firstChild.value;}
				else{break;}

			}
			else {
				console.log(col.firstChild.value);
				sendToTraderConfirmation(col.firstChild.value);
			}
		}
	}
}


function sendToTraderConfirmation(order_id) {
	var txt = confirm("Confirm sending to trader??");
	if (txt == true) {
		location.href = "ViewOrdersAfterSendingToTrader?id=" + order_id;
	}
	else {
		alert("You have cancelled sending to trader");
	}
}

function cancelOrder(radio_name) {
	var oRadio = document.getElementsByName(radio_name);
	for (var i = 0; i < oRadio.length; i++) {
		if (oRadio[i].checked) {
			var txt = confirm("Confirm cancelling the order??");
			if (txt == true) {
				location.href = "ViewOrdersAfterCancel?id=" + oRadio[i].value;
			}
			else {
				break;
			}
			console.log(oRadio[i]);

		}
	}
}

function addNew()
{
	var txt=window.prompt("Enter portfolio name");
	if(txt!=null){
		window.alert(txt);
		location.href = "newPortfolio?id="+txt;
	}
	else{
		alert("You have not entered portfolio name");
	}
}


function selectType(value) {
	check = document.getElementById(value).value;
	if (check == "MARKET")
	{
		document.getElementById("limitPrice").innerHTML = 0.0;
		document.getElementById("stopPrice").innerHTML = 0.0;
		document.getElementById("limitPrice").disabled = true;
		document.getElementById("stopPrice").disabled= true;
		var buy = document.getElementById("side1");
		buy.style.visibility="hidden";
	} 
	else if (check == "LIMIT")
	{
		document.getElementById("limitPrice").disabled = false;
		document.getElementById("stopPrice").innerHTML = 0.0;
		document.getElementById("stopPrice").disabled = true;
		var buy = document.getElementById("side1");
		buy.style.visibility="hidden";
	} else 
	{
		document.getElementById("limitPrice").innerHTML=0.0;
		document.getElementById("limitPrice").disabled = true;
		document.getElementById("stopPrice").disabled = false;
	}
}

function doSubmit() {
	var pageId = document.getElementById("select").value;
	console.log(pageId);
	if (pageId != null) {
		location.href = "/InternalSystem/ViewPositionsbyportfolio?id=" + pageId;
	}

}
