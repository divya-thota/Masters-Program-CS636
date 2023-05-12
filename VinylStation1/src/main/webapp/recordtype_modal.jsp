<script type="text/javascript" src="javascripts/customer_dashboard.js"></script>
<%@include file="header_link.jsp"%>
<div class="modal fade" id="recordTypeModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Choose Record Type</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			
				<div class="modal-body">
				<%
					List<RecordType> allrecordtypes = (ArrayList<RecordType>) request.getSession().getAttribute("allrecordtypes");
					for (RecordType objRecordType : allrecordtypes) {
						out.print("<button type='button' class='btn btn-warning recordtype my-1' onclick='add_to_cart("+objRecordType.get_record_type_id()+",\""+objRecordType.get_record_type_name()+"\","+objRecordType.get_duration()+")' >"+objRecordType.get_record_type_name()+"</button>");
					}
				%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>