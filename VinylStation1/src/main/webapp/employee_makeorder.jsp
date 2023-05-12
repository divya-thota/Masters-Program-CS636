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
	<div class="card-header text-center">Order ${invoice_id}</div>
	<div class='card-body'>
	<form action='./updateRecordTrackServlet' method='post'>
	<%
	HashMap<Integer, List<RecordTrack>> orderTracks = (HashMap<Integer, List<RecordTrack>>) request.getSession().getAttribute("orderTracks");
	for (Entry<Integer, List<RecordTrack>> entry : orderTracks.entrySet()) {
		out.print("<div class='card invoices'>");
		out.print("<div class='card-body'>");
		out.print("<h5 class='card-title'>Record #" + entry.getKey() + "</h5>");
		out.print("<table class='table table-bordered'><tbody>");
		for (RecordTrack objRecordTrack : entry.getValue()) {
			out.print("<tr><th scope='row'>" + objRecordTrack.get_track_name() + "</th>");
			out.print("<td>" + objRecordTrack.get_track_id()+ "</td>");
			out.print("<td><input name='recordtrackcheckbox' value="+objRecordTrack.get_record_track_id()+" type='checkbox'");
			if (objRecordTrack.get_added() == 1){
				out.print(" checked></td></tr>");
			} else {
				out.print("></td></tr>");
			}
		}
		out.print("</tbody></table>");
		out.print("</div></div>");
	}
	%>
	<div class="card-footer text-center">
	<form action="./updateRecordTrackServlet" method="post">
	<button type="button" class="btn btn-secondary" onclick="window.location.href='employee_dashboard.jsp'">Exit</button>
	<button type='submit' class='btn btn-warning'>Done</button>
	</form>
	</div>
	</form>
	</div>
	</div>
	</div>
</body>
</html>