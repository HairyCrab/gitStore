<%--
  Created by IntelliJ IDEA.
  User: LiY
  Date: 2021/8/11
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border: 2px solid red;
            margin: 0px auto;
            width: 80%;
        }
        td,th{
            border: 2px solid green;
        }

    </style>
    <script src="js/jquery.min.js"></script>
    <script>
        function changePage(page) {
            //alert(page)
            if(page<1){
                alert("前面没有啦！")
                return;
            }else if(page>${requestScope.StudentPageBean.totalPages}){
                alert("后面没有啦！")
                return;
            }
            window.location.href="showStudent.do?stname="+$("#stname").val()+"&lage="+$("#lage").val()+"&currentPage="+page+"&pageSize="+$("#pz").val();

        }
        var input = document.getElementById("pz");
        input.addEventListener("keyup", function(event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                //document.getElementById("myBtn").click();
                changePage(1);
            }
        });
    </script>
</head>
<body>
<div style="text-align: center">
    学生姓名：<input type="text" name="stname" id="stname" value="${requestScope.stname}">
    最低年龄限制：<input type="text" name="lage" id="lage" value="${requestScope.lage}">
    <input type="button" value="搜索" onclick="changePage(1)">
</div>
<table>
    <tr>
        <th>学生编号</th>
        <th>学生姓名</th>
        <th>学生年龄</th>
        <th>学生性别</th>
        <th>照片</th>
        <th>照片类型</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.StudentPageBean.studentData}" var="student">
        <tr>
            <td>${student.stuid}</td>
            <td>${student.stuname}</td>
            <td>${student.stuage}</td>
            <td>${student.stugender}</td>
            <td>${student.filename}</td>
            <td>${student.filetype}</td>
            <td ><a href="">删除</a></td>
        </tr>

    </c:forEach>
    <
    <tr align="center">
        <td colspan="7">
            <a href="javascript:void(0)" onclick="changePage(${requestScope.StudentPageBean.currentPage}-1)">上一页</a>&nbsp;&nbsp;&nbsp;
            <c:forEach begin="1" end="${requestScope.StudentPageBean.totalPages}" var="num" step="1">
                <c:choose>
                    <c:when test="${num eq requestScope.StudentPageBean.currentPage}">
                        [${num}]
                    </c:when>
                    <c:otherwise>
                        ${num}
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <a href="javascript:void(0)" onclick="changePage(${requestScope.StudentPageBean.currentPage}+1)">下一页</a>&nbsp;&nbsp;&nbsp;
            尾&nbsp;页&nbsp;&nbsp;&nbsp;
            每页<input id="pz" style="width: 40px"  value="${requestScope.StudentPageBean.pageSize}">条&nbsp;&nbsp;&nbsp;
            当前第${requestScope.StudentPageBean.currentPage}页&nbsp;&nbsp;&nbsp;
            共${requestScope.StudentPageBean.totalPages}页&nbsp;&nbsp;&nbsp;
            共${requestScope.StudentPageBean.totalSize}条
        </td>
    </tr>
</table>
</body>
</html>
