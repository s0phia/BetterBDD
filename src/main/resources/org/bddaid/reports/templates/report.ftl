<!DOCTYPE html>

<link rel="stylesheet" href="org/bddaid/reports/templates/style.css">
<link rel="stylesheet" href="org/bddaid/reports/css/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="org/bddaid/reports/templates/script.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Result', 'Features scans'],
            ['Passed',  ${featuresPassedCount}],
            ['Failed',   ${featuresFailedCount}],
        ]);

        var options = {
            title: ''
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
</script>


</head>
</p>

<div id="logo"><a href="index.html"><img src="org/bddaid/reports/images/logo.jpeg" border="0"></a></div>

<body onload="openTab(event, 'Summary')">
<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'Summary')" id="defaultOpen">
        <h2>Execution Summary</h2>
    </button>
    <button class="tablinks" onclick="openTab(event, 'Rules')">
        <h2>Rules<h2>
    </button>
</div>


<tr>
    <div id="Summary" class="tabcontent">


        <div class="test-count-summary">
            <h2>BDD Scan Results</h2>
            <li style="list-style-type: none">${featuresTotal} features (${featuresPassedCount}
                passed, ${featuresFailedCount}
                failed)
            </li>
            <li style="list-style-type: none">${rulesTotal} rules (${rulesPassed} passed, ${rulesFailed}
                failed)
            </li>

        </div>
        <div class="container">
            <div id="piechart" class="one"></div>
            <div class="two">
                <div>
                    <h4>Rule Execution Summary</h4>
                    <table class="summary-table">
                        <tbody>
                        <tr>
                            <th>Rule Category</th>
                            <th>Total</th>
                            <th>Pass</th>
                            <th>Fail</th>
                        </tr>

                        <#list ruleCategoryMap as key, value>
                        <tr>
                            <td class="summary-leading-column">${key}</td>
                            <td>${value}</td>
                            <td>9 (90%)</td>
                            <td>1 (10%)</td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>


        <div>
            <table border="0">
                <tbody>
                <tr>
                    <td valign="bottom">
                        <h3>&emsp;Features scanned:</h3>
                    </td>
                </tr>
                <#list featuresPassed as feature>
                <tr>
                    <td>&emsp;&emsp;
                        <i class="fa fa-check-square-o success-icon "></i> ${feature.path}
                    </td>
                </tr>
                </#list>
                <#list featuresFailed as feature>

                <tr>
                    <td>&emsp;&emsp;
                        <i class="fa fa-exclamation-triangle error-icon " aria-hidden="true"></i> ${feature.path}
                    </td>
                </tr>
                </#list>

                </tbody>
            </table>

            <table border="0">
                <tbody>
                <tr>
                    <td>&emsp;
                        <h3> Rules executed:</h3>
                    </td>
                </tr>
                <#list rules as rule>
                <tr>
                    <td>&emsp;&emsp;
                        <i class="fa fa-pencil-square-o" aria-hidden="true"> ${rule.name}
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>

        </div>

    </div>


    <div id="Rules" class="tabcontent">
        <div class="test-count-summary">
            <h4>List of available BDD rules</h4>
        </div>
        <table border="0">
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

</body>
