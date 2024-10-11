<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Dashboard</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Navbar Styling */
        .navbar {
            background-color: #1e88e5;
        }
        .navbar-brand, .nav-link {
            color: white !important;
        }
        .navbar-brand:hover {
            color: #ffcc00 !important;
        }

        /* Dashboard Styling */
        .dashboard-content {
            margin-top: 50px;
        }

        /* Card Styles */
        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-radius: 15px;
        }
        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
        }

        /* Individual Card Designs */
        .card:nth-child(1) {
            background-color: #abebc6;
            border: 2px solid #e91e63;
        }

        .card:nth-child(2) {
            background-color: #c8e6c9;
            border: 2px solid #4caf50;
        }

        .card:nth-child(3) {
            background-color: #bbdefb;
            border: 2px solid #2196f3;
        }

        .card:nth-child(4) {
            background-color: #ffcc80;
            border: 2px solid #fb8c00;
        }

        .card:nth-child(5) {
            background-color: #f5f5f5;
            border: 2px solid #607d8b;
        }

        /* Button Styles */
        .btn {
            border-radius: 20px;
            transition: background-color 0.3s ease;
        }
        .btn-danger:hover {
            background-color: #c2185b;
        }
        .btn-success:hover {
            background-color: #388e3c;
        }
        .btn-info:hover {
            background-color: #1976d2;
        }
        .btn-primary:hover {
            background-color: #1565c0;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .card {
                margin-bottom: 30px;
            }
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">Seller Dashboard</a>
    </nav>

    <!-- Main Content -->
    <div class="container dashboard-content">
        <h1 class="text-center">Welcome, Seller!</h1>
        <p class="text-center">Manage your products and orders efficiently.</p>

        <div class="row">
            <!-- Add Category Card -->
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Add Category</h5>
                        <p class="card-text">Add new categories for your products.</p>
                        <a href="/addCategory" class="btn btn-danger">Add Category</a>
                    </div>
                </div>
            </div>

            <!-- Add Products Card -->
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Add Products</h5>
                        <p class="card-text">Add new products to your inventory.</p>
                        <a href="/addProduct" class="btn btn-success">Add Product</a>
                    </div>
                </div>
            </div>

            <!-- View All Products Card -->
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">All Products</h5>
                        <p class="card-text">View all products in your store.</p>
                        <a href="/allProduct" class="btn btn-info">View Products</a>
                    </div>
                </div>
            </div>

            <!-- View Orders Card -->
           <!--  <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">View Orders</h5>
                        <p class="card-text">Check all the orders placed by customers.</p>
                        <a href="/viewOrder" class="btn btn-primary">View Orders</a>
                    </div>
                </div>
            </div> -->

            <!-- Logout Card -->
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Logout</h5>
                        <p class="card-text">Logout from your account.</p>
                        <a href="/logout" class="btn btn-danger">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
