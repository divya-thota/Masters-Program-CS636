/*function add_to_cart (customer_id, record_type, record_type_duration){
	$('#recordTypeModal').modal('hide');
	let cart = localStorage.getItem("cart");
	if (cart==null){
		let records=[];
		let record = {record_type: record_type,record_type_duration: record_type_duration, duration:0.00, quantity:0, price:0.00};
		records.push(record);
		cart = [{customer_id: customer_id , cart_price: 0.00, records:records}];
		localStorage.setItem("cart", JSON.stringify(cart));
	} else {
		let cartlist=JSON.parse(cart);
		let customercart = cartlist.find((customer) => customer.customer_id == customer_id);
		let record = {record_type: record_type, record_type_duration: record_type_duration, duration:0.00, quantity:0, price:0.00, };
		customercart.records.push(record);
		localStorage.setItem("cart", JSON.stringify(cartlist));
	}
	update_cart();
}*/

function add_to_cart (record_type_id, record_type_name, record_type_duration){
	$('#recordTypeModal').modal('hide');
	let cart = localStorage.getItem("cart");
	if (cart==null){
		let records=[];
		let record = {record_type_id:record_type_id, record_type_name: record_type_name,record_type_duration: record_type_duration, duration:0.00, quantity:0, price:0.00};
		records.push(record);
		localStorage.setItem("cart", JSON.stringify(records));
		localStorage.setItem("total_price",0.0);
	} else {
		let pcart=JSON.parse(cart);
		let record = {record_type_id: record_type_id,record_type_name:record_type_name, record_type_duration: record_type_duration, duration:0.00, quantity:0, price:0.00, };
		pcart.push(record);
		localStorage.setItem("cart", JSON.stringify(pcart));
	}
	update_cart();
}

function update_cart(){
	let cart = JSON.parse(localStorage.getItem("cart"));
	if (cart==null || cart.length==0){
		$(".cart-items").html("(0)");
		$(".cart-body").html("<h2>Cart is Empty!<h2>");
	} else {
		$(".cart-items").html(`(${cart.length})`);
		let tracklist = ``;
		 
		 cart.map((item,index) => {
			 tracklist+=`<div class="card invoices"><div class="card-body"><h5 class="card-title">${item.record_type_name}<div class="d-inline float-right" onclick=delete_record(${index})><i class="fa fa-trash" aria-hidden="true"></i></div></h5>`;
			 let tracks=`<table class='table table-borderless table-sm'>
			 <thead>
			 	<tr><th scope="col">Trackname</th>
			 		<th scope="col">Duration</th>
			 		<th scope="col">Quantity</th>
			 		<th scope="col">Price</th>
			 	</tr>
			 </thead><tbody>`
			 if (item.tracks){
				 for (let i=0 ; i< item.tracks.length; i++){
					 tracks+=`<tr><td>${item.tracks[i].track_name}</td>`
					 tracks+=`<td>${item.tracks[i].duration}</td>`
					 tracks+=`<td>${item.tracks[i].quantity}</td>`
					 tracks+=`<td>${item.tracks[i].price}</td></tr>`
					 
				 }
			 }
			 
			 tracks+=`<tr><th scope="col">Total</th>
			 		<th scope="col">${item.duration}</th>
			 		<th scope="col">${item.quantity}</th>
			 		<th scope="col">${item.price}</th>
			 	</tr></tbody></table>`;
			 tracklist+=tracks;
			 tracklist+=`</div></div>`;
		 })
		 
		 $(".cart-body").html(tracklist);
	}
}

function delete_record(index){
	let cart = JSON.parse(localStorage.getItem('cart'));
	total_price = Math.round((parseFloat(localStorage.getItem("total_price")) - cart[index].price) * 100) / 100;
	localStorage.setItem("total_price",total_price);
	cart.splice(index,1);
	localStorage.setItem("cart", JSON.stringify(cart));
	update_cart();
}

//function delete_track(track_id){
//}

function add_to_record(track_id, track_name, genre_id, duration, price, band_id) {
	let cart = JSON.parse(localStorage.getItem("cart"));
	if (cart==null || cart.length == 0){
		$('#recordTypeModal').modal('show');
		return;
	} else {
		let cartitem= cart[cart.length - 1];
		if(cartitem.duration+duration > cartitem.record_type_duration){
			alert("Duration Exceeded");
			$('#recordTypeModal').modal('show');
		}
		cartitem.duration += duration;
		cartitem.duration = Math.round(cartitem.duration * 100) / 100
		cartitem.price += price;
		cartitem.price = Math.round(cartitem.price * 100) / 100
		total_price = Math.round((parseFloat(localStorage.getItem("total_price")) + price) * 100) / 100;
		localStorage.setItem("total_price",total_price);
		cartitem.quantity += 1;
		if (cartitem.tracks == null) {
			let tracklist = [];
			let track = {track_id: track_id, track_name: track_name, genre_id: genre_id, duration: duration, price: price, band_id: band_id, quantity:1};
			tracklist.push(track);
			cartitem.tracks = tracklist;
			cart[cart.length - 1] = cartitem;
			localStorage.setItem("cart", JSON.stringify(cart));
		} else {
			let oldTrack = cartitem.tracks.find((track) => track.track_id == track_id);
			if (oldTrack) {
				oldTrack.quantity+=1;
				cartitem.tracks.map((track) => {
					if(track.track_id == oldTrack.track_id){
						track.quantity = oldTrack.quantity;
					}
				});
				localStorage.setItem("cart", JSON.stringify(cart));
			} else {
				let track = {track_id: track_id, track_name: track_name, genre_id: genre_id, duration: duration, price: price, band_id: band_id, quantity: 1};
				cartitem.tracks.push(track);
				localStorage.setItem("cart", JSON.stringify(cart));
			}
			
		}
	}
	update_cart();
	showToast("Track \""+track_name+"\" added!");
}

function new_record(){
	$('#recordTypeModal').modal('show');
}

$(document).ready(function (){
	update_cart();
})
	
$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	switch (e.target.id){
		case "nav-search-tab":{
			$('#recordTypeModal').modal('show');
			break;
		}
	}
})


