<html>
<head>
<title>Vinyl Station</title>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cs636.vinylstation.domain.Invoice"%>
<%@page import="cs636.vinylstation.domain.RecordTrack"%>
<link rel="stylesheet" type="text/css" href="assets/dashboard.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="employee_header.jsp"%>
	<div class="container">
		<div class="card invoices">
			<div class="card-header text-center">Order ${objInvoice.get_invoice_id()}</div>
			<div class='card-body'>
				<form action='./updatedStatusServlet' method='post'>
					<table class='table table-borderless'>
						<tbody>
							<tr>
								<th scope='row'>Invoice ID:</th>
								<td>${objInvoice.get_invoice_id()}</td>
							</tr>
							<tr>
								<th scope='row'>Customer ID:</th>
								<td>${objInvoice.get_customer_id()}</td>
							</tr>
							<tr>
								<th scope='row'>Invoice Date</th>
								<td>${objInvoice.get_invoice_date()}</td>
							</tr>
							<tr>
								<th scope='row'>Status:</th>
								<td><select name="status">
								<% Invoice objInvoice = (Invoice)request.getSession().getAttribute("objInvoice");
										if (objInvoice.get_status().equals("In Progress")){
											out.print("<option value=3 selected>In Progress</option>");
										} else {
											out.print("<option value=3>In Progress</option>");
										}
										if (objInvoice.get_status().equals("Out for Delivery")){
											out.print("<option value=4 selected>Out for Delivery</option>");
										}
										else {
											out.print("<option value=4>Out for Delivery</option>");
										}
										if (objInvoice.get_status().equals("Delivered")){
											out.print("<option value=5 selected>Delivered<>/option>");
										}
										else {
										out.print("<option value=5>Delivered</option>");
										}
								%>
								</select></td>
							</tr>
							<tr>
								<th scope='row'>Total Price:</th>
								<td>${objInvoice.get_total_price()}</td>
							</tr>
						</tbody>
					</table>
					<div class="card-footer text-center">
						<button type="button" class="btn btn-secondary"
							onclick="window.location.href='employee_dashboard.jsp'">Exit</button>
						<button type='submit' class='btn btn-warning'>Done</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>