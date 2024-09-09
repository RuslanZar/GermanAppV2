<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabengenerator</title>
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

        input[type="text"], select {
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

        input[type="submit"],button:hover {
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
    <a href="/nouns" class="menu-button">Nomen</a>
    <a href="/adjectives" class="menu-button">Adjective</a>
    <a href="/verbs" class="menu-button">Verben</a>
    <a href="/phrases" class="menu-button">Phrasen</a>
    <a href="/exerciseGeneration" class="menu-button">Üben</a>
    <a href="/profile" class="menu-button">Ihr Profil</a>
    <a href="/logout" class="logout">Abmelden</a>
</nav>

<main>
    <form id="exerciseDataForm" action="/generatedExercise">
        <div>
            <label for="exerciseType">Wählen Sie den Übungsart:</label>
            <select id="exerciseType" name="exerciseType">
                <option value="Nomen" name="exerciseType">Nomen</option>
                <option value="Verben" name="exerciseType">Verben</option>
                <option value="Phrasen" name="exerciseType">Phrasen</option>
            </select>
        </div>
        <div>
            <label for="count">Anzahl:</label>
            <input type="text" id="count" name="number" placeholder="Geben Sie die Anzahl ein">
        </div>
        <button onclick="generateExercise()">Aufgabe generieren</button>
<#--        <input type="submit" value="Aufgabe generieren">-->
    </form>
    <div id="result"></div>

    <script>
        function generateExercise(){
            event.preventDefault()
            const numberRegex = /^[0-9]+$/;
            if (numberRegex.test(document.getElementById('count').value)){
                document.getElementById("exerciseDataForm").submit();
            }else {
                alert("Number field should contain only number")
            }
        }
    </script>
</main>
</body>
</html>
