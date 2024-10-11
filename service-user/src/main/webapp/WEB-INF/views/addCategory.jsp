<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Category</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7f6;
            margin: 30px;
            padding: 0;
        }
        .container {
            display: flex;
            justify-content: space-between;
            padding: 40px;
            max-width: 1200px;
            margin: 0 auto;
            background-color: #d6eaf8;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .left, .right {
            width: 45%;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 10px;
        }
        .right {
            border-left: 2px solid #ddd;
        }
        h1, h2 {
            color: #333;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        #categoryList {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #f1f1f1;
            border-radius: 5px;
        }
        #categoryList ul {
            list-style: none;
            padding: 0;
        }
        #categoryList ul li {
            background-color: #fff;
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        /* Styling the Home link */
        a.home-link {
            display: inline-block;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            margin-top: 15px;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s, box-shadow 0.3s;
        }
        a.home-link:hover {
            background-color: #2980b9;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                padding: 20px;
            }
            .left, .right {
                width: 100%;
                margin-bottom: 20px;
            }
            .right {
                border-left: none;
            }
        }
    </style>

</head>
<body>
    <div class="container">
        <!-- Left side: Form -->
        <div class="left">
            <h1>Add New Category</h1>
            <form id="categoryForm">
                <label for="name">Category Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="imageName">Image Name:</label>
                <input type="text" id="imageName" name="imageName" required>

                <input type="submit" value="Add Category">
                <br>
                <!-- Home link -->
                <a href="/sellerPage" class="home-link">Home</a>
            </form>
        </div>

        <!-- Right side: List of Categories -->
        <div class="right">
            <h2>Categories</h2>
            <div id="categoryList">
                <!-- List of categories will be populated here via AJAX -->
            </div>
        </div>
    </div>
    
        <script type="text/javascript">
        $(document).ready(function() {
            $('#categoryForm').submit(function(e) {
                e.preventDefault(); // Prevents the form from refreshing the page

                // Get form data
                var categoryData = {
                    "name": $('#name').val(),
                    "imageName": $('#imageName').val()
                };

                // Send AJAX request
                $.ajax({
                    url: 'http://localhost:8082/categories', // Directly to the REST API
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(categoryData),
                    success: function(response) {
                        alert('Category added successfully!');
                        loadCategories(); // Call function to reload categories
                    },
                    error: function(xhr, status, error) {
                        console.log(xhr.responseText); // Log error for more details
                        alert('Error occurred: ' + xhr.status + ' - ' + xhr.responseText);
                    }
                });
            });

            // Function to load categories
            function loadCategories() {
                $.ajax({
                    url: 'http://localhost:8082/categories', // URL to fetch categories
                    type: 'GET',
                    success: function(data) {
                        var categoryList = '';
                        $.each(data, function(index, category) {
                            categoryList += '<li>' + category.name + '</li>';
                        });
                        $('#categoryList').html('<ul>' + categoryList + '</ul>');
                    },
                    error: function() {
                        $('#categoryList').html('Failed to load categories.');
                    }
                });
            }

            // Load categories on page load
            loadCategories();
        });
    </script>
</body>
</html>
