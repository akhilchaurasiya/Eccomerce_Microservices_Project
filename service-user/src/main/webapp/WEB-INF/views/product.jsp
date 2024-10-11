<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>">
    <style>
    /* Body Styling */
body {
    background-color: #f8f9fa; /* Light background */
    font-family: 'Roboto', sans-serif; /* Modern font */
    color: #333; /* Dark text color */
}

/* Header Styling */
h2 {
    color: #343a40;
    font-weight: 700;
    margin-bottom: 30px;
}

/* Product Card Styling */
.card {
    border: none; /* Remove card border */
    transition: transform 0.3s ease, box-shadow 0.3s ease; /* Smooth hover effect */
}

.card:hover {
    transform: translateY(-10px); /* Slight lift on hover */
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1); /* Subtle shadow on hover */
}

/* Product Image Styling */
.card-img-top {
    max-height: 180px;
    object-fit: contain; /* Ensure image fits within card without distortion */
    background-color: #f1f1f1; /* Light background for images */
    padding: 10px; /* Add padding around images */
}

/* Card Body Styling */
.card-title {
    font-size: 18px;
    font-weight: 600;
    color: #007bff; /* Product name in blue */
}

.card-text {
    font-size: 14px;
    color: #6c757d; /* Description and category in muted color */
}

.card-text strong {
    color: #333; /* Price in darker color */
}

/* Card Footer Styling */
.card-footer {
    background-color: #ffffff; /* White background for footer */
    border-top: none; /* Remove footer border */
}

/* Button Styling */
.btn-primary {
    background-color: #007bff;
    border-color: #007bff;
    font-weight: 600;
    transition: background-color 0.3s ease;
}

.btn-primary:hover {
    background-color: #0056b3; /* Darker blue on hover */
}

.btn-info {
    background-color: #17a2b8;
    border-color: #17a2b8;
    font-weight: 600;
}

.btn-info:hover {
    background-color: #138496; /* Darker info color on hover */
}

.btn-outline-danger {
    border-color: #dc3545;
    color: #dc3545;
    font-weight: 600;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-outline-danger:hover {
    background-color: #dc3545;
    color: #fff; /* White text on hover */
}

/* Grid and Spacing */
.row {
    margin-bottom: 30px; /* Space between rows */
}

.card-body {
    padding: 20px;
}

.card-footer .btn {
    width: 30%; /* Ensure buttons fit nicely */
}
    
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Product List</h2>

    <!-- Product Tiles (Bootstrap Cards) -->
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-3 mb-4">
                <div class="card h-100 shadow-sm">
                    <img src="${product.imageurl}" class="card-img-top" alt="${product.name}" style="height: 200px; object-fit: contain;">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                        <p class="card-text"><strong>Price: </strong>$${product.price}</p>
                        <p class="card-text"><small class="text-muted">Category: ${product.category.name}</small></p>
                    </div>
                    <div class="card-footer bg-white">
                        <div class="d-flex justify-content-around">
                            <!-- Add to Cart Button -->
                            <a href="#" class="btn btn-primary">Add to Cart</a>
                            <!-- Wishlist Button (Icon) -->
                            <a href="#" class="btn btn-outline-danger">
                                <i class="bi bi-heart"></i> Wishlist
                            </a>
                            <!-- View Details Button -->
                            <a href="#" class="btn btn-info">View Details</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Bootstrap JS and dependencies (for icons and modals) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
