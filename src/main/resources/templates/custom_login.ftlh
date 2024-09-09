<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 90%;
            max-width: 1000px;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            gap: 20px;
            box-sizing: border-box;
        }

        .form-container {
            width: 100%;
            max-width: 500px;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            color: #3498db;
            margin-bottom: 20px;
        }

        label {
            font-size: 14px;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #3498db;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }

            .form-container {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Login Form-->
    <div class="form-container">
        <h1>Anmelden</h1>
        <form action="/login" method="POST">
            <label for="login_username">Benutzername</label>
            <input type="text" id="login_username" name="username" required>

            <label for="login_password">Passwort</label>
            <input type="password" id="login_password" name="password" required>

            <input type="submit" value="Anmelden">
        </form>
    </div>

    <!-- Registration Form -->
    <#--    <div class="form-container">-->
    <#--        <h1>Registrieren</h1>-->
    <#--        <form id="registerForm">-->
    <#--            <label for="register_username">Benutzername</label>-->
    <#--            <input type="text" id="register_username" name="username" required>-->

    <#--            <label for="register_password">Passwort</label>-->
    <#--            <input type="password" id="register_password" name="password" required>-->

    <#--            <label for="register_confirm_password">Passwort bestätigen-->
    <#--            </label>-->
    <#--            <input type="password" id="register_confirm_password" name="confirm_password" required>-->

    <#--            <input type="hidden" value="USER" id="register_role" name="role" required>-->

    <#--            <input type="submit" value="Registrieren">-->
    <#--        </form>-->
    <#--    </div>-->
    </div>
    <#--<script>-->
    <#--    document.getElementById('registerForm').addEventListener('submit', function (event) {-->
    <#--        event.preventDefault(); // Prevent default form submission-->

    <#--        // Collect form data-->
    <#--        const formData = {-->
    <#--            username: document.getElementById('register_username').value,-->
    <#--            password: document.getElementById('register_password').value,-->
    <#--            confirm_password: document.getElementById('register_confirm_password').value,-->
    <#--            role: document.getElementById('register_role').value-->
    <#--        };-->

    <#--        if (formData.password === formData.confirm_password) {-->

    <#--            // Send data using fetch-->
    <#--            fetch('/register/user', {-->
    <#--                method: 'POST',-->
    <#--                headers: {-->
    <#--                    'Content-Type': 'application/json'-->
    <#--                },-->
    <#--                body: JSON.stringify(formData)-->
    <#--            })-->
    <#--                .then(response => response.json())-->
    <#--                .then(data => {-->
    <#--                    console.log(data)-->
    <#--                    if (data.status === "success") {-->
    <#--                        // Handle success-->
    <#--                        alert('Registration successful!');-->
    <#--                    } else {-->
    <#--                        // Handle errors-->
    <#--                        alert('Registration failed. Please try again.\n' + data.message);-->
    <#--                    }-->
    <#--                })-->
    <#--                .catch(error => {-->
    <#--                    // Handle network errors-->
    <#--                    console.log(error)-->
    <#--                    alert('An error occurred. Please try again later.');-->
    <#--                });-->
    <#--        } else {-->
    <#--            alert("Passwords must match!");-->
    <#--        }-->
    <#--    });-->
    <#--</script>-->
</body>
</html>
