<html>
<link rel="stylesheet" href="org/bddaid/reports/templates/style.css">
<script src="org/bddaid/reports/templates/script.js"></script>

<head>
    <title>${title}</title>
</head>

<body onload="openTab(event, 'Summary')">

<h1>Better BDD</h1>
<p>Make your BDDs Better!</p>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'Summary')" id="defaultOpen">Execution Summary</button>
    <button class="tablinks" onclick="openTab(event, 'Rules')">Rules</button>
</div>

<div id="Rules" class="tabcontent">
    <h4>List of available BDD rules</h4>
    <table border="1">
        <tbody>
        <tr>
            <td>
                <ul>
                <#list allRules as system>
                    <li>${system}</li>
                </#list>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div id="Summary" class="tabcontent">
    <h3>BDD Scan Results</h3>
    <li>${featuresTested} features (${featuresPassed} passed, ${featuresFailed} failed)</li>
    <li>${featuresTested} rules (${featuresPassed} passed, ${featuresFailed} failed)</li>

    <h4>Features Scanned</h4>
    <table border="1">
        <tbody>
        <tr>
            <td>
                <ul>
                <#list featuresAnalysed as featuresAnalysed>
                    <li>${featuresAnalysed.path}</li>
                </#list>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>

    <h4>Rules executed</h4>
    <table border="1">
        <tbody>
        <tr>
            <td>
                <ul>
                <#list rules as rule>
                    <li>${rule.name}</li>
                </#list>
                </ul>

            </td>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>