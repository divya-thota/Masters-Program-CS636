<!DOCTYPE html>
<html>
<head>
<title>Vinyl Station</title>
<link rel="stylesheet" type="text/css" href="assets/create_customer.css">
</head>
<body>
		<div class="loginbox">
			<img src="assets/images/logo.jpg" class="logo">
			<h1>New Customer</h1>
			<form action="./createCustomerServlet" method="post">
				<span id="errormessage">${error}</span>
				<p>First Name</p>
				<input type="text" id="firstname" name="firstname"
					placeholder="Your first name..">
				<p>Last Name</p>
				<input type="text" id="lastname" name="lastname"
					placeholder="Your last name..">
				<p>Email Address</p>
				<input type="email" id="email" name="email"
					placeholder="Your email address..">
				<p>Password</p>
				<input type="password" id="password" name="password"
					placeholder="Your password..">
				<p>Address</p>
				<input type="text" id="address" name="address"
					placeholder="Your address..">
				<p>City</p>
				<input type="text" id="city" name="city" placeholder="Your city..">
				<p>State</p>
				<input type="text" id="state" name="state"
					placeholder="Your state..">
				<p>Postal Code</p>
				<input type="text" id="postal_code" name="postal_code"
					placeholder="Your postal code..">
				<p>Country</p>
				<input type="text" id="country" name="country"
					placeholder="Your country..">
				<p>Credit Card Type</p>
				<select id="credit_card_type" name="credit_card_type">
					<option value="Visa">Visa</option>
					<option value="Mastercard">Mastercard</option>
					<option value="American Express">American Express</option>
					<option value="Discover">Discover</option>
				</select>
				<p>Credit Card Number</p>
				<input type="number" id="credit_card_no" name="credit_card_no"
					placeholder="Your credit card number...">
				<p>Credit Card Expiration Date</p>
				<input type="date" id="credit_card_expiration_date"
					name="credit_card_expiration_date"
					placeholder="Your credit card expiration date.."> <input
					type="submit" value="Submit">
			</form>
		</div>
</body>
</html>