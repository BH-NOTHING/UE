$(document).ready(function () {
    var $nav = $(".bs-docs-sidenav");//导航栏
    $.each($(".bs-docs-section"), function (i, n) {
        var $this = $(this);
        var $pageHeader = $this.find(".page-header");
        var phleng = $pageHeader.length;
        var html = "<li>";
        for (var i = 0; i < phleng; i++) {
            var liId = $($pageHeader[i]).attr("id");
            var liName = $($pageHeader[i]).text();
            if (liName.indexOf("SNAPSHOT") > -1) {
                liName = liName.replace('SNAPSHOT', 'snapshot');
            }
            if (i == 0) {
                html += "<a href='#" + liId + "'>" + liName + "</a> ";
            } else {
                html += "<ul class='nav'><li> <a href='#" + liId + "'>" + liName + "</a> </li></ul>";
            }
        }
        html += "</li>";
        $nav.append(html);
    })
});