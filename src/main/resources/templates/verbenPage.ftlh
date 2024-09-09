<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>German App v${.version}</title>
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
<header >
    <h1 style="font-size: 40px; color: white; text-align: center; width : 100%">German App v${version}</h1>
</header>

<nav>
    <#list menuList as element>
        <a href=${element.getEndpoint()} class=${element.getHtmlClass()}>${element.getDisplayName()}</a>
    </#list>
</nav>

<main>
    <div class="container">
        <h1>Fügen Sie die neuen Verben hinzu</h1>
        <form id="addVerbForm">
            <div>
                <label for="russian_word">Verb auf Russisch:</label>
                <input type="text" id="russian_word" name="russian_word" required>
            </div>

            <div>
                <label for="infinitive">Infinitiv/wir/Sie/sie:</label>
                <input type="text" id="infinitive" name="infinitiv" required>
            </div>

            <div>
                <label for="prateritum">Präteritum ich:</label>
                <input type="text" id="prateritum" name="präteritum">
            </div>

            <div>
                <label for="particip2">Partizip II:</label>
                <input type="text" id="particip2" name="participle">
            </div>

            <div>
                <label for="ich_form">ich:</label>
                <input type="text" id="ich_form" name="ich_form">
            </div>

            <div>
                <label for="du_form">du:</label>
                <input type="text" id="du_form" name="du_form">
            </div>

            <div>
                <label for="er_sie_es_form">er/sie/es:</label>
                <input type="text" id="er_sie_es_form" name="er_sie_es_form">
            </div>

            <div>
                <label for="ihr_form">ihr:</label>
                <input type="text" id="ihr_form" name="ihr_form">
            </div>

<#--            <div>-->
<#--                <label for="sie_Sie_form">Sie/sie:</label>-->
<#--                <input type="text" id="sie_Sie_form" name="sie_Sie_form">-->
<#--            </div>-->

            <input type="submit" value="Hinzufügen">
        </form>

        <h2>Existing Verbs</h2>
        <table>
            <thead>
            <tr>
                <th>Russisch</th>
                <th>infinitiv/wir/Sie/sie</th>
                <th>Präteritum ich</th>
                <th>Partizip II</th>
                <th>ich</th>
                <th>du</th>
                <th>er/sie/es</th>
                <th>ihr</th>
<#--                <th>Sie (plural, formal)</th>-->
                <th>löschen</th>
            </tr>
            </thead>
            <tbody>
            <#if listOfVerbs?has_content>
                <#list listOfVerbs as verb>
                    <tr id="${verb.getId()}">
                        <td>${verb.russian}</td>
                        <td>${verb.infinitiv}</td>
                        <td>${verb.prateritum}</td>
                        <td>${verb.partizip2}</td>
                        <td>${verb.ich}</td>
                        <td>${verb.du}</td>
                        <td>${verb.er_sie_es}</td>
                        <td>${verb.ihr}</td>
<#--                        <td>${verb.sie_Sie}</td>-->
                        <td style="padding: 0">
                            <button class="shadowButton" onclick="deleteLine(${verb.getId()})">löschen</button>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="9">Keine Verben verfügbar.</td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>

    <script>
        document.getElementById('addVerbForm').addEventListener('submit', function (event) {
            event.preventDefault();

            // Collect form data
            const formData = {
                russian: document.getElementById('russian_word').value,
                infinitiv: document.getElementById('infinitive').value,
                prateritum: document.getElementById('prateritum').value,
                partizip2: document.getElementById('particip2').value,
                ich: document.getElementById('ich_form').value,
                du: document.getElementById('du_form').value,
                er_sie_es: document.getElementById('er_sie_es_form').value,
                ihr: document.getElementById('ihr_form').value,
                // sie_Sie: document.getElementById('sie_Sie_form').value,
                score: 0
            };

            fetch('/saveNewVerb', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the new verb
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
            fetch('/deleteVerb', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({id: id})
            })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Reload the page to show the updated list
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again later.');
                });
        }
    </script>
</main>
</body>
</html>