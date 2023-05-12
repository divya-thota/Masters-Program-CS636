<html>
<head>
<title>Vinyl Station</title>
<%@include file="header_link.jsp"%>
<script type="text/javascript" src="javascripts/employee_dashboard.js"></script>
<script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
        var EmployeenamevsPerformance = new Map(Object.entries(JSON.parse('${EmployeenamevsPerformance}')));
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Employeename');
        data.addColumn('number', 'Performance');
        EmployeenamevsPerformance.forEach((value,key) => {
            data.addRow([key,value]);
        });
        var options = {title:'Employee Performance',
                width:400,
                height:300,
                backgroundColor: 'transparent',
                is3D: true,
                titleTextStyle: {color: 'white', fontSize: 20},
                legend: 'none'};
	 var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	 chart.draw(data, options);
      }
    </script>
</head>
<body>
	<%@include file="employee_header.jsp"%> 
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
			aria-labelledby="nav-home-tab">
			<div class="container">
				<div class="row">
					<div class="col-sm">
						<div class="card progress-table">
							<div class="card-header text-center">Your Progress:</div>
							<div class="card-body text-center">
							<table class='table table-bordered progress-table'>
								<%
								Map<String, Integer> monthvsPerformance = (HashMap<String, Integer>) request.getSession()
										.getAttribute("monthvsPerformance");
								for (Entry<String, Integer> entry : monthvsPerformance.entrySet()) {
									out.print("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
								}
								%>
							</table>
							</div>
						</div>
					</div>
					<div class="col-sm">
						<div id="chart_div" align='center'></div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header">Pending Orders:</div>
							<%
							List<Invoice> pendingInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("pendingInvoices");
							for (Invoice objInvoice : pendingInvoices) {
								out.print("<div class='card'>");
								out.print("<div class='card-body'>");
								out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
								out.print("<div class='row'>");
								out.print("<div class='col'>");
								out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Customer ID</th><td>"
								+ objInvoice.get_customer_id() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
								out.print("</tbody></table>");
								out.print("</div><div class='col text-center'>");
								out.print("<form action='./makeOrderServlet'>");
								out.print("<input type='hidden' name='invoice_id' value="+ objInvoice.get_invoice_id() +">");
								out.print("<button type='submit' class='btn btn-warning'>Make Order</button>");
								out.print("</form></div></div></div></div>");
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-invoices" role="tabpanel"
			aria-labelledby="nav-invoices-tab">
			<div class="container">
				<div class="row">
					<div class="col-3">
						<div class="card">
							<div class="nav flex-column nav-pills" id="v-pills-tab"
								role="tablist" aria-orientation="vertical">
								<a class="nav-link active" id="v-pills-all-tab"
									data-toggle="pill" href="#v-pills-all" role="tab"
									aria-controls="v-pills-all" aria-selected="true">All
									Invoices</a> 
								<a class="nav-link" id="v-pills-in-progress-tab"
									data-toggle="pill" href="#v-pills-in-progress" role="tab"
									aria-controls="v-pills-in-progress" aria-selected="false">In Progress</a>
								<a class="nav-link" id="v-pills-out-for-delivery-tab" data-toggle="pill"
									href="#v-pills-out-for-delivery" role="tab"
									aria-controls="v-pills-out-for-delivery" aria-selected="false">Out for Delivery</a>
								<a class="nav-link" id="v-pills-delivered-tab" data-toggle="pill"
									href="#v-pills-delivered" role="tab"
									aria-controls="v-pills-delivered" aria-selected="false">Delivered</a>
							</div>
						</div>
					</div>
					<div class="col-9">
						<div class="tab-content" id="v-pills-tabContent">
							<div class="tab-pane fade show active" id="v-pills-all"
								role="tabpanel" aria-labelledby="v-pills-all-tab">
								<%
								List<Invoice> employeeInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("employeeInvoices");
								for (Invoice objInvoice : employeeInvoices) {
									out.print("<div class='card'>");
									out.print("<div class='card-body'>");
									out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
									out.print("<div class='row'>");
									out.print("<div class='col'>");
									out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Customer ID</th><td>"
									+ objInvoice.get_customer_id() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
									out.print("</tbody></table>");
									out.print("</div><div class='col text-center'>");
									out.print("<form action='./makeOrderServlet'>");
									out.print("<input type='hidden' name='invoice_id' value=" + objInvoice.get_invoice_id() + ">");
									out.print("<button type='submit' class='btn btn-warning'>Edit Order</button>");
									out.print("</form></div></div></div></div>");
								}
								%>
							</div>
							<div class="tab-pane fade" id="v-pills-in-progress" role="tabpanel"
								aria-labelledby="v-pills-in-progress-tab"><%
								List<Invoice> inProgressInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("inProgressInvoices");
								for (Invoice objInvoice : inProgressInvoices) {
									out.print("<div class='card'>");
									out.print("<div class='card-body'>");
									out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
									out.print("<div class='row'>");
									out.print("<div class='col'>");
									out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Customer ID</th><td>"
									+ objInvoice.get_customer_id() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
									out.print("</tbody></table>");
									out.print("</div><div class='col text-center'>");
									out.print("<form action='./updateInvoiceServlet'>");
									out.print("<input type='hidden' name='invoice_id' value=" + objInvoice.get_invoice_id() + ">");
									out.print("<button type='submit' class='btn btn-warning'>Update Status</button>");
									out.print("</form></div></div></div></div>");
								}
								%></div>
							<div class="tab-pane fade" id="v-pills-out-for-delivery" role="tabpanel"
								aria-labelledby="v-pills-out-for-delivery-tab"><%
								List<Invoice> outForDeliveryInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("outForDeliveryInvoices");
								for (Invoice objInvoice : outForDeliveryInvoices) {
									out.print("<div class='card'>");
									out.print("<div class='card-body'>");
									out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
									out.print("<div class='row'>");
									out.print("<div class='col'>");
									out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Customer ID</th><td>"
									+ objInvoice.get_customer_id() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
									out.print("</tbody></table>");
									out.print("</div><div class='col text-center'>");
									out.print("<form action='./updateInvoiceServlet'>");
									out.print("<input type='hidden' name='invoice_id' value=" + objInvoice.get_invoice_id() + ">");
									out.print("<button type='submit' class='btn btn-warning'>Update Status</button>");
									out.print("</form></div></div></div></div>");
								}
								%></div>
							<div class="tab-pane fade" id="v-pills-delivered" role="tabpanel"
								aria-labelledby="v-pills-delivered-tab"><%
								List<Invoice> DeliveredInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("DeliveredInvoices");
								for (Invoice objInvoice : DeliveredInvoices) {
									out.print("<div class='card'>");
									out.print("<div class='card-body'>");
									out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
									out.print("<div class='row'>");
									out.print("<div class='col'>");
									out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Customer ID</th><td>"
									+ objInvoice.get_customer_id() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
									out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
									out.print("</tbody></table>");
									out.print("</div><div class='col text-center'>");
									out.print("<form action='./updateInvoiceServlet'>");
									out.print("<input type='hidden' name='invoice_id' value=" + objInvoice.get_invoice_id() + ">");
									out.print("<button type='submit' class='btn btn-warning'>Update Status</button>");
									out.print("</form></div></div></div></div>");
								}
								%></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-profile" role="tabpanel"
			aria-labelledby="nav-profile-tab">
			<div class="container">
				<div class="card invoices">
					<div class="card-header">Profile Information</div>
					<div class="card-body">
						<table class='table table-borderless'>
							<tbody>
								<tr>
									<th scope='row'>Employee ID:</th>
									<td>${employee.get_employee_id()}</td>
								</tr>
								<tr>
									<th scope='row'>First Name:</th>
									<td>${employee.get_first_name()}</td>
								</tr>
								<tr>
									<th scope='row'>Last Name:</th>
									<td>${employee.get_last_name()}</td>
								</tr>
								<tr>
									<th scope='row'>Email:</th>
									<td>${employee.get_email() }</td>
								</tr>
								<tr>
									<th scope='row'>Address:</th>
									<td>${employee.get_address()}</td>
								</tr>
								<tr>
									<th scope='row'>City:</th>
									<td>${employee.get_city()}</td>
								</tr>
								<tr>
									<th scope='row'>State:</th>
									<td>${employee.get_state()}</td>
								</tr>
								<tr>
									<th scope='row'>Postal Code:</th>
									<td>${employee.get_postal_code()}</td>
								</tr>
								<tr>
									<th scope='row'>Country:</th>
									<td>${employee.get_country()}</td>
								</tr>
								<tr>
									<th scope='row'>Phone Number:</th>
									<td>${employee.get_phone_no()}</td>
								</tr>
								<tr>
									<th scope='row'>Date Of Birth:</th>
									<td>${employee.get_date_of_birth()}</td>
								</tr>
								<tr>
									<th scope='row'>Role:</th>
									<td>${employee.get_role()}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>