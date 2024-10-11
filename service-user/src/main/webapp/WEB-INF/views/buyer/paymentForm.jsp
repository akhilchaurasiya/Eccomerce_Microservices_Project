<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="buyerHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Order Page</title>

    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    <style>
        /* Add your existing CSS */
  
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 40px auto;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        h2 {
            text-align: center;
            font-weight: 600;
            color: #007bff;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            font-size: 14px;
            color: #555;
        }

        input, select {
            width: 100%;
            border-radius: 6px;
            border: 1px solid #ced4da;
            padding: 10px;
            font-size: 14px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        input:focus, select:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
        }

        .btn {
            width: 100%;
            padding: 12px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn-primary {
            background-color: #007bff;
            color: #fff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
        }

        .col-md-6 {
            width: 48%;
            margin-right: 2%;
        }

        .col-md-12 {
            width: 100%;
        }

        .card {
            border: 1px solid #ddd;
            background-color: #f7f9fc;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .card-body {
            background-color: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
        }

        .table-borderless td {
            padding: 10px 5px;
            font-size: 14px;
        }

        .table-borderless tr.border-top td {
            border-top: 2px solid #007bff;
            font-weight: bold;
            font-size: 16px;
        }

        .mt-3 {
            margin-top: 20px;
        }

        .shadow {
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        @media (max-width: 768px) {
            .col-md-6 {
                width: 100%;
                margin-right: 0;
            }
        }
    </style>
 
</head>
<body>
    <div class="container">
        <h2>Complete Your Order</h2>
        <form action="/successPayment" method="post" id="orders" novalidate>
            <div class="row">
                <!-- Billing Address -->
                <div class="col-md-6">
                    <div class="card">
                        <h3 class="text-center text-primary">Billing Address</h3>
                        <hr>
                        <div class="card-body">
                            <!-- Existing input fields -->
                            <div class="mb-3">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="mobileNo">Mobile Number</label>
                                <input type="text" id="mobileNo" name="mobileNo" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="address">Address</label>
                                <input type="text" id="address" name="address" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="city">City</label>
                                <input type="text" id="city" name="city" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="state">State</label>
                                <input type="text" id="state" name="state" required class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="pincode">Pincode</label>
                                <input type="number" id="pincode" name="pincode" required class="form-control">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Payment Type and Summary -->
                <div class="col-md-6">
                    <div class="card">
                        <h3 class="text-center text-primary">Payment Type</h3>
                        <hr>
                        <div class="card-body">
                            <!-- Payment summary and type selection -->
                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <td>Price</td>
                                        <td>:</td>
                                        <td>&#8377; ${orderPrice}</td>
                                    </tr>
                                    <tr>
                                        <td>Delivery Fee</td>
                                        <td>:</td>
                                        <td>&#8377; 250</td>
                                    </tr>
                                    <tr>
                                        <td>Tax</td>
                                        <td>:</td>
                                        <td>&#8377; 100</td>
                                    </tr>
                                    <tr class="border-top">
                                        <td>Total Price</td>
                                        <td>:</td>
                                       <td>&#8377; ${totalPrice + 250 + 100}</td> 
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="card shadow mt-3">
                        <div class="card-body">
                            <div class="mb-3">
                                <label for="paymentType">Select Payment Type</label>
                                <select required id="paymentType" name="paymentType" class="form-control">
                                    <option value="">--select--</option>
                                    <option value="COD">Cash On Delivery</option>
                                    <option value="ONLINE">Online Payment</option>
                                </select>
                            </div>
                            <button type="button" id="razorpay-button" class="btn btn-primary">Pay with Razorpay</button><br><br>
                            <button type="submit" id="place-order" class="btn btn-primary">Place Order</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        // Payment Type toggle functionality
        document.getElementById('paymentType').addEventListener('change', function () {
            const paymentType = this.value;
            const razorpayButton = document.getElementById('razorpay-button');
            const placeOrderButton = document.getElementById('place-order');

            if (paymentType === 'ONLINE') {
                razorpayButton.style.display = 'block';
                placeOrderButton.style.display = 'none';
            } else {
                razorpayButton.style.display = 'none';
                placeOrderButton.style.display = 'block';
            }
        });
 
        // Razorpay payment handler
        document.getElementById('razorpay-button').addEventListener('click', function (e) {
            e.preventDefault();
/* const totalOrderPrice = ${totalOrderPrice};  */
            const totalOrderPrice = ${totalPrice};  
            const options = {
                "key": "rzp_test_i6zKIwHPWWVkPs", 
                "amount": totalOrderPrice * 100, 
                "currency": "INR",
                "name": "E-Commerce Website",
                "description": "Order Payment",
                "handler": function (response) {
                    const form = document.getElementById('orders');
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'razorpayPaymentId';
                    input.value = response.razorpay_payment_id;
                    form.appendChild(input);
                    form.submit();
                },
                "prefill": {
                    "name": document.getElementsByName('firstName')[0].value + ' ' + document.getElementsByName('lastName')[0].value,
                    "email": document.getElementsByName('email')[0].value,
                    "contact": document.getElementsByName('mobileNo')[0].value
                },
                "theme": {
                    "color": "#3399cc"
                }
            };

            const rzp1 = new Razorpay(options);
            rzp1.open();
        });

        // Prevent form submission if invalid and show validation messages
        document.getElementById('orders').addEventListener('submit', function (event) {
            if (!this.checkValidity()) {
                event.preventDefault();  // Prevent form from submitting
                event.stopPropagation(); // Stop event propagation to ensure validation messages are shown
            }
            this.classList.add('was-validated'); // Add Bootstrap-like class to indicate validation
        });
    </script>
</body>
</html>