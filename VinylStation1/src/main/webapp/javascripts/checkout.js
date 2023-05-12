/**
 * 
 */

function placeOrder(){
	$.ajax({
		method:"POST",
		url:"placeOrderServlet",
		data:{cart:localStorage.getItem("cart")},
		success: function(data, textStatus, jqXHR){
			showToast("Order Placed");
			localStorage.clear();
			updatecart();
		}
	})
}

 function updatecart(){
	 let cart = JSON.parse(localStorage.getItem("cart"));
	 let total_price = JSON.parse(localStorage.getItem("total_price"));
	 $(".subtotal").html(total_price);
	 if (cart==null || cart.length==0){
		 $(".checkout-body").html("<h2>Cart is Empty<h2>");
	 }
	 else{
		 let tracklist = ``;
		 
		 cart.map((item) => {
			 tracklist+=`<div class="card invoices"><div class="card-body"><h5 class="card-title">${item.record_type_name}</h5>`;
			 let tracks=`<table class='table table-borderless table-sm'>
			 <thead>
			 	<tr><th scope="col">Trackname</th>
			 		<th scope="col">Quantity</th>
			 		<th scope="col">Price</th>
			 	</tr>
			 </thead><tbody>`
			 if (item.tracks){
				 for (let i=0 ; i< item.tracks.length; i++){
					 tracks+=`<tr><td>${item.tracks[i].track_name}</td>`
					 tracks+=`<td>${item.tracks[i].quantity}</td>`
					 tracks+=`<td>${item.tracks[i].price}</td></tr>`
				 }
			 }
			 tracks+=`</tbody></table>`;
			 tracklist+=tracks;
			 tracklist+=`</div></div>`;
		 })
		 
		 $(".checkout-body").html(tracklist);
	 }
 }
 
 $(document).ready(function (){
	updatecart();
})