/**
 * 登录页面的Login的js
 */
// if (window != window.top && window.top.location.href != '/pwplogin/toDevLoginPage.do') {
//     window.top.location.href = ctx + '/pwplogin/toLoginPage.do'
// }

function login() {
    if($('#theLoginForm input[name=j_username]').val() == '' || $('#theLoginForm input[name=j_password]').val() == '') {
        return;
    }
    $('.ipt-btn .msg').html('正在登录...');

    $("#theLoginForm").ajaxSubmit({
        success : function (data) {
            console.log(data);
            if(data == null) {
                $('.ipt-btn .msg').text("用户或密码错误，登录失败！");
                return;
            }
            if(data.code == 200) {
                //登录成功
                $("#door").find(".door-left").addClass("door-left-hover");
                $("#door").find(".door-right").addClass("door-right-hover");
                $("#theLoginForm").css("display", "none");

                //3秒后跳转去首页
                setTimeout(function() {
                    window.location.href = ctx + "/pwplogin/toMainframePage.do";
                }, 3000);
            } else {
                $('.ipt-btn .msg').text(data.msg);
            }
        }
    });
}

$(function () {
    //按下鼠标回车键 登录
    $("body").keydown(function (event) {
        if (event.keyCode == 13) {
            login();
        }
    });
    $("#loginBtn").on("click", function () {
        login();
    });

    //点击 增加高亮 ipt-n focus
    $('.login-from input[type="text"], .login-from input[type="password"]').focus(function(){
        $(this).closest('div').addClass('focus');
    });
    $('.login-from input[type="text"], .login-from input[type="password"]').blur(function(){
        $(this).closest('div').removeClass('focus');
    });

})