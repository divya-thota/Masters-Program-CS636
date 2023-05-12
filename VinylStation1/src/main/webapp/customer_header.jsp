<nav class="navbar navbar-expand-lg navbar-light header-background">
	<img src="assets/images/logo.jpg" width="30" height="30"
		class="d-inline-block align-top logo" alt="">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<nav>
			<div class="nav navbar-nav" id="nav-tab" role="tablist">
				<a class="nav-item nav-link active" id="nav-home-tab"
					data-toggle="tab" href="#nav-home" role="tab"
					aria-controls="nav-home" aria-selected="true">Home</a> 
				<a class="nav-item nav-link" id="nav-search-tab" data-toggle="tab"
					href="#nav-search" role="tab" aria-controls="nav-search"
					aria-selected="false">Search</a> 
				<a class="nav-item nav-link"
					id="nav-orders-tab" data-toggle="tab" href="#nav-orders" role="tab"
					aria-controls="nav-orders" aria-selected="false">Orders</a> 
				<a
					class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
					href="#nav-profile" role="tab" aria-controls="nav-profile"
					aria-selected="false">Profile</a>
			</div>
		</nav>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a class="nav-link" href="#" data-toggle="modal" data-target="#cartModal"> 
					<i class="fa fa-cart-plus" aria-hidden="true"></i> 
					<span class="cart-items"></span>
				</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="logoutServlet">Logout</a>
			</li>
		</ul>
	</div>
</nav>