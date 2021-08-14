<%--
  Created by IntelliJ IDEA.
  User: LiY
  Date: 2021/8/12
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery.min.js"></script>
    <style type="text/css">
        .canUse {
            color: green;
        }

        .noUse {
            color: red;
        }
    </style>
    <script>
        var xhr;

        function checkUsername() {
            var elementById = document.getElementById("usernameID");
            var info = document.getElementById("checkUID");
            var uname = elementById.value;
            //alert($("#usernameID").val());
            if (null == $("#usernameID").val() || '' == $("#usernameID").val()) {
                alert("用户名不能为空！")
                return;
            }
            $.ajax({
                type: "GET",
                url: "usernameController.do?",
                data: "uname=" + $("#usernameID").val(),
                success: function (info) {
                    //alert(info);
                    if (info == "true") {
                        //$("#checkUID").addClass("canUse")
                        $("#checkUID").css("color","green")
                        $("#checkUID").text("此名称可用")
                    } else {
                        $("#checkUID").addClass("noUse")
                        $("#checkUID").text("此名称不可用")
                    }
                },
                error: function () {
                    alert("响应失败");

                }
            })

        }
    </script>
</head>
<body>
<form action="">
    用户名：<input id="usernameID" type="text" onblur="checkUsername()"><span id="checkUID"></span><br>
    密码：<input id="passwordID" type="password"><br>
    <input type="submit" value="登录">
</form>


</body>
</html>

