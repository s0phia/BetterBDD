function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");

    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";


}
function openSummary() {

    document.getElementById("defaultOpen").click();

}

function plotGraph()   { // $(document).ready(function() {
//     $.jqplot.config.enablePlugins = false;
//
//     var l1 = [18, 36, 14, 11];
//     var l2 = [[2, 14], [7, 2], [8,5]];
//     var l3 = [4, 7, 9, 2, 11, 5, 9, 13, 8, 7];
//     var l4 = [['peech',3], ['cabbage', 2], ['bean', 4], ['orange', 5]];
//
//     $("#tabs").tabs();
//
//     var plot1 = $.jqplot('chart1', [[['a',25],['b',14],['c',7]]], {
//         gridPadding: {top:0, bottom:38, left:0, right:0},
//         seriesDefaults:{
//             renderer:$.jqplot.PieRenderer,
//             trendline:{ show:false },
//             rendererOptions: { padding: 8, showDataLabels: true }
//         },
//         legend:{
//             show:true,
//             placement: 'outside',
//             rendererOptions: {
//                 numberRows: 1
//             },
//             location:'s',
//             marginTop: '15px'
//         }
//     });
//
//     var plot2 = $.jqplot('chart2', [l4], {
//         height: 200,
//         width: 300,
//         series:[{renderer:$.jqplot.PieRenderer}],
//         legend:{show:true}
//     });
//
//     var catOHLC = [[1, 138.7, 139.68, 135.18, 135.4],
//         [2, 143.46, 144.66, 139.79, 140.02],
//         [3, 140.67, 143.56, 132.88, 142.44],
//         [4, 136.01, 139.5, 134.53, 139.48],
//         [5, 143.82, 144.56, 136.04, 136.97],
//         [6, 136.47, 146.4, 136, 144.67],
//         [7, 124.76, 135.9, 124.55, 135.81],
//         [8, 123.73, 129.31, 121.57, 122.5]];
//
//     var ticks = ['Tue', 'Wed', 'Thu', 'Fri', 'Mon', 'Tue', 'Wed', 'Thr'];
//
//
//     $('#tabs').bind('tabsshow', function(event, ui) {
//         if (ui.index === 1 && plot1._drawCount === 0) {
//             plot1.replot();
//         }
//         else if (ui.index === 2 && plot2._drawCount === 0) {
//             plot2.replot();
//         }
//     });
//
//
// });{


}