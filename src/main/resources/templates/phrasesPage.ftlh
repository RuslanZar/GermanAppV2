<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>German App v2.0</title>
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

        input[type="text"] {
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
            padding-left: 8px
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
    <h1 style="font-size: 40px; color: white; text-align: center; width : 100%">German App v${version}</h1>
</header>

<nav>
    <#list menuList as element>
        <a href=${element.getEndpoint()} class=${element.getHtmlClass()}>${element.getDisplayName()}</a>
    </#list>
</nav>

<main>
    <div class="container">
        <h1>Fügen Sie die neue Phrase hinzu</h1>
        <form id="addPhraseForm">

            <div>
                <label for="german_phrase">Phrase auf Deutsch:</label>
                <input type="text" id="german_phrase" name="german_phrase" required>
            </div>

            <div>
                <label for="russian_Phrase">Phrase auf Russisch:</label>
                <input type="text" id="russian_phrase" name="russian_phrase" required>
            </div>

            <input type="submit" value="Hinzufügen">
        </form>

        <h2>Phrasen</h2>
        <table>
            <thead>
            <tr>
                <th>Phrase auf Russisch</th>
                <th>Phrase auf Deutsch</th>
                <th>Löschen</th>
            </tr>
            </thead>
            <tbody>
            <#if listOfPhrases?has_content>
                <#list listOfPhrases as Phrase>
                    <tr id="${Phrase.getId()}">
                        <td>${Phrase.german}</td>
                        <td>${Phrase.russian}</td>
                        <td style="padding: 0">
                            <button class="shadowButton" onclick="deleteLine(${Phrase.getId()})">löschen</button>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="3">Keine Phrasen verfügbar.</td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>

    <script>
        document.getElementById('addPhraseForm').addEventListener('submit', function (event) {
            event.preventDefault();

            // Collect form data
            const formData = {
                russian: document.getElementById('russian_phrase').value,
                german: document.getElementById('german_phrase').value,
                score: 0
            };

            fetch('/saveNewPhrase', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the new Phrase
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again later.');
                });
        });
    </script>
    <script>
        function deleteLine(id) {
            fetch('/deletePhrase', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({id: id})
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the new Phrase
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
        }
    </script>
</main>
</body>
</html>
