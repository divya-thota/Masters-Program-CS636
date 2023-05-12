<html>
<head>
<title>Vinyl Station</title>
<%@include file="header_link.jsp"%>
<script type="text/javascript" src="javascripts/band_dashboard.js"></script>
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
	var yearvsPurchases = new Map(Object.entries(JSON.parse('${yearvsPurchases}')));
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Trackname');
	data.addColumn('number', 'Purchases');
	TracknamevsPurchases.forEach((value, key) => {
		data.addRow([key, value]);
	});

	var linedata = new google.visualization.DataTable();
	linedata.addColumn('string', 'Year');
	linedata.addColumn('number', 'Purchases');
	yearvsPurchases.forEach((value, key) => {
		linedata.addRow([key, value]);
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
	var line_options = {
		title: 'Track Record',
		width: 400,
		height: 300,
		legend: {position: 'bottom', textStyle: {color: 'white', fontSize: 16}},
		backgroundColor: '#262626',
		colors: ['#F79619'],
		titleTextStyle: {color: 'white', fontSize: 20},
		hAxis: {textStyle: {color: 'white', fontSize: 10}},
		vAxis: {textStyle: {color: 'white', fontSize: 10}},
	};
	var barchart = new google.visualization.BarChart(document.getElementById('chart_div'));
	barchart.draw(data, barchart_options);
	var linechart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	linechart.draw(linedata, line_options);
}
</script>

</head>
<body>
	<%@include file="band_header.jsp"%>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
			aria-labelledby="nav-home-tab">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header text-center">File Upload</div>
							<div class='card-body'>
							<form enctype="multipart/form-data" method="post" action="" id="attachfileform" name="attachfileform" >
								<div class="file-drop-area">
									<span class="choose-file-button">Choose files</span> 
									<span class="file-message">or drag and drop files here</span> 
									<input class="file-input" type="file" accept=".mp3,audio/*" name="trackfile">
								</div>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col">
						<div id="chart_div" align='center'></div>
					</div>
					<div class="col">
						<div id="line_chart_div" align='center'></div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-tracks" role="tabpanel"
			aria-labelledby="nav-tracks-tab">
			<div class="container">
				<div class="card invoices">
					<div class="card-header">Tracks</div>
					<%
					List<Track> bandTracks = (ArrayList<Track>) request.getSession().getAttribute("bandTracks");
					for (Track objTrack : bandTracks) {
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
						out.print("</tbody></table>");
						out.print("</div><div class='col text-center'>");
						out.print("<button type='button' class='btn btn-warning'>Delete Track</button>");
						out.print("</div></div></div></div>");
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
									<th scope='row'>Band ID:</th>
									<td>${band.get_band_id()}</td>
								</tr>
								<tr>
									<th scope='row'>Band Name:</th>
									<td>${band.get_name()}</td>
								</tr>
								<tr>
									<th scope='row'>Band Members:</th>
									<td>${band.get_band_members()}</td>
								</tr>
								<tr>
									<th scope='row'>Description</th>
									<td>${band.get_description() }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="create_track.jsp" %>
</body>
</html>