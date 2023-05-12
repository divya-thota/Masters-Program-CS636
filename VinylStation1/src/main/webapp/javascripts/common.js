/**
 * 
 */
 function showToast(content){
	$('#toast').addClass("display");
	$('#toast').html(content+`<div class="d-inline float-right" onclick="hideToast()"><i class="fa fa-times" aria-hidden="true"></i></div>`);
	setTimeout(()=>{
		$('#toast').removeClass("display");
		},2000);
}

function hideToast() {
	$('#toast').removeClass("display");
}