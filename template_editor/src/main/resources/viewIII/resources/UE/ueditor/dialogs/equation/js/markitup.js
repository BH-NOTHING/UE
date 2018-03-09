/**
 * Copyleft 2014 pcb (puchonbin@163.com)
 *   
 */
var markupSet = [
		{ name: '分数', replaceWith: '{/}frac{b}{a} ' },
		{ name: '平方根', replaceWith: '{/}sqrt{ab} ' },
		{ name: '根号', replaceWith: '{/}sqrt[n]{ab} ' },
		{ name: '上标', replaceWith: 'x^{a} ' },
		{ name: '下标', replaceWith: 'x_{a} ' },
		{ name: '上下标', replaceWith: 'x_{a}^{b} ' },
		{ name: '倒数', replaceWith: '{/}dot{x} ' },
		{ name: '下划线', replaceWith: '{/}underline{ab} ' },
		{ name: '上划线', replaceWith: '{/}overline{ab} ' },
//		{ name: '矢量(右)', replaceWith: '{/}overrightarrow{ab} ' },
//		{ name: '矢量(左)', replaceWith: '{/}overleftarrow{ab} ' },
//		{ name: '矢量', replaceWith: '{/}widehat{ab} ' },
//		{ name: '矩阵', replaceWith: '{/}begin{bmatrix}a & b {/}{/}c & d {/}end{bmatrix} ' },
		{ name: '\'', replaceWith: '{/}prime ' },
		{ name: '求和', replaceWith: '{/}sum_{x}^{y}{z} ' },
		{ name: '+', replaceWith: ' + ' },
		{ name: '-', replaceWith: ' - ' },
		{ name: '×', replaceWith: '{/}times ' },
		{ name: '÷', replaceWith: '{/}div ' },
		{ name: '不等于', replaceWith: '{/}neq ' },
		{ name: '因为', replaceWith: '{/}because ' },
		{ name: '所以', replaceWith: '{/}therefore ' },
		{ name: '正负', replaceWith: '{/}pm ' },
		{ name: '并集', replaceWith: '{/}cup ' },
		{ name: '交集', replaceWith: '{/}cap ' },
		{ name: '垂直', replaceWith: '{/}perp ' },
		{ name: '无穷', replaceWith: '{/}infty ' },
//		{ name: '方程组', replaceWith: '{/}begin{cases} {a} {/}{/} {b} {/}end{cases} ' },
//		{ name: '化学反应方程式', replaceWith: '{/}mathop{={/}!={/}!=}^{a}_{b} ' },
//		{ name: '极限', replaceWith: '{/}lim_{n {/}to {/}infty} ' },
		{ name: '小于等于', replaceWith: '{/}leq ' },
		{ name: '大于等于', replaceWith: '{/}geq ' },
		{ name: '子集', replaceWith: '{/}subset ' },
		{ name: '父集', replaceWith: '{/}supset ' },
		{ name: '子集或等于', replaceWith: '{/}subseteq ' },
		{ name: '父集或等于', replaceWith: '{/}supseteq ' },
		{ name: '包含于', replaceWith: '{/}in ' },
		{ name: '不包含于', replaceWith: '{/}ni ' },
		{ name: '大于', replaceWith: ' > ' },
		{ name: '小于', replaceWith: ' < ' },
		{ name: '约等于', replaceWith: '{/}approx ' },
		{ name: '相似', replaceWith: '{/}sim ' },
        { name: '全等', replaceWith: '{/}cong ' },
		{ name: '上箭头', replaceWith: '{/}uparrow ' },
		{ name: '下箭头', replaceWith: '{/}downarrow ' },
		{ name: 'α', replaceWith: '{/}alpha ' },
		{ name: 'β', replaceWith: '{/}beta ' },
		{ name: 'γ', replaceWith: '{/}gamma ' },
		{ name: 'θ', replaceWith: '{/}theta ' },
		{ name: 'λ', replaceWith: '{/}lambda ' },
		{ name: 'π', replaceWith: '{/}pi ' },
		{ name: 'μ', replaceWith: '{/}mu ' },
		{ name: 'ρ', replaceWith: '{/}rho ' },
		{ name: '∑', replaceWith: '{/}Sigma ' },
		{ name: 'Ω', replaceWith: '{/}Omega ' },
		{ name: 'Δ', replaceWith: '{/}Delta ' },
		{ name: '空集', replaceWith: '{/}phi ' },
		{ name: '三角形', replaceWith: '{/}triangle ' },
		{ name: '角', replaceWith: '{/}angle ' },
		{ name: '圆', replaceWith: '{/}odot ' }
];

function mathHtml() {
    var html = "";
    $(markupSet).each(function (i, e) {
        html += "<li ><a title=\"" + e.name + "\" href=\"javascript:toformula('"+e.replaceWith + "')\">" + e.name + "</a></li>";
    });
    $(".markItUpHeader").children("ul").html(html);
}

function toformula(code) {
    $("#matheq_latex").focus().mathquill("write", code.replace("{/}", "\\"));
    var codehtml=$("#matheqCode").html();
    $("#matheqCode").html(codehtml+code.replace("{/}", "\\"));
}
//输入公式html 转换为编码
$(function(){
    $("#matheq_latex").bind('keydown keypress', function() {
        setTimeout(function() {
          var latex = $("#matheq_latex").mathquill('latex');
           $("#matheqCode").html(latex);
           $("#matheq_latex1").mathquill('latex', latex);
          //htmlSource.text(printTree($("#matheq_latex").mathquill('html')));
        });
      }).keydown().focus();
      
   
   if(location.hash && location.hash.length > 1)
      $("#matheq_latex").mathquill('latex', decodeURIComponent(location.hash.slice(1))).focus();
 });
 //编码转换为 html
 
setTimeout(function () {
    mathHtml();
    //公式编辑框
    $("#matheq_latex").html("").css("font-size","19px;").mathquill('editable').mathquill('write', "");
}, 10);
