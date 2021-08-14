<%--
  Created by IntelliJ IDEA.
  User: LiY
  Date: 2021/8/13
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        function check() {
            alert("-------")
            $.ajax({
                type:"GET",
                url:"servlet2.do",
                data:"uname=aiai&password=32344",
                dataType:"json",
                success:function (info) {
                    alert(info.stuUname);
                }

            })

        }
    </script>
</head>
<body>
<input type="button" value="测试" onclick="check()">

</body>
</html>
