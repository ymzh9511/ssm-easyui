<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width=device-width" />
<!-- 引入JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.4.2/jquery.min.js"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入EasyUI的样式文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.4.2/themes/gray/easyui.css" type="text/css"/>
<!-- 引入EasyUI的图标样式文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.4.2/themes/icon.css" type="text/css"/>
<head>
    <title>Login</title>
</head>
<body>
<div style="margin-top: 500px">
<div id="loginWin" class="easyui-window" title="登录" style="width:350px;height:188px;padding:5px;"
     minimizable="false" maximizable="false" resizable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form id="loginForm" method="post">
                <div style="padding:5px 0;">
                    <label for="login">帐号:</label>
                    <input type="text" name="username" style="width:260px;" />
                </div>
                <div style="padding:5px 0;">
                    <label for="password">密码:</label>
                    <input type="password" name="password" style="width:260px;" />
                </div>
                <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
            </form>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="login()">登录</a>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    document.onkeydown = function (e) {
        var event = e || window.event;
        var code = event.keyCode || event.which || event.charCode;
        if (code == 13) {
            login();
        }
    }
    $(function () {
        $("input[name='username']").focus();
    });
    function cleardata() {
        $('#loginForm').form('clear');
    }
    function login() {
        if ($("input[name='username']").val() == "" || $("input[name='password']").val() == "") {
            $("#showMsg").html("用户名或密码为空，请输入");
            $("input[name='username']").focus();
        } else {
            //ajax异步提交
            $.ajax({
                type: "POST",   //post提交方式默认是get
                url: "${pageContext.request.contextPath}/user/login",
                data: $("#loginForm").serialize(),   //序列化
                error: function (request) {      // 设置表单提交出错
                    $("#showMsg").html(request);  //登录错误提示信息
                },
                success: function (data) {
                    var flag =data.result
                    if (flag==false) {
                        return false;
                    }

                    if (flag==true){

                        window.location = "user/index";
                        $.messager.alert("系统提示", "登陆成功！");
                        return true;
                    }

                }
            });
        }
    }
</script>
</body>
</html>
