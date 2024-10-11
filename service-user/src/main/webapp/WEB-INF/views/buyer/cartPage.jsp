<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="buyerHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cart Page</title>

<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background-color: #f8f9fa;
}

/* Cart Page Styling */
.cart-container {
    margin-top: 50px;
}

.cart-header {
    text-align: center;
    margin-bottom: 30px;
    font-size: 2rem;
    font-weight: bold;
}

.cart-items {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    gap: 20px;
    list-style-type: none;
    padding: 0;
}

.cart-item {
    padding: 0;
    border: none;
    flex-basis: calc(25% - 20px); /* 4 items per row */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    background-color: white;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    height: 380px; /* Set a fixed height for the card */
    max-width: 250px; /* Optional: Set a maximum width for the card */
    overflow: hidden; /* Ensure content does not overflow the card */
}

.cart-item:hover {
    transform: scale(1.05);
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
}

.cart-item img {
    width: 100%;
    height: 180px !important; /* Set a fixed height for the image */
    object-fit: cover;
    border-bottom: 1px solid #ddd;
    border-radius: 8px 8px 0 0;
}

.cart-item-content {
    padding: 15px;
}

.cart-item:last-child {
    border-bottom: none;
}

.cart-total {
    font-weight: bold;
    text-align: right;
    margin-top: 20px;
    font-size: 1.2rem;
}

.product-name {
    font-size: 1.1rem;
    font-weight: bold;
    margin-bottom: 10px;
}

.product-info {
    font-size: 0.95rem;
    margin-bottom: 5px;
}

/* Quantity buttons styling */
.quantity-control {
    display: flex;
    align-items: center;
    gap: 10px;
}

.btn-quantity {
    padding: 5px 10px;
    font-size: 1rem;
    font-weight: bold;
    border: none;
    background-color: #007bff;
    color: white;
    cursor: pointer;
}

.btn-quantity:hover {
    background-color: #0056b3;
}

.btn-delete {
    background-color: #e74c3c;
    color: white;
    border: none;
    padding: 7px 15px;
    font-size: 0.85rem;
    border-radius: 5px;
    transition: background-color 0.3s ease;
    cursor: pointer;
    display: block;
    margin-top: 10px;
    text-align: center;
}

.btn-delete:hover {
    background-color: #c0392b;
}

.no-items-message {
    text-align: center;
    font-size: 18px;
    font-weight: bold;
    color: #dc3545;
    margin-top: 20px;
    list-style: none;
    padding: 10px;
    background-color: #f8d7da;
    border-radius: 5px;
}

/* Styling for the Checkout Button */
a[id="checkout-link"] h1 {
    display: block;
    width: 250px;
    background-image: linear-gradient(to right, #ff7e5f, #feb47b);
    color: white;
    text-align: center;
    padding: 15px;
    margin: 30px auto;
    font-size: 22px;
    font-weight: bold;
    border-radius: 30px;
    text-decoration: none;
    transition: background 0.3s ease;
    box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
}

a[id="checkout-link"] h1:hover {
    background-image: linear-gradient(to right, #feb47b, #ff7e5f);
    box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.3);
    transform: scale(1.05);
}

/* Responsive styling */
@media (max-width: 768px) {
    .cart-items {
        flex-direction: column;
        align-items: center;
    }

    .cart-item {
        flex-basis: 100%;
        max-width: 100%;
    }

    .btn-delete {
        width: 100%;
    }
}
</style>
</head>
<body>

<div class="container cart-container">
    <h1 class="cart-header">Your Cart Items</h1>

    <!-- Cart Items will be displayed here -->
    <ul id="cart-items" class="cart-items">
        <!-- Items will be loaded using AJAX -->
    </ul>

    <!-- Cart Total -->
    <p id="cart-total" class="cart-total"></p>
    <!-- Checkout Button -->
    <a id="checkout-link" href="#"><h1>Checkout</h1></a>

</div>

<!-- jQuery and AJAX to Fetch Cart Items -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
let userId = null; // Store the user ID dynamically
let totalAmount = 0;

$(document).ready(function() {
    // Fetch user email and ID from the session or API
    $.ajax({
        url: 'http://localhost:8080/api/users',  // API URL
        type: 'GET',
        success: function(users) {
            if (users.length > 0) {
                userId = users[0].id; // Assuming you are fetching the first user's ID, update accordingly
                fetchCartItems(userId); // Fetch cart items dynamically based on user ID
            } else {
                alert("No user logged in.");
            }
        },
        error: function(error) {
            console.log('Error fetching user ID:', error);
        }
    });

    // Fetch cart items on page load
    function fetchCartItems(userId) {
        $.ajax({
            url: 'http://localhost:8083/cart/user/' + userId,  // API URL for cart items
            type: 'GET',
            success: function(cart) {
                $('#cart-items').empty(); // Clear previous items
                totalAmount = 0;

                console.log('Cart API response:', cart); // Log the entire response for debugging

                if (cart && cart.length > 0) {
                    cart.forEach(item => {
                        item.cartItems.forEach(cartItem => {
                            appendCartItem(cartItem, cartItem.image || 'https://via.placeholder.com/50', cartItem.productName || 'Unknown Product', item.id);
                            totalAmount += cartItem.price * cartItem.quantity;
                        });
                    });

                    // Display total amount
                    updateTotalAmount();
                } else {
                    $('#cart-items').append('<li class="cart-item">No items in cart.</li>');
                }
            },
            error: function(error) {
                console.log('Error fetching cart items:', error);
                $('#cart-items').append(
                    '<li class="cart-item no-items-message">No items in cart.</li>'
                );
            }

        });
    }

    // Helper function to append cart items with delete button and quantity counter
    function appendCartItem(cartItem, productImage, productName, cartId) {
        const cardElement = $('<div></div>').addClass('card mb-4 shadow-sm');
        const cardBodyElement = $('<div></div>').addClass('card-body');
        const productImageElement = $('<img>').addClass('card-img-top').attr('src', productImage).attr('alt', productName).css({ height: '180px', objectFit: 'cover' });
        const productNameElement = $('<h5></h5>').addClass('card-title').text('Product Name: ' + productName);
        const productIdElement = $('<p></p>').addClass('card-text').text('Product ID: ' + cartItem.productId);
        const priceElement = $('<p></p>').addClass('card-text price').text('Price: $' + (cartItem.price * cartItem.quantity).toFixed(2));

        // Quantity counter
        const quantityElement = $('<div></div>').addClass('quantity-control');
        const decreaseButton = $('<button></button>').text('-').addClass('btn-quantity').click(function() {
            updateQuantity(cartItem, -1, priceElement, cartId, $(this));
        });
        const quantityDisplay = $('<span></span>').addClass('quantity-display').text(cartItem.quantity);
        const increaseButton = $('<button></button>').text('+').addClass('btn-quantity').click(function() {
            updateQuantity(cartItem, 1, priceElement, cartId, $(this));
        });

        quantityElement.append(decreaseButton, quantityDisplay, increaseButton);

        // Delete button
        const deleteButton = $('<button></button>').text('Delete').addClass('btn-delete').click(function() {
            deleteCartItem(cartId);
        });

        // Append all elements to card
        cardBodyElement.append(productImageElement, productNameElement, productIdElement, quantityElement, priceElement, deleteButton);
        cardElement.append(cardBodyElement);

        // Append the card to the cart-items list
        $('#cart-items').append(cardElement);
    }

    // Update quantity of cart items
    function updateQuantity(cartItem, change, priceElement, cartId, button) {
        let newQuantity = cartItem.quantity + change;
        if (newQuantity <= 0) return; // Prevent quantity going below 1

        // Update the cart item's quantity
        cartItem.quantity = newQuantity;

        // Update the specific quantity display of this cart item
        button.siblings('.quantity-display').text(newQuantity);

        // Update price for the current item
        let updatedPrice = (cartItem.price * newQuantity).toFixed(2);
        priceElement.text('Price: $' + updatedPrice);

        // Recalculate total amount
        recalculateTotalAmount();
    }

    // Function to handle cart item deletion
    function deleteCartItem(cartId) {
        if (confirm('Are you sure you want to delete this item from the cart?')) {
            $.ajax({
                url: 'http://localhost:8083/cart/delete/' + cartId,  // Deletion URL
                type: 'DELETE',
                success: function(response) {
                    alert('Cart item deleted successfully!');
                    // Refresh the cart items after deletion
                    fetchCartItems(userId);  // Reload cart items after deletion
                },
                error: function(error) {
                    console.log('Error deleting cart item:', error);
                    alert('Failed to delete cart item.');
                }
            });
        }
    }

    // Recalculate total amount
    function recalculateTotalAmount() {
        totalAmount = 0;
        $('#cart-items .card').each(function() {
            const priceText = $(this).find('.price').text().replace('Price: $', '');
            totalAmount += parseFloat(priceText);
        });
        updateTotalAmount();
    }

    // Update total amount display and set the checkout link with the correct total price
    function updateTotalAmount() {
        $('#cart-total').text('Total: $' + totalAmount.toFixed(2));

       
        $('#checkout-link').click(function(e) {
            e.preventDefault();
            if (userId && totalAmount > 0) {
                const orderData = {
                    userId: userId,
                    totalAmount: totalAmount,
                    status: "Order Placed",
                    cartItems: []
                };

                $('#cart-items .card').each(function() {
                    const cartItem = {
                        productId: $(this).find('.card-text').text().replace('Product ID: ', ''),
                        productName: $(this).find('.card-title').text().replace('Product Name: ', ''),
                        quantity: parseInt($(this).find('.quantity-display').text()),
                        price: parseFloat($(this).find('.price').text().replace('Price: $', ''))
                    };
                    orderData.cartItems.push(cartItem);
                });

                let existingOrders = JSON.parse(localStorage.getItem('orders')) || [];
                existingOrders.push(orderData);
                localStorage.setItem('orders', JSON.stringify(existingOrders));

                // Redirect to the payment page with the total price
                window.location.href = '/processPayment?totalPrice=' + totalAmount.toFixed(2);
            }
        });
    }
});
</script>

<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
