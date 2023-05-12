$(document).on('change', '.file-input', function() {
  var filesCount = $(this)[0].files.length;
  var textbox = $(this).prev();

  if (filesCount === 1) {
    var fileName = $(this).val().split('\\').pop();
    textbox.text(fileName);
  } else {
    textbox.text(filesCount + ' files selected');
  }
  $('#createTrackModal').modal('show');
});


function createTrack(){
	var ajaxfile = $(".file-input");
	var trackname = document.getElementById("trackname").value;
	var genre = document.getElementById("genre").value;
	var url = ajaxfile.val();
	var duration = document.getElementById("duration").value;
	var price = document.getElementById("price").value;
	$.ajax({
		method:"POST",
		url:"createTrackServlet",
		data:{trackname:trackname, genre:genre, url:url, duration:duration, price:price},
		success: function(data, textStatus, jqXHR){
			$('#createTrackModal').modal('hide');
			showToast("Track created");
		}
	})
}