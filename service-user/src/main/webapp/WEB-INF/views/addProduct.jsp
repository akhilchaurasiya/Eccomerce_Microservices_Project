<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px; /* Increased width */
            margin: 50px auto;
            padding: 15px; /* Reduced padding */
            background-color: #fff;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            color: #1a237e;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #303f9f;
        }
        input[type="text"], input[type="number"], select {
            padding: 8px; /* Reduced height */
            margin-bottom: 15px; /* Reduced margin */
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #1b5e20;
            color: white;
            border: none;
            padding: 10px 0; /* Reduced height */
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #2e7d32;
        }
        .home-button {
            display: inline-block;
            background-color: #0d47a1;
            color: white;
            padding: 10px 0; /* Reduced height */
            text-align: center;
            text-decoration: none;
            width: 100%; /* Same width as inputs */
            margin-top: 10px;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }
        .home-button:hover {
            background-color: #1565c0;
        }
        .error-message {
            color: #e53935;
            margin-top: -10px;
            margin-bottom: 15px;
            display: none;
        }
        .success-message {
            color: #388e3c;
            font-weight: bold;
            margin-top: 20px;
            text-align: center;
        }
        @media (max-width: 768px) {
            .container {
                padding: 15px;
            }
            input[type="submit"], .home-button {
                font-size: 16px;
            }
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            // Function to fetch categories and populate the dropdown
            function loadCategories() {
                $.ajax({
                    url: 'http://localhost:8082/categories', // Fetch categories
                    type: 'GET',
                    contentType: 'application/json',
                    success: function(categories) {
                        var dropdown = $('#categoryDropdown');
                        dropdown.empty(); // Clear existing options
                        dropdown.append('<option value="">Select a category</option>');
                        $.each(categories, function(index, category) {
                            dropdown.append('<option value="' + category.id + '" data-name="' + category.name + '">' + category.name + '</option>');
                        });
                    },
                    error: function(xhr, status, error) {
                        console.log("Error fetching categories: " + xhr.responseText);
                    }
                });
            }

            // Call loadCategories when the page loads
            loadCategories();

            // Automatically update the category ID field when a category is selected
            $('#categoryDropdown').change(function() {
                var selectedOption = $(this).find('option:selected');
                var categoryId = selectedOption.val();
                var categoryName = selectedOption.data('name');
                $('#categoryId').val(categoryId);
                $('#categoryName').val(categoryName);
            });

            // Handle form submission with validation
            $('#productForm').submit(function(e) {
                e.preventDefault(); // Prevents the form from refreshing the page
                
                // Basic form validation
                var isValid = true;
                $('.error-message').hide(); // Hide all previous error messages

                if (!$('#name').val()) {
                    $('#name').next('.error-message').show();
                    isValid = false;
                }
                if (!$('#description').val()) {
                    $('#description').next('.error-message').show();
                    isValid = false;
                }
                if (!$('#skuCode').val()) {
                    $('#skuCode').next('.error-message').show();
                    isValid = false;
                }
                if (!$('#price').val()) {
                    $('#price').next('.error-message').show();
                    isValid = false;
                }
                if (!$('#categoryId').val()) {
                    $('#categoryDropdown').next('.error-message').show();
                    isValid = false;
                }

                if (!isValid) {
                    return;
                }

                // Get form data
                var productData = {
                    "name": $('#name').val(),
                    "description": $('#description').val(),
                    "skuCode": $('#skuCode').val(),
                    "price": parseFloat($('#price').val()), // Ensure it's a number
                    "category": {
                        "id": parseInt($('#categoryId').val()), // Get selected category ID
                        "name": $('#categoryName').val() // Get selected category name
                    },
                    "imageurl": $('#imageurl').val()
                };

                // Send AJAX request to add the product
                $.ajax({
                    url: 'http://localhost:8082/products', // Directly to the REST API for products
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(productData),
                    success: function(response) {
                        $('.success-message').text('Product added successfully!').show();
                    },
                    error: function(xhr, status, error) {
                        console.log(xhr.responseText); // Log error for more details
                        alert('Error occurred: ' + xhr.status + ' - ' + xhr.responseText); // More detailed error message
                    }
                });
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h1>Add New Product</h1>

        <form id="productForm">
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required>
            <div class="error-message">Product name is required</div>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required>
            <div class="error-message">Description is required</div>

            <label for="skuCode">SKU Code:</label>
            <input type="text" id="skuCode" name="skuCode" required>
            <div class="error-message">SKU Code is required</div>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required>
            <div class="error-message">Price is required</div>

            <!-- Category dropdown -->
            <label for="categoryDropdown">Category:</label>
            <select id="categoryDropdown" name="categoryDropdown" required>
                <option value="">Select a category</option>
            </select>
            <div class="error-message">Please select a category</div>

            <!-- Hidden fields for category ID and name -->
            <input type="hidden" id="categoryId" name="categoryId">
            <input type="hidden" id="categoryName" name="categoryName">

            <label for="imageurl">Image URL:</label>
            <input type="text" id="imageurl" name="imageurl" required>
            <div class="error-message">Image URL is required</div>

            <input type="submit" value="Add Product">
        </form>

        <!-- Home Button -->
        <a href="/sellerPage" class="home-button">Home</a>

        <div class="success-message" style="display:none;"></div>
    </div>
</body>
</html>
