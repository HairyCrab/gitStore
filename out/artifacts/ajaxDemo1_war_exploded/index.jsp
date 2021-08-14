<%--
  Created by IntelliJ IDEA.
  User: LiY
  Date: 2021/8/12
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery.min.js"></script>
    <script>
        var xhr;
        function checkUsername() {
            var elementById = document.getElementById("usernameID");
            var info = document.getElementById("checkUID");
            var uname = elementById.value;
           alert($("#usernameID").val());
            if (null == uname || uname == '') {
                alert("用户名不能为空！")
                return;
            }
            info.innerText="";
            //发送异步请求
            //获取一个XMLHttpRequest对象，此对象可以发送异步请求
            xhr=new XMLHttpRequest();
            //使用xhr对象设置打开链接，设置 请求方式和参数 xhr.open("请求方式","请求的URL",是否使用异步方式);
            xhr.open("get","usernameController.do?uname="+uname,true);
            //设置回调函数
            xhr.onreadystatechange=showReturnInfo;
            //正式发送请求，post传值，get传null就行
            xhr.send(null);
        }
        function showReturnInfo() {
            //alert(xhr.readyState+"---"+xhr.status)
            if(xhr.readyState==4&&xhr.status==200){
               // alert("jinlaile--")
                var returnInfo = xhr.responseText;
                //alert(xhr.responseText)
                var elementById = document.getElementById("checkUID");
                elementById.innerText=returnInfo;

            }
        }
        function color(flag) {
            return flag;
        }
    </script>
</head>
<body>
<form action="">
    用户名：<input id="usernameID" type="text" onblur="checkUsername()"><span id="checkUID" style="color:red"></span><br>
    密码：<input id="passwordID" type="password"><br>
    <input type="submit" value="登录">
</form>


</body>
</html>
