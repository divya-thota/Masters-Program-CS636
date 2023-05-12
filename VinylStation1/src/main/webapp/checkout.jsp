<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Vinyl Station</title>
<%@include file="header_link.jsp"%>
<script type="text/javascript" src="javascripts/checkout.js"></script>
</head>

<body>
	<%@include file="customer_header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-8">
			<div class="card">
			<div class="card-body">
			<div class="checkout-body"></div>
			</div></div>
			</div>
			<div class="col-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Shipping Address:</h5>
						<p class="card-text">
						${customer.get_address()} 
						${customer.get_city()} 
						${customer.get_state()} 
						${customer.get_postal_code()} 
						${customer.get_country()}</p>
					</div>
				</div>
				<div class="card invoices">
					<div class="card-body">
						<h5 class="card-title">Payment Details:</h5>
						<p class="card-text">
							Credit Card Type:
							${customer.get_credit_card_type()} </br>
							Credit Card Number:
							${customer.get_credit_card_no()} </br>
							Credit Card Expiration Date:
							${customer.get_credit_card_expiration_date()}
						</p>
					</div>
				</div>
				<div class="card invoices">
				<div class="card-body">
				<table class="table table-borderless"><tbody>
				<tr><th scope='row'>Subtotal :</th><td class="subtotal"></td></tr>
				<tr><th scope='row'>Shipping :</th><td>0.00</td></tr>
				<tr><th scope='row'>Sales Tax :</th><td>0.00</td></tr>
				<tr><th scope='row'>Total :</th><td class="subtotal"></td></tr>
				</tbody></table>
				<form">
				<button type='button' class='btn btn-warning w-100' onclick="placeOrder()">Confirm Order</button>
				</form>
				<form action="./customerServlet">
				<button type="submit" class="btn btn-secondary w-100 mt-1" data-dismiss="modal">Exit</button>
				</form>
				</div>
				</div>
			</div>

		</div>
	</div>
</body>
	
</html>