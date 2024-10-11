<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            font-weight: bold;
            color: #333;
        }

        .form-control {
            border-radius: 30px;
            padding: 15px;
        }

        .btn-primary {
            background-color: #0d47a1;
            border: none;
            border-radius: 30px;
            padding: 10px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #1565c0;
        }

        .forgot-password {
            color: #0d47a1;
            text-decoration: none;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }

        .home-button {
            display: block;
            background-color: #0d47a1;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            margin: 20px auto;
            border-radius: 30px;
            font-size: 18px;
            width: 150px;
        }

        .home-button:hover {
            background-color: #1565c0;
        }

    </style>
</head>

<body>

    <div class="login-container">
        <h2 class="text-center mb-4">Login</h2>

        <form action="/doLogin" method="post">
            <!-- Email Field -->
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <!-- Password Field -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <!-- Submit Button -->
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>

            <!-- Forgot Password Link -->
            <div class="mt-3 text-center">
                <a href="/" class="forgot-password">Home</a>
            </div>
        </form>
    </div>

   

    <!-- Bootstrap JS (optional for responsive behaviors) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>

</html>
