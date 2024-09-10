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

        input[type="submit"], button {
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
    <h1 style="font-size: 40px; color: white; text-align: center; width : 100%">Svinka App
        v<#if version?has_content>${version}</#if></h1>
</header>

<nav>
    <#list menuList as element>
        <a href=${element.getEndpoint()} class=${element.getHtmlClass()}>${element.getDisplayName()}</a>
    </#list>
</nav>

<main>
    <div class="container">
        <h1>Fügen Sie das neue Nomen hinzu.</h1>
        <form id="addWordForm">

            <div>
                <label for="german_word">Nomen auf Deutsch:</label>
                <input onclick="changeActiveElement(id)" type="text" id="german_word" name="german_word" required>
            </div>

            <div>
                <label for="plural_form">Plural:</label>
                <input onclick="changeActiveElement(id)" type="text" id="plural_form" name="plural_form">
            </div>

            <div>
                <label for="russian_word">Nomen auf Russisch:</label>
                <input onclick="changeActiveElement(id)" type="text" id="russian_word" name="russian_word" required>
            </div>
            <div>
                <button type="button" onclick="concatenate('ä')" style="margin-top: 20px">ä</button>
                <button type="button" onclick="concatenate('Ä')" style="margin-top: 20px">Ä</button>
                <button type="button" onclick="concatenate('ö')" style="margin-top: 20px">ö</button>
                <button type="button" onclick="concatenate('Ö')" style="margin-top: 20px">Ö</button>
                <button type="button" onclick="concatenate('ü')" style="margin-top: 20px">ü</button>
                <button type="button" onclick="concatenate('Ü')" style="margin-top: 20px">Ü</button>
                <button type="button" onclick="concatenate('ß')" style="margin-top: 20px">ß</button>
            </div>

            <input type="submit" value="Hinzufügen">
        </form>

        <h2>Nomen</h2>
        <table>
            <thead>
            <tr>
                <th>Nomen auf Deutsch</th>
                <th>Plural</th>
                <th>Nomen auf Russisch</th>
                <th style="width: 10%">Löschen</th>
            </tr>
            </thead>
            <tbody>
            <#if listOfNoun?has_content>
                <#list listOfNoun as noun>
                    <tr id="${noun.getId()}">
                        <td>${noun.german}</td>
                        <td>${noun.plural}</td>
                        <td>${noun.russian}</td>
                        <td style="padding: 0">
                            <button class="shadowButton" onclick="deleteLine(${noun.getId()})">löschen</button>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="3">Keine Nomena verfügbar.</td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>
    <script title="vars">
        let activeElement = null;
    </script>
    <script title="save new noun">
        document.getElementById('addWordForm').addEventListener('submit', function (event) {
            event.preventDefault();

            //Form data
            const german = document.getElementById('german_word').value.trim();
            const plural = document.getElementById('plural_form').value.trim();
            const russian = document.getElementById('russian_word').value.trim();
            // Check format
            const germanRegex = /^(der|die|das|Der|Die|Das)\s+[\wßäöüÄÖÜ]+$/;
            const pluralRegex = /^(die|Die)\s+[\wßäöüÄÖÜ]+$/;

            if (!germanRegex.test(german)) {
                alert("The German word must start with 'der', 'die', or 'das' and contain exactly two words.");
                return;
            }
            if (!pluralRegex.test(plural)) {
                alert("The plural form must start with 'die' and contain exactly two words.");
                return;
            }

            const formData = {
                russian: document.getElementById('russian_word').value,
                german: document.getElementById('german_word').value,
                plural: document.getElementById('plural_form').value,
            };

            fetch('/saveNewNoun', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the new word
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
    <script title="delete noun">
        function deleteLine(id) {
            fetch('/deleteNoun', {
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
    <script title="active element change">
        function changeActiveElement(id) {
            activeElement = document.getElementById(id)
        }
    </script>
    <script title="concatenate letter">

        function concatenate(letter) {
            if (activeElement !== null){
                if (activeElement.tagName === 'INPUT' && activeElement.type === 'text') {
                    activeElement.value += letter;
                    activeElement.focus()
                }
            }
        }
    </script>
</main>
</body>
</html>
