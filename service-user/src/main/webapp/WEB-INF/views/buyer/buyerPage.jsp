<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Buyer Dashboard</title>

<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- FontAwesome CDN for icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">

<style>
body {
	background-color: #f8f9fa;
}

/* Navbar Styling */
.navbar {
	background-color: #1e88e5;
}

.navbar-brand {
	color: white !important;
}

.navbar-brand:hover {
	color: #ffcc00 !important;
}

/* Navbar Link Styling */
.navbar-nav .nav-link {
	color: white !important;
	font-size: 18px;
	margin-right: 20px;
}

.navbar-nav .nav-link:hover {
	color: #ffcc00 !important;
}

/* Product Card Styling */
.product-card {
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
	border: 2px solid #ddd;
	margin-top: 20px;
	border-color: green;
    border-width:3px;
}

.product-image {
	height:200px;
	object-fit: cover;
	border-radius: 10px;
	margin-bottom: 15px;
}

.product-details h5 {
	font-weight: bold;
	color: #333;
}

.product-details p {
	margin: 0;
	color: #555;
}

.price {
	color: #28a745;
	font-size: 18px;
	font-weight: bold;
}

/* Action Buttons Styling */
.action-buttons {
	display: flex;
	gap: 10px;
	justify-content: center;
	margin-top: 15px;
}

/* Wishlist Button */
.btn-wishlist {
	background-color: #ff5722;
	color: white;
	border: 2px solid #e64a19;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 16px;
	transition: background-color 0.3s ease;
}

.btn-wishlist:hover {
	background-color: #d84315;
}

/* Cart Button */
.btn-cart {
	background-color: #4caf50;
	color: white;
	border: 2px solid #388e3c;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 16px;
	transition: background-color 0.3s ease;
}

.btn-cart:hover {
	background-color: #388e3c;
}

.home-button {
	display: block;
	background-color: #0d47a1;
	color: white;
	padding: 10px 0;
	text-align: center;
	text-decoration: none;
	width: 150px;
	margin: 20px auto;
	border-radius: 5px;
	font-size: 18px;
	transition: background-color 0.3s ease;
}

.home-button:hover {
	background-color: #1565c0;
}
</style>
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #343a40;">
		<a class="navbar-brand" href="/buyerPage"> <i
			class="fas fa-tachometer-alt"></i> Buyer Dashboard
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<!-- Link to All Products -->
				<li class="nav-item"><a class="nav-link"
					href="/buyerAllProduct"> <i class="fas fa-box-open"></i> All
						Products
				</a></li>

				<!-- Link to View Orders -->
				<li class="nav-item"><a class="nav-link" href="/orders"> <i
						class="fas fa-shopping-bag"></i> View Orders
				</a></li>

				<!-- Link to Cart -->
				<li class="nav-item"><a class="nav-link" href="/cartPage">
						<i class="fas fa-shopping-cart"></i> Cart
				</a></li>

				<!-- Link to Wishlist -->
				<li class="nav-item"><a class="nav-link" href="/wishlist"> <i
						class="fas fa-heart"></i> Wishlist
				</a></li>

				<!-- Link to Logout -->
				<li class="nav-item"><a class="nav-link" href="/logout"> <i
						class="fas fa-sign-out-alt"></i> Logout
				</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">
		<!-- Loop through products and display each product as a card -->
		<div class="row">
			<c:forEach var="product" items="${products}">
				<div class="col-md-4">
					<div class="product-card">
						<!-- Product Image -->
						<img class="product-image" src="${product.imageurl}"
							alt="${product.name}">

						<!-- Product Details -->
						<div class="product-details">
							<h5>${product.name}</h5>
							<p>
								<strong>ID:</strong> ${product.id}
							</p>
							<p>
								<strong>Description:</strong> ${product.description}
							</p>
							<p>
								<strong>Category:</strong>
								<c:out value="${product.category.name}" default="Unknown" />
							</p>
							<p>
								<strong>SKU Code:</strong> ${product.skuCode}
							</p>
							<p class="price">&#8377 ${product.price}</p>
						</div>

						<!-- Action Buttons (Wishlist & Cart) -->
						<div class="action-buttons">
							<!-- Heart icon for wishlist -->
							<button class="btn-wishlist"
								onclick="toggleWishlist(${product.id}, '${product.name}', '${product.imageurl}', ${product.price})">
								<i class="fas fa-heart"></i>
							</button>

							<!-- Cart Button -->
							<button class="btn-cart"
								onclick="addToCart(${product.id}, ${product.price}, '${product.name}', '${product.imageurl}')">
								Add to Cart</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- jQuery and AJAX to Fetch Email -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
        let userId = null;  // Store the user ID dynamically

        $(document).ready(function() {
            // Fetch user email and ID from API
            $.ajax({
                url: 'http://localhost:8080/api/users',  // API URL
                type: 'GET',
                success: function(users) {
                    if (users.length > 0) {
                        $('#profile-link').text(users[0].email);
                        userId = users[0].id; // Store the user ID
                    } else {
                        $('#profile-link').text('No Profile');
                    }
                },
                error: function(error) {
                    console.log('Error fetching user email:', error);
                    $('#profile-link').text('Error');
                }
            });
        });

        function addToCart(productId, productPrice, productName, productImage) {
            if (userId === null) {
                alert("User not logged in.");
                return;
            }

            const quantity = 1; // Default quantity

            const cartData = {
                "userId": userId,
                "cartItems": [
                    {
                        "productId": productId,
                        "productName": productName,
                        "image": productImage,  // Send 'image' field as 'productImage'
                        "quantity": quantity,
                        "price": productPrice
                    }
                ]
            };

            // AJAX call to add product to cart
            $.ajax({
                url: 'http://localhost:8083/cart/add',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(cartData),
                success: function(response) {
                    alert('Product added to cart successfully!');
                },
                error: function(error) {
                    console.log('Error adding product to cart:', error);
                    alert('Failed to add product to cart.');
                }
            });
        }

        function toggleWishlist(productId, productName, productImage, productPrice) {
            let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

            const product = { id: productId, name: productName, image: productImage, price: productPrice };

            // Check if the product is already in the wishlist
            const existingProduct = wishlist.find(item => item.id === productId);

            if (existingProduct) {
                wishlist = wishlist.filter(item => item.id !== productId);
                alert('Product removed from wishlist.');
            } else {
                wishlist.push(product);
                alert('Product added to wishlist.');
            }

            localStorage.setItem('wishlist', JSON.stringify(wishlist));
        }

    </script>

	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
