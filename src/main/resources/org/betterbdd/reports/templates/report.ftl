<!DOCTYPE html>

<link rel="stylesheet" href="org/betterbdd/reports/templates/style.css">
<link rel="stylesheet" href="org/betterbdd/reports/css/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="org/betterbdd/reports/templates/script.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">

    google.charts.load('current', {'packages': ['corechart']});

    google.charts.setOnLoadCallback(drawAnthonyChart);
    google.charts.setOnLoadCallback(drawSarahChart);

    function drawAnthonyChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Passed/Failed');
        data.addColumn('number', 'count');
        data.addRows([
            ['Passed', ${rulesPassed}],
            ['Failed', ${rulesFailed}]
        ]);

        var options = {
            title: 'Rules executed',
            width: 500,
            height: 300,
            chartArea: {left: 0, top: 0, width: "100%", height: "100%"}
        };

        var chart = new google.visualization.PieChart(document.getElementById('rules_pie_chart_div'));
        chart.draw(data, options);
    }

    function drawSarahChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Passed/Failed');
        data.addColumn('number', 'count');
        data.addRows([
            ['Passed', ${featuresPassedCount}],
            ['Failed', ${featuresFailedCount}]
        ]);

        var options = {
            title: 'Features Scanned',
            width: 500,
            height: 300,
            chartArea: {left: 0, top: 0, width: "100%", height: "100%"}
        };

        var chart = new google.visualization.PieChart(document.getElementById('features_pie_chart_div'));
        chart.draw(data, options);
    }

</script>

</head>
</p>
<div id="logo"><a href="index.html"><img src="org/bddaid/reports/images/logo.jpeg" border="0"></a></div>
<p style="float: right">Report generated: ${.now}<p>
<body onload="openTab(event, 'Summary')">
<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'Summary')" id="defaultOpen">
        <h3>Execution Summary</h3>
    </button>
    <button class="tablinks" onclick="openTab(event, 'Features')">
        <h3>Features</h3>
    </button>
    <button class="tablinks" onclick="openTab(event, 'Rules')">
        <h3>Rules</h3>
    </button>

</div>

<tr>
    <div id="Summary" class="tabcontent">
        <div class="test-count-summary">
            <h2>BDD Scan Results</h2>
            <li style="list-style-type: none">${rulesTotal} rules executed (${rulesPassed} passed, ${rulesFailed}
                failed)
            </li>

        </div>

        <table class="container" style="vertical-align: top">
            <div style="align-content: center">
                <tr>
                    <td style="width: 40%">
                        <div id="rules_pie_chart_div" style="border: 1px solid #ccc"></div>
                    </td>
                    <td style="padding-left: 5%; padding-top: 5px"><h4>Rule Execution Summary</h4>
                        <table class="summary-table">
                            <tbody>
                            <tr>
                                <th>Rule Category</th>
                                <th>Total</th>
                                <th>Pass</th>
                                <th>Fail</th>
                            </tr>

                            <#list ruleCategoryMap as key, value>
                                <#assign passCount = ruleCategoryPassedMap[key]>
                                <#assign failCount = ruleCategoryfailedMap[key]>
                            <tr>
                                <td class="summary-leading-column">${key}</td>

                                <td>${value}</td>
                                <td> ${passCount}</td>
                                <td>${failCount}</td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </td>
                </tr>
            </div>
        </table>
    </div>


    <div id="Rules" class="tabcontent">

        <table border="0">
            <tbody>
            <tr>
                <td>&emsp;
                    <h3>Rules executed</h3>
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

        <table border="0">
            <tbody>
            <tr>
                <td>&emsp;
                    <h3>List of available BDD rules</h3>
                </td>
            </tr>
            <#list allRules as rule1>
            <tr>
                <td>&emsp;&emsp;
                    <i class="fa fa-pencil-square-o" aria-hidden="true"> ${rule1}
                </td>
            </tr>
            </#list>
            </tbody>
        </table>


        <#--<table border="0">-->
            <#--<tbody>-->
            <#--<tr>-->
                <#--<td>&emsp;-->
                    <#--<h3>List of available BDD rules</h3>-->
                <#--</td>-->
            <#--</tr>-->
            <#--&lt;#&ndash;<div class="test-count-summary">&ndash;&gt;-->
                <#--&lt;#&ndash;<h4>List of available BDD rules</h4>&ndash;&gt;-->
            <#--&lt;#&ndash;</div>&ndash;&gt;-->
            <#--<tr>-->
                <#--<td>-->

                    <#--<#list allRules as system>-->
                       <#--${system}-->
                    <#--</#list>-->

                <#--</td>-->
            <#--</tr>-->
            <#--</tbody>-->
        <#--</table>-->
    </div>


    <div id="Features" class="tabcontent">
        <div class="test-count-summary">
            <h2>BDD Scan Results</h2>
            <li style="list-style-type: none">${featuresTotal} features scanned (${featuresPassedCount}
                passed, ${featuresFailedCount}
                failed)
            </li>
        </div>
        <table class="container">
            <tr>
                <td style="width: 100%">
                    <div id="features_pie_chart_div" style="border: 1px solid #ccc"></div>
                </td>

            </tr>
        </table>

        <div>
            <h3>&emsp;Features scanned:</h3>

            <div id="table-wrapper">
                <div id="table-scroll">


                    <table class="summary-table">
                        <thead>
                        <tr>
                            <th><span class="text">Feature</span></th>
                            <th><span class="text">Location</span></th>

                        </tr>
                        </thead>
                        <tbody>

                        <#list featuresPassed as feature>
                        <tr>
                            <td>
                                <i class="fa fa-check-square-o success-icon "></i> ${feature.fileName}
                            </td>
                            <td>
                            ${feature.path}
                            </td>
                        </tr>
                        </#list>
                        <#list featuresFailed as feature>

                        <tr>
                            <td>
                                <i class="fa fa-exclamation-triangle " aria-hidden="true"></i> ${feature.fileName}
                            </td>
                            <td>
                            ${feature.path}
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div id="table-wrapper">
            </div id="table-scroll">

        </div>
    </div>

</body>
