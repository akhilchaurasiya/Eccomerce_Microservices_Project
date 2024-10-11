
 <%@include file="buyerHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders Page</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .orders-container {
            margin-top: 50px;
            text-align: center;
        }

        .orders-header {
            margin-bottom: 30px;
            font-size: 2rem;
            font-weight: bold;
        }

        .order-list {
            list-style-type: none;
            padding: 0;
        }

        .order-item {
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            text-align: left;
        }

        .order-status {
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container orders-container">
    <h1 class="orders-header">Your Orders</h1>

    <!-- Orders will be displayed here -->
    <ul id="orders-list" class="order-list">
        <!-- Orders will be loaded using JavaScript -->
    </ul>
</div>

<!-- jQuery and JavaScript to Fetch Orders from localStorage -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    // Fetch orders from localStorage
    let orders = JSON.parse(localStorage.getItem('orders')) || [];

    if (orders.length > 0) {
        // Loop through each order and display it
        orders.forEach(order => {
            appendOrderItem(order);
        });
    } else {
        $('#orders-list').append('<li>No orders found.</li>');
    }

    // Helper function to append order items to the list
    function appendOrderItem(order) {
        let orderHTML = `
            <li class="order-item">
                <h5>Order ID: ` + Math.floor(Math.random() * 1000000) + `</h5>
                <p><strong>Status:</strong> <span class="order-status">` + order.status + `</span></p>
                <p><strong>Total Amount:</strong> $` + order.totalAmount.toFixed(2) + `</p>
                <p><strong>Items:</strong></p>
                <ul>
                    ` + order.cartItems.map(function(item) {
                        return `<li>` + item.productName + ` - Quantity: ` + item.quantity + ` - Price: $` + item.price + `</li>`;
                    }).join('') + `
                </ul>
            </li>
        `;
        $('#orders-list').append(orderHTML);
    }

});
</script>

<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
 