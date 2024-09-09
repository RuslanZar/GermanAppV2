<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>German App v${version}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header {
            width: 100%;
            background-color: #3498db;
            color: #fff;
            padding: 0px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-sizing: border-box;
        }

        h2 {
            color: #3498db;
        }

        h2 {
            color: #3498db;
        }

        .logout {
            padding: 10px 20px;
            background-color: #e74c3c;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            margin: 5px;
            font-size: 16px;
            display: inline-block;
        }

        .logout:hover {
            background-color: #c0392b;
        }

        nav {
            width: 100%;
            background-color: #3498db;
            padding: 10px 0;
            box-sizing: border-box;
            text-align: center;
        }

        main {
            width: 90%;
            max-width: 1200px;
            margin: 20px 0;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px;
            margin-bottom: 30px;
        }

        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            display: block;
            align-content: center;
        }

        select, input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            grid-column: span 2;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            height: 32px;
            text-align: left;
            padding-left: 8px;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .shadowButton {
            opacity: 0;
            transition: opacity 0.3s;
            width: 100%;
            height: 100%;
        }

        .shadowButton:hover {
            opacity: 1;
        }

        @media (max-width: 768px) {
            form {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<header>
    <h1 style="width: 100%; text-align: center">Hello Admin ${userName} of German App v2.0</h1>
</header>

<nav>
    <a href="/logout" class="logout">Logout</a>
</nav>

<main>
    <h2>Users</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <#if allUserList?has_content>
            <#list allUserList as user>
                <tr id="${user.getId()}" <#if user.getUsername() == userName> style="background-color: coral" </#if>>
                    <td>${user.getId()}</td>
                    <td>
                        ${user.getUsername()}
                    </td>
                    <td>${user.getPassword()}</td>
                    <td>${user.getRole()}</td>
                    <#if user.getUsername() != userName>
                        <td style="padding: 0">
                            <button class="shadowButton" onclick="deleteUser(${user.getId()})">delete</button>
                        </td> </#if>

                </tr>
            </#list>
        <#else>
            <tr>
                <td colspan="3">No Users... WTF?</td>
            </tr>
        </#if>
        </tbody>
    </table>

    <!-- Registration Form -->
    <div class="form-container">
        <h1>Registrieren neu Benutzer</h1>
        <form id="registerForm">
            <label for="register_username">Benutzername</label>
            <input type="text" id="register_username" name="username" required>

            <label for="register_password">Passwort</label>
            <input type="password" id="register_password" name="password" required>

            <label for="register_confirm_password">Passwort bestätigen
            </label>
            <input type="password" id="register_confirm_password" name="confirm_password" required>

            <label for="register_role">Role
            </label>
            <select id="register_role" name="role" required>
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>

            <input type="submit" value="Registrieren">
        </form>
    </div>
    </div>
    <script>
        document.getElementById('registerForm').addEventListener('submit', function (event) {
            event.preventDefault(); // Prevent default form submission

            // Collect form data
            const formData = {
                username: document.getElementById('register_username').value,
                password: document.getElementById('register_password').value,
                confirm_password: document.getElementById('register_confirm_password').value,
                role: document.getElementById('register_role').value
            };

            if (formData.password === formData.confirm_password) {

                // Send data using fetch
                fetch('/register/user', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(formData)
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data)
                        if (data.status === "success") {
                            // Handle success
                            location.reload()
                        } else {
                            // Handle errors
                            alert('Registration failed. Please try again.\n' + data.message);
                        }
                    })
                    .catch(error => {
                        // Handle network errors
                        console.log(error)
                        alert('An error occurred. Please try again later.');
                    });
            } else {
                alert("Passwords must match!");
            }
        });
    </script>
    <script>
        function deleteUser(id) {
            fetch('/deleteUser', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({id: id})
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the new word
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
        }
    </script>
</main>
</body>
</html>
