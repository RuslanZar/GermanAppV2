<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe: Nomen</title>
    <style>
        header {
            width: 100%;
            background-color: #3498db;
            color: #fff;
            display: flex;
            margin: 0px;
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
            padding: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            box-sizing: border-box;
        }

        h1 {
            font-size: 40px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 0px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            height: 32px;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            padding: 10px 20px;
            background-color: #3498db;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            display: block;
            margin: 0 auto;
        }

        button:hover {
            background-color: #2980b9;
        }

        .result {
            margin-top: 20px;
            font-size: 18px;
            text-align: center;
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
    <table id="TableOfNouns">
        <thead>
        <tr>
            <th style="width: 10%;">Russisches Wort</th>
            <th>Deutsches Wort</th>
            <th>Plural auf Deutsch</th>
            <th style="width: 2%">Helb</th>
        </tr>
        </thead>
        <tbody>
        <#if nounList?has_content>
            <#list nounList as noun>
                <tr id="row_${noun.getId()}">
                    <td id="id_content_${noun.getId()}">${noun.russian}</td>
                    <td><input onclick="changeActiveElement(id)" class="your_answer_german" id="your_answer_german_${noun.getId()}" type="text"></td>
                    <td><input onclick="changeActiveElement(id)" class="your_answer_plural" id="your_answer_plural_${noun.getId()}" type="text"></td>
                    <td>
                        <button type="button"
                                onclick="fillAnswer('${noun.getGerman()}','${noun.getPlural()}',${noun.getId()})">fill
                        </button>
                    </td>
                </tr>
            </#list>
        <#else>
        </#if>
        </tbody>
    </table>
    <div style="display: flex; flex-direction: row; width: 400px; margin: 0 auto;">
        <button type="button" onclick="concatenate('ä')" style="margin-top: 20px">ä</button>
        <button type="button" onclick="concatenate('Ä')" style="margin-top: 20px">Ä</button>
        <button type="button" onclick="concatenate('ö')" style="margin-top: 20px">ö</button>
        <button type="button" onclick="concatenate('Ö')" style="margin-top: 20px">Ö</button>
        <button type="button" onclick="concatenate('ü')" style="margin-top: 20px">ü</button>
        <button type="button" onclick="concatenate('Ü')" style="margin-top: 20px">Ü</button>
        <button type="button" onclick="concatenate('ß')" style="margin-top: 20px">ß</button>
    </div>
    <button id="checkButton" onclick="checkAllAnswers()" style="margin-top: 10px">Prüfen</button>
    <div class="result" id="result"></div>
</main>
<script title="vars">
    let activeElement = null;
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
<script title="Check all inputs">

    let sent = false

    function checkAllAnswers() {

        let rightAnswers = [
            <#list nounList as noun>
            {
                id: "${noun.getId()}",
                german: "${noun.getGerman()}",
                plural: "${noun.getPlural()}"
            }<#if noun_has_next>, </#if>
            </#list>
        ]

        console.log(rightAnswers)

        let correctIndexes = []
        let incorrectIndexes = []

        let germanAnswers = document.querySelectorAll('input.your_answer_german');
        let pluralAnswers = document.querySelectorAll('input.your_answer_plural');
        let number = germanAnswers.length

        for (let i = 0; i < number; i++) {
            if ((germanAnswers[i].value.charAt(0).toLowerCase() + germanAnswers[i].value.slice(1))
                &&
                ((pluralAnswers[i].value.charAt(0).toLowerCase() + pluralAnswers[i].value.slice(1)) === rightAnswers[i].plural)) {
                console.log(germanAnswers[i].value + ' and ' + pluralAnswers[i].value + '  is correct!');
                correctIndexes.push(rightAnswers[i].id)
                document.getElementById('row_' + rightAnswers[i].id).style.backgroundColor = 'green'
            } else {
                console.log(germanAnswers[i].value + ' and ' + pluralAnswers[i].value + '  is NOT correct!');
                incorrectIndexes.push(rightAnswers[i].id)
                document.getElementById('row_' + rightAnswers[i].id).style.backgroundColor = 'red'
            }
        }
        if (!sent) {
            fetch('/addScoreToNouns', {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(correctIndexes)
            })
                .then(response => {
                    if (response.ok) {
                        console.log(sent)
                        // location.reload(); // Reload the page to show the new word
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again later.');
                });

            fetch('/removeScoreToNouns', {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(incorrectIndexes)
            })
                .then(response => {
                    if (response.ok) {
                        sent = true
                        console.log(sent)
                        // location.reload(); // Reload the page to show the new word
                    } else {
                        alert('Failed. Please try again.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again later.');
                });
        }
    }
</script>
<script title="Fill right answer">
    function fillAnswer(german, plural, id) {
        document.getElementById("your_answer_german_" + id).value = german
        document.getElementById("your_answer_plural_" + id).value = plural
    }
</script>
</body>
</html>
