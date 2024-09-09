<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
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

        .logout {
            padding: 10px 20px;
            background-color: #e74c3c;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
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

        nav .menu-button {
            padding: 10px 20px;
            margin: 5px;
            background-color: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            display: inline-block;
        }

        nav .menu-button:hover {
            background-color: #2980b9;
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
            gap: 15px;
            margin-bottom: 30px;
        }

        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"], input[type="password"] {
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
    <h1 style="font-size: 40px; color: white; text-align: center; width : 100%">${userName} Profil</h1>
</header>

<nav>
    <#list menuList as element>
        <a href=${element.getEndpoint()} class=${element.getHtmlClass()}>${element.getDisplayName()}</a>
    </#list>
</nav>

<main>
    <h2>Profile Information</h2>
    <table>
        <thead>
        <tr>
            <th>Category</th>
            <th>Count</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Nouns</td>
            <td>${nounNumber}</td>
        </tr>
        <tr>
            <td>Verbs</td>
            <td>${verbNumber}</td>
        </tr>
        <tr>
            <td>Phrases</td>
            <td>${phraseNumber}</td>
        </tr>
        </tbody>
    </table>

    <h2>Change Password</h2>
    <form id="changePassForm">
        <div>
            <label for="current_password">Current Password:</label>
            <input type="password" id="current_password" name="current-password" required>
        </div>
        <div>
            <label for="new_password">New Password:</label>
            <input type="password" id="new_password" name="new-password" required>
        </div>
        <div>
            <label for="confirm_password">Confirm New Password:</label>
            <input type="password" id="confirm_password" name="confirm-password" required>
        </div>
        <input type="submit" value="Change Password">
    </form>


    <script>
        document.getElementById('changePassForm').addEventListener('submit', function (event) {
            //event.preventDefault(); // Prevent default form submission

            // Collect form data
            const formData = {
                userID: ${userID},
                password: document.getElementById('current_password').value,
                newPass: document.getElementById('new_password').value,
                confirm_password: document.getElementById('confirm_password').value
            };

            if ((formData.newPass === formData.confirm_password) && !(formData.newPass === formData.newPass)) {

                // Send data using fetch
                fetch('/updatePassword', {
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
                            alert('Failed. Please try again.\n' + data.message);
                        }
                    })
                    .catch(error => {
                        // Handle network errors
                        console.log(error)
                        alert('An error occurred. Please try again later.');
                    });
            } else {
                alert("New passwords must match! New password should differ from the old one!");
            }
        });
    </script>
</main>

</body>
</html>
