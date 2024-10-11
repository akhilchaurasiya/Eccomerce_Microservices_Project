<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* General Body Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7f6;
            padding: 40px;
            color: #333;
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        /* Form Styling */
        form {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #34495e;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #3498db;
            outline: none;
        }

        /* Button Styling */
        #updateProductBtn {
            background-color: #27ae60;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
            width: 100%;
        }

        #updateProductBtn:hover {
            background-color: #2ecc71;
            transform: translateY(-3px);
        }

        #updateProductBtn:active {
            background-color: #27ae60;
            transform: translateY(0);
        }

        /* Result Message Styling */
        #result {
            margin-top: 20px;
            text-align: center;
            font-size: 16px;
        }

        #result p {
            padding: 10px;
            border-radius: 5px;
        }

        /* Success and Error Messages */
        .success-message {
            background-color: #2ecc71;
            color: white;
        }

        .error-message {
            background-color: #e74c3c;
            color: white;
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
    <h2>Edit Product</h2>
    
    <form id="editProductForm">
        <input type="hidden" id="productId" value="${product.id}" />

        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" />

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${product.description}" />

        <label for="skuCode">SKU Code:</label>
        <input type="text" id="skuCode" name="skuCode" value="${product.skuCode}" />

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.price}" />

        <label for="categoryId">Category ID:</label>
        <input type="number" id="categoryId" name="categoryId" value="${product.category.id}" />

        <label for="imageUrl">Image URL:</label>
        <input type="text" id="imageUrl" name="imageUrl" value="${product.imageurl}" />

        <button type="button" id="updateProductBtn">Update Product</button>
    </form>
    <!-- Home Button -->
<a href="/sellerPage" class="home-button">Home</a>

    <div id="result"></div>

    <script>
        $(document).ready(function() {
            $('#updateProductBtn').click(function() {
                var productId = $('#productId').val();
                var name = $('#name').val();
                var description = $('#description').val();
                var skuCode = $('#skuCode').val();
                var price = $('#price').val();
                var categoryId = $('#categoryId').val();
                var imageUrl = $('#imageUrl').val();

                // Create the JSON data to be sent
                var jsonData = JSON.stringify({
                    "id": productId,
                    "name": name,
                    "description": description,
                    "skuCode": skuCode,
                    "price": price,
                    "category": {
                        "id": categoryId
                    },
                    "imageurl": imageUrl
                });

                // AJAX call to update the product
                $.ajax({
                    url: 'http://localhost:8082/products/' + productId,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: jsonData,
                    success: function(response) {
                        $('#result').html('<p class="success-message">Product updated successfully!</p>');
                    },
                    error: function(error) {
                        $('#result').html('<p class="error-message">Error updating product: ' + error.responseText + '</p>');
                    }
                });
            });
        });
    </script>
</body>
</html>
