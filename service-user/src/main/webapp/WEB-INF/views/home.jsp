<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Ensure the whole page is covered */
        html, body {
            height: 100%;
            margin: 0;
        }

        /* Flexbox container to push footer down */
        body {
            display: flex;
            flex-direction: column;
        }

        .container {
            flex: 1; /* This makes the container grow and push the footer down */
        }

        /* Navbar Styling */
        .navbar {
            background-color: #5bc0de;
        }

        .navbar-brand {
            color: white !important;
            font-weight: bold;
            font-size: 1.5rem;
        }

        .nav-link {
            color: white !important;
            font-size: 1.1rem;
        }

        .nav-link:hover {
            color: #d9534f !important;
        }

        .navbar-toggler {
            border: none;
        }

        .search-box {
            width: 50%;
        }

        /* Welcome Message */
        h1 {
            color: #0275d8;
            font-weight: bold;
        }

        p {
            color: #5a5a5a;
        }

        /* Section Styling */
        .colorful-section {
            background-color: #f8f9fa;
            padding: 40px 0;
            text-align: center;
        }

        .product-card {
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            border-radius: 10px;
            overflow: hidden;
        }

        .product-card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }

        .product-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .product-info {
            padding: 15px;
            background-color: #fff;
        }

        /* Carousel styling */
        .carousel-item img {
            height:400px;
            
            object-fit: cover;
        }

        /* Footer */
        footer {
            background-color: #292b2c;
            color: white;
            padding: 20px 0;
            text-align: center;
        }

        footer a {
            color: #d9534f;
        }

        footer a:hover {
            color: #5bc0de;
        }

        /* Mobile view adjustments */
        @media (max-width: 768px) {
            .search-box {
                width: 100%;
                margin-top: 10px;
            }
            .navbar-collapse {
                justify-content: space-between;
            }
        }
    </style>
</head>

<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="#">RevShop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home</a>
                </li>
            </ul>
            <form class="form-inline search-box mx-auto">
                <input class="form-control mr-sm-2 w-100" type="search" placeholder="Search for products..."
                    aria-label="Search">
            </form>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Register</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Main Content with carousel and product cards -->
    <div class="container mt-5">
        <h1 class="text-center">Welcome to RevShop!</h1>
        <p class="text-center">Browse through a variety of products and find the best deals.</p>

        <!-- Carousel Section -->
        <div id="productCarousel" class="carousel slide mb-5" data-ride="carousel">
            <div class="carousel-inner">
                
                <div class="carousel-item">
                    <img src="https://motorolaph.vtexassets.com/assets/vtex.file-manager-graphql/images/9c486c39-b5eb-421e-982a-2e7793d3b2a9___c528703c01a312c541a44bb126e2c99a.jpg" class="d-block w-100" alt="Product 2">
                </div>
                <div class="carousel-item">
                    <img src="https://image.made-in-china.com/226f3j00ackbZUYKCzqF/Portable-Wireless-HD-Sound-Bluetooth-Speakers-with-RGB-Lights.webp" class="d-block w-100" alt="Product 1">
                </div>
                <div class="carousel-item active">
                    <img src="https://static.vecteezy.com/system/resources/previews/011/871/820/non_2x/online-shopping-on-phone-buy-sell-business-digital-web-banner-application-money-advertising-payment-ecommerce-illustration-search-vector.jpg"  class="d-block w-100" alt="Product 3">
                </div>
                
                
                
            </div>
            <a class="carousel-control-prev" href="#productCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#productCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <!-- Product Cards Section -->
        <div class="colorful-section">
            <h2 class="mb-4">Featured Products</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card product-card">
                        <img src="https://motorolaph.vtexassets.com/assets/vtex.file-manager-graphql/images/c7a9f14a-eec7-49ef-9eb6-77c06db12f14___300eb68d0872427a6465c3779cacad7b.jpg" class="product-img" alt="Product 1">
                        <div class="product-info">
                            <h5>Mobile 5G</h5>
                            <p>Best quality product with amazing features.</p>
                            <a href="/login" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card product-card">
                        <img src="https://img.freepik.com/free-photo/still-life-books-versus-technology_23-2150062920.jpg" class="product-img" alt="Product 2">
                        <div class="product-info">
                            <h5>Laptop </h5>
                            <p>Stylish and affordable for everyone.</p>
                            <a href="/login" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card product-card">
                        <img src="https://5.imimg.com/data5/SELLER/Default/2022/2/LY/ES/ME/147129650/shrimad-bhagwat-geeta.png" class="product-img" alt="Product 3">
                        <div class="product-info">
                            <h5>Book</h5>
                            <p>Experience the life Lessons</p>
                            <a href="/login" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
