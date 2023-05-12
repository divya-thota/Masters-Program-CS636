<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cs636.vinylstation.domain.Genre"%>

<div class="modal fade" id="createTrackModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New Track</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="./createTrackServlet">
				<div class="modal-body">
					<label>Track Name</label> 
					<input class="form-control" type="text" placeholder="Track Name" id="trackname">
					<label>Genre</label> 
					<select id="genre" class="custom-select">
						<%
						List<Genre> genreList = (ArrayList<Genre>)request.getSession().getAttribute("genreList");
						for (Genre objGenre : genreList) {
							out.print("<option value="+objGenre.get_genre_id()+">"+objGenre.get_genre_name()+"</option>");
						}
						%>
					</select>
					<label>Duration</label> 
					<input class="form-control" type="number" step=".01" placeholder="Duration" id="duration"> 
					<label>Price</label> 
					<input class="form-control" type="number" step=".01" placeholder="Price" id="price">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" onclick="createTrack()" class="btn btn-warning">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>