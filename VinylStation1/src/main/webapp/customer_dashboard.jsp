<html>

<head>
<title>Vinyl Station</title>
<%@include file="header_link.jsp"%>
<script type="text/javascript" src="javascripts/customer_dashboard.js"></script>
<script type="text/javascript">
//Load the Visualization API and the corechart package.
google.charts.load('current', { 'packages': ['corechart'] });

//Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

//Callback that creates and populates a data table,
//instantiates the pie chart, passes in the data and
//draws it.
function drawChart() {
	var TracknamevsPurchases = new Map(Object.entries(JSON.parse('${TracknamevsPurchases}')));
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Trackname');
	data.addColumn('number', 'Purchases');
	TracknamevsPurchases.forEach((value, key) => {
		data.addRow([key, value]);
	});
	var barchart_options = {
		title: 'Trending Music',
		width: 400,
		height: 300,
		legend: 'none',
		backgroundColor: '#262626',
		colors: ['#F79619'],
		titleTextStyle: {color: 'white', fontSize: 20},
		hAxis: {textStyle: {color: 'white', fontSize: 10}},
		vAxis: {textStyle: {color: 'white', fontSize: 10}},
	};
	var barchart = new google.visualization.BarChart(document.getElementById('chart_div'));
	barchart.draw(data, barchart_options);
}
</script>

</head>

<body>
	<%@include file="customer_header.jsp"%>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
			aria-labelledby="nav-home-tab">
			<div class="container">
				<div class="row">
					<div class="col">
					<div class="card">
					<div class="text-center">
					<h5>#1 ${firstTrack}</h5>
					<img src="assets/images/first.png" class="firsttrack">
					</div>
						
						</div>
					</div>
					<div class="col">
						<div id="chart_div" align='center'></div>
					</div>
				</div>
				<div class="row">
				<div class="col">
				<div class="card">
							<div class="card-header">Recent Orders:</div>
				
					<%
					List<Invoice> customerInvoicesDashboard = (ArrayList<Invoice>) request.getSession().getAttribute("customerInvoicesDashboard");
					for (Invoice objInvoice : customerInvoicesDashboard) {
						out.print("<div class='card'>");
						out.print("<div class='card-body'>");
						out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
						out.print("<table class='table table-borderless table-sm'><tbody>");
								out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
								out.print("</tbody></table>");
								out.print("</div></div>");
					}
					%>
				</div>
				</div>
				</div>
			</div>
		</div>
		
		<div class="tab-pane fade" id="nav-search" role="tabpanel"
			aria-labelledby="nav-search-tab">
			<div class="container">
				<div class="card invoices">
					<div class="card-header">Tracks</div>
					<!-- <form class="form-inline my-2 my-lg-0" action="./customerServlet"> -->
						<!-- <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchtext" onchange="">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
					<!-- </form> -->
					<button type='button' class='btn btn-warning' onclick="new_record()">New Record</button>
					<%
					List<Track> searchtracks = (ArrayList<Track>) request.getSession().getAttribute("searchtracks");
					for (Track objTrack : searchtracks) {
						out.print("<div class='card'>");
						out.print("<div class='card-body'>");
						out.print("<h5 class='card-title'>" + objTrack.get_track_name() + "</h5>");
						out.print("<div class='row'>");
						out.print("<div class='col'>");
						out.print("<table class='table table-borderless table-sm'><tbody><tr><th scope='row'>Track ID</th><td>"
						+ objTrack.get_track_id() + "</td></tr>");
						out.print("<tr><th scope='row'>Genre</th><td>" + objTrack.get_genre_id() + "</td></tr>");
						out.print("<tr><th scope='row'>Track Duration</th><td>" + objTrack.get_duration() + "</td></tr>");
						out.print("<tr><th scope='row'>Track Price</th><td>" + objTrack.get_price() + "</td></tr>");
						out.print("<tr><th scope='row'>Band ID</th><td>" + objTrack.get_band_id() + "</td></tr>");
						out.print("</tbody></table>");
						out.print("</div><div class='col text-center'>");
						out.print("<button type='button' class='btn btn-warning' onclick='add_to_record("+objTrack.get_track_id()+", \""+objTrack.get_track_name()+"\","+objTrack.get_genre_id()+","+objTrack.get_duration()+","+ objTrack.get_price() +","+ objTrack.get_band_id() +")'>Add to Record</button>");
						out.print("</div></div></div></div>");
					}
					%>
				</div>

			</div>
		</div>
		
		<div class="tab-pane fade" id="nav-orders" role="tabpanel"
			aria-labelledby="nav-orders-tab">
			<div class="container">
				<div class="card invoices">
					<div class="card-header">Orders</div>
					<%
					List<Invoice> customerInvoices = (ArrayList<Invoice>) request.getSession().getAttribute("customerInvoices");
					for (Invoice objInvoice : customerInvoices) {
						out.print("<div class='card'>");
						out.print("<div class='card-body'>");
						out.print("<h5 class='card-title'>" + objInvoice.get_invoice_id() + "</h5>");
						out.print("<table class='table table-borderless table-sm'><tbody>");
								out.print("<tr><th scope='row'>Invoice Date</th><td>" + objInvoice.get_invoice_date() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Status</th><td>" + objInvoice.get_status() + "</td></tr>");
								out.print("<tr><th scope='row'>Invoice Price</th><td>" + objInvoice.get_total_price() + "</td></tr>");
								out.print("</tbody></table>");
								out.print("</div></div>");
					}
					%>
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
									<th scope='row'>Customer ID:</th>
									<td>${customer.get_customer_id()}</td>
								</tr>
								<tr>
									<th scope='row'>First Name:</th>
									<td>${customer.get_first_name()}</td>
								</tr>
								<tr>
									<th scope='row'>Last Name:</th>
									<td>${customer.get_last_name()}</td>
								</tr>
								<tr>
									<th scope='row'>Email:</th>
									<td>${customer.get_email() }</td>
								</tr>
								<tr>
									<th scope='row'>Address:</th>
									<td>${customer.get_address()}</td>
								</tr>
								<tr>
									<th scope='row'>City:</th>
									<td>${customer.get_city()}</td>
								</tr>
								<tr>
									<th scope='row'>State:</th>
									<td>${customer.get_state()}</td>
								</tr>
								<tr>
									<th scope='row'>Postal Code:</th>
									<td>${customer.get_postal_code()}</td>
								</tr>
								<tr>
									<th scope='row'>Country:</th>
									<td>${customer.get_country()}</td>
								</tr>
								<tr>
									<th scope='row'>Credit Card Type:</th>
									<td>${customer.get_credit_card_type()}</td>
								</tr>
								<tr>
									<th scope='row'>Credit Card Number:</th>
									<td>${customer.get_credit_card_no()}</td>
								</tr>
								<tr>
									<th scope='row'>Credit Card Expiration Date:</th>
									<td>${customer.get_credit_card_expiration_date()}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
	</div>
	<%@include file="recordtype_modal.jsp" %>
	<%@include file="cart.jsp" %>
</body>
</html>