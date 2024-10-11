<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Products</title>

    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS for styling -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .product-card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        .product-image {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 10px;
            margin-bottom: 15px;
        }
        .product-details {
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
        .action-buttons {
            display: flex;
            gap: 10px;
            justify-content: center;
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

<div class="container">
    <h2 class="my-4 text-center">All Products</h2>

    <!-- Loop through products and display each product as a card -->
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4">
                <div class="product-card">
                    <!-- Product Image -->
                    <img class="product-image" src="${product.imageurl}" alt="${product.name}">

                    <!-- Product Details -->
                    <div class="product-details">
                        <h5>${product.name}</h5>
                        <p><strong>ID:</strong> ${product.id}</p>
                        <p><strong>Description:</strong> ${product.description}</p>
                        <p><strong>Category:</strong> <c:out value="${product.category.name}" default="Unknown" /></p>
                        <p><strong>SKU Code:</strong> ${product.skuCode}</p>
                        <p class="price">$${product.price}</p>
                    </div>

                    <!-- Action Buttons -->
                    <div class="action-buttons">
                        <a href="/editProduct/${product.id}" class="btn btn-primary btn-sm">Edit</a>
                        <a href="/deleteProduct/${product.id}" class="btn btn-danger btn-sm">Delete</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Home Button -->
<a href="/sellerPage" class="home-button">Home</a>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
