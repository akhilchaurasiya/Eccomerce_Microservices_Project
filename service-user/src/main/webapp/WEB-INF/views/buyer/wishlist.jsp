<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@include file="buyerHeader.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Wishlist</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .wishlist-item {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .wishlist-image {
            height: 200px;
            object-fit: cover;
            border-radius: 10px;
            margin-bottom: 15px;
        }

        .wishlist-details h5 {
            font-weight: bold;
            color: #333;
        }

        .remove-button {
            background-color: red;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }

    </style>
</head>
<body>

    <div class="container">
        <h1>My Wishlist</h1>
        <div id="wishlist-container" class="row">
            <!-- Wishlist items will be loaded here -->
        </div>
    </div>

    <!-- Script to Load Wishlist from Local Storage -->
<script>
    // Function to load and display the wishlist items using DOM manipulation
    function loadWishlist() {
        // Get the wishlist from local storage
        let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

        const wishlistContainer = document.getElementById('wishlist-container');
        wishlistContainer.innerHTML = '';  // Clear previous content

        console.log("Wishlist items:", wishlist); // Log the wishlist items to verify the data structure

        // Check if the wishlist is empty
        if (wishlist.length === 0) {
            const emptyMessage = document.createElement('p');
            emptyMessage.textContent = 'No products are available in the wishlist.';
            wishlistContainer.appendChild(emptyMessage);
            return;
        }

        // Create and append each product item dynamically
        wishlist.forEach(product => {
            console.log("Rendering product:", product);  // Log each product to inspect its structure

            // Create the outer div for the product card
            const productDiv = document.createElement('div');
            productDiv.classList.add('col-md-4');

            const wishlistItemDiv = document.createElement('div');
            wishlistItemDiv.classList.add('wishlist-item');
            
            // Create and set the product image
            const productImage = document.createElement('img');
            productImage.src = product.image;
            productImage.alt = product.name;
            productImage.classList.add('wishlist-image');

            // Create the product details div
            const productDetailsDiv = document.createElement('div');
            productDetailsDiv.classList.add('wishlist-details');

            // Add the product name
            const productName = document.createElement('h5');
            productName.textContent = product.name;

            // Append the name to the product details div
            productDetailsDiv.appendChild(productName);

            // Create the remove button
            const removeButton = document.createElement('button');
            removeButton.textContent = 'Remove';
            removeButton.classList.add('remove-button');
            removeButton.addEventListener('click', () => removeFromWishlist(product.id));

            // Append the image, product details, and remove button to the wishlist item div
            wishlistItemDiv.appendChild(productImage);
            wishlistItemDiv.appendChild(productDetailsDiv);
            wishlistItemDiv.appendChild(removeButton);

            // Append the wishlist item div to the main product div
            productDiv.appendChild(wishlistItemDiv);

            // Append the product div to the wishlist container
            wishlistContainer.appendChild(productDiv);
        });
    }

    // Function to remove an item from the wishlist
    function removeFromWishlist(productId) {
        // Get the current wishlist from local storage
        let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

        // Filter out the product to be removed
        wishlist = wishlist.filter(item => item.id !== productId);

        // Save the updated wishlist back to local storage
        localStorage.setItem('wishlist', JSON.stringify(wishlist));

        // Reload the wishlist display
        loadWishlist();
    }

    // Load the wishlist when the page is loaded
    document.addEventListener('DOMContentLoaded', loadWishlist);
</script>

</body>
</html>
