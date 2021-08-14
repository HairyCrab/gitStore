<%--
  Created by IntelliJ IDEA.
  User: LiY
  Date: 2021/8/13
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery.min.js"></script>
    <script>
        function showArea(val, selectID) {
            $.ajax({
                type: "GET",
                url: "servlet1.do?",
                data: {parentID: val},
                dataType: "json",
                success: function (pros) {
                    console.log(pros)
                    $(selectID).html('<option value="">请选择</option>')
                    if(selectID=="#city"){
                        $("#county").html('<option value="">请选择</option>')
                    }
                    $.each(pros, function (i, e) {
                        $(selectID).append('<option value="' + e.areaid + '" >' + e.areaname + '</option>')
                    })

                },
                error: function (info) {
                    alert("后端相应失败")
                }
            })
        }

        $(function () {

            showArea(0, "#province")
        })
    </script>
</head>
<body>
省市区
<select id="province" onchange=" showArea(this.value,'#city')">
    <option value="">请选择</option>
</select>
<select id="city" onchange="showArea(this.value,'#county')">
    <option value="">请选择</option>
</select>
<select id="county">
    <option value="">请选择</option>
</select>
</body>
</html>
