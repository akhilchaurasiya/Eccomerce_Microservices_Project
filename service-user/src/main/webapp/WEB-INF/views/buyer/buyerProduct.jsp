 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="buyerHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Categories</title>

    <!-- Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS for Sidebar and Product List -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }

       #sidebar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 70px; /* Adjust this value to the height of your navigation bar */
            left: 0;
            background-color: #343a40;
            padding-top: 20px;
            color: white;
            overflow-y: auto;
        }

        #sidebar h3 {
            text-align: center;
            color: #ffc107;
            margin-bottom: 20px;
        }

        #sidebar ul {
            list-style-type: none;
            padding-left: 0;
        }

        #sidebar ul li {
            padding: 10px 15px;
            text-align: center;
        }

        #sidebar ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 18px;
            display: block;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        #sidebar ul li a:hover {
            background-color: #ffc107;
            color: #343a40;
            font-weight: bold;
        }

        /* Main content styling */
        .container {
            margin-left: 270px;
            padding: 20px;
           
        }

        .product {
            background-color: #fff;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            margin-bottom: 20px;
            padding: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-color: green;
            border-width:3px;
        }

        .product:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 20px rgba(0, 0, 0, 0.15);
        }

        .product img {
            max-height: 200px;
            object-fit: contain;
            margin-bottom: 10px;
            border-bottom: 1px solid #dee2e6;
            padding-bottom: 10px;
        }

        .product h3 {
            font-size: 20px;
            color: #007bff;
            text-align: center;
            margin-top: 10px;
        }

        .product p {
            font-size: 14px;
            color: #6c757d;
            text-align: center;
        }

        .product .price {
            font-weight: bold;
            color: #28a745;
            text-align: center;
        }

        /* Button styling */
        .btn-wishlist, .btn-cart {
            display: inline-block;
            padding: 8px 15px;
            text-align: center;
            width: 100%;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: all 0.3s ease;
            border: 2px solid;
        }

        .btn-wishlist {
            background-color: #ffffff;
            color: #dc3545;
            border-color: #dc3545;
        }

        .btn-wishlist:hover {
            background-color: #dc3545;
            color: #ffffff;
        }

        .btn-cart {
            background-color: #ffffff;
            color: #007bff;
            border-color: #007bff;
        }

        .btn-cart:hover {
            background-color: #007bff;
            color: #ffffff;
        }

        /* Add space between buttons */
        .action-buttons {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            margin-top: 15px;
        }

        /* Responsive styling */
        @media (max-width: 768px) {
            #sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }

            .container {
                margin-left: 0;
            }
        }
    </style>

    <!-- jQuery for AJAX -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

    <!-- Sidebar with dynamically fetched categories -->
    <div id="sidebar">
        <h3>Categories</h3>
        <ul id="categoryList">
            <li><a href="javascript:void(0)" id="allProductsLink">All Products</a></li>
            <!-- Categories will be dynamically populated here -->
        </ul>
    </div>

    <!-- Main Section where products will be displayed -->
    <div class="container">
        <div id="productList" class="row">
            <!-- Products will be dynamically displayed here -->
        </div>
    </div>

    <script type="text/javascript">
        let userId = null;

        $(document).ready(function() {
            // Fetch user ID and email
            $.ajax({
                url: 'http://localhost:8080/api/users',  // API URL to fetch user data
                type: 'GET',
                success: function(users) {
                    if (users.length > 0) {
                        userId = users[0].id;  // Assuming first user is the logged-in user
                    } else {
                        alert('No logged-in user found.');
                    }
                },
                error: function(error) {
                    console.log('Error fetching user data:', error);
                }
            });

            // Function to display products
            function displayProducts(products) {
                $('#productList').empty(); // Clear the current product list
                $.each(products, function(index, product) {
                    $('#productList').append(
                        '<div class="col-12 col-sm-6 col-md-4 col-lg-3">' +
                            '<div class="product">' +
                                '<img src="' + product.imageurl + '" alt="' + product.name + '" class="img-fluid"/>' +
                                '<h3>' + product.name + '</h3>' +
                                '<p>' + product.description + '</p>' +
                                '<p class="price">Price: &#8377;' + product.price + '</p>' +
                                '<p>Category: ' + product.category.name + '</p>' +
                                '<div class="action-buttons">' +
                                    /* '<a href="/addToWishlist/' + product.id + '" class="btn-wishlist">Add to Wishlist</a>' + */
                                    '<button class="btn-cart" onclick="addToCart(' + product.id + ', ' + product.price + ', \'' + product.name + '\', \'' + product.imageurl + '\')">Add to Cart</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>'
                    );
                });
            }

            // Fetch and display all products on page load
            $.ajax({
                url: 'http://localhost:8082/products', // URL for fetching all products
                type: 'GET',
                success: function(products) {
                    displayProducts(products); // Display all products
                },
                error: function() {
                    alert('Failed to fetch products.');
                }
            });

            // Fetch categories dynamically from the backend
            $.ajax({
                url: 'http://localhost:8082/categories', // Replace with the correct URL for fetching categories
                type: 'GET',
                success: function(categories) {
                    // Loop through categories and append them to the category list
                    $.each(categories, function(index, category) {
                        $('#categoryList').append(
                            '<li><a href="#" class="category-link" data-category-id="' + category.id + '">' + category.name + '</a></li>'
                        );
                    });

                    // Add event listener for dynamically generated category links
                    $('.category-link').on('click', function(e) {
                        e.preventDefault(); // Prevent default link behavior
                        var categoryId = $(this).data('category-id'); // Get the category ID from data attribute
                        
                        // Fetch products by category ID
                        $.ajax({
                            url: 'http://localhost:8082/products/categoryProduct/' + categoryId, // Use category ID in URL
                            type: 'GET',
                            success: function(products) {
                                displayProducts(products); // Display products for the selected category
                            },
                            error: function() {
                                alert('Failed to fetch products for this category.');
                            }
                        });
                    });

                    // Add event listener to "All Products" link
                    $('#allProductsLink').on('click', function(e) {
                        e.preventDefault();
                        // Fetch and display all products when "All Products" is clicked
                        $.ajax({
                            url: 'http://localhost:8082/products', // URL for fetching all products
                            type: 'GET',
                            success: function(products) {
                                displayProducts(products); // Display all products again
                            },
                            error: function() {
                                alert('Failed to fetch all products.');
                            }
                        });
                    });
                },
                error: function() {
                    alert('Failed to fetch categories.');
                }
            });
        });

        // Add to Cart Function
        function addToCart(productId, productPrice, productName, productImage){
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
            	            "image": productImage,
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
    </script>

    <!-- Bootstrap JS for responsive features -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
