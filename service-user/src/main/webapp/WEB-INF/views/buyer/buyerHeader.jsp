<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buyer Dashboard</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- FontAwesome CDN for icons -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

<style>

body {
    background-color: #f8f9fa;
}

/* Navbar Styling */
.navbar {
    background-color: #1e88e5 ; /* Primary background color */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.navbar-brand {
    color: #ffffff !important;
    font-size: 22px;
    font-weight: bold;
}

.navbar-brand:hover {
    color: #ffcc00 !important; /* Yellow hover effect */
}

/* Navbar Link Styling */
.navbar-nav .nav-link {
    color: #ffffff !important;
    font-size: 18px;
    font-weight: 500;
    margin-right: 20px;
    transition: color 0.3s ease;
}

.navbar-nav .nav-link:hover {
    color: #ffcc00 !important; /* Yellow hover effect */
    font-weight: 600;
}

/* Active State for Links */
.navbar-nav .nav-link:active {
    color: #00e676 !important; /* Green color on click */
    font-weight: bold;
}

/* Custom Button for the Cart Link */
.navbar-nav .nav-link-cart {
    color: #ffffff !important;
    background-color: #f50057;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.navbar-nav .nav-link-cart:hover {
    background-color: #ff4081 !important; /* Pink hover effect */
    color: #ffffff !important;
}

.navbar-nav .nav-link-profile {
    color: #ffffff !important;
    background-color: #673ab7;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.navbar-nav .nav-link-profile:hover {
    background-color: #9575cd !important;
    color: #ffffff !important;
}

</style>

</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #273746;">
    <a class="navbar-brand" href="/buyerPage">
        <i class="fas fa-tachometer-alt"></i> Buyer Dashboard
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <!-- Link to All Products -->
            <li class="nav-item">
                <a class="nav-link" href="/buyerAllProduct">
                    <i class="fas fa-box-open"></i> All Products
                </a>
            </li>

            <!-- Link to View Orders -->
            <li class="nav-item">
                <a class="nav-link" href="/orders">
                    <i class="fas fa-shopping-bag"></i> View Orders
                </a>
            </li>

            <!-- Link to Cart -->
            <li class="nav-item">
                <a class="nav-link nav-link-cart" href="/cartPage">
                    <i class="fas fa-shopping-cart"></i> Cart
                </a>
            </li>

            <!-- Link to Wishlist -->
            <!-- <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fas fa-heart"></i> Wishlist
                </a>
            </li> -->

           

            <!-- Link to Logout -->
            <li class="nav-item">
                <a class="nav-link" href="/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
        </ul>
    </div>
</nav>



<!-- Bootstrap JS for responsiveness -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
