<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        var pageSize = "${pageinfo.pageSize}";
        var pageNumber = "${pageinfo.pageNumber}";
        var sname = "${pageinfo.sname}";
        var tname = "${pageinfo.tname}";
        var total = ${pageinfo.total};
        //i表示循环脚标，n表示迭代变量
        $.each($(":radio"), function (i, n) {
            if ($(n).val() == pageSize) {
                $(n).attr("checked", "checked");
            }
        });
        $(":text[name='sname']").val(sname);
        $(":text[name='tname']").val(tname);

        $("button").click(function () {
            location.href = "show?pageSize=" + pageSize + "&pageNumber=1&tname=" + $(":text[name='tname']").val() + "&sname=" + $(":text[name='sname']").val();
        });
        $(":radio").click(function () {
            pageSize = $(this).val();
            location.href = "show?pageSize=" + pageSize + "&pageNumber=1&tname=" + $(":text[name='tname']").val() + "&sname=" + $(":text[name='sname']").val();
        })
        $(".page:eq(0)").click(function () {
            pageNumber = parseInt(pageNumber) - 1;
            if (pageNumber >= 1) {
                window.location.href = "show?pageSize=" + pageSize + "&pageNumber=" + pageNumber + "&tname=" + $(":text[name='tname']").val() + "&sname=" + $(":text[name='sname']").val();
            } else {
                pageNumber = 1;
            }
            return false;
        })
        $(".page:eq(1)").click(function () {
            pageNumber =parseInt(pageNumber)+ 1;
            if (pageNumber <= total) {
                window.location.href = "show?pageSize=" + pageSize + "&pageNumber=" + pageNumber + "&tname=" + $(":text[name='tname']").val() + "&sname=" + $(":text[name='sname']").val();
            } else {
                pageNumber = total;
            }
            return false;
        })
    })
</script>
<body style="text-align: center;margin: 100px;padding: 100px;">
<input type="radio" value="2" name="pageSize" style="margin-top: 30px;">2
<input type="radio" value="3" name="pageSize" style="margin-top: 30px;">3
<input type="radio" value="4" name="pageSize" style="margin-top: 30px;">4
<br>
学生姓名<input type="text" name="sname">
教师姓名<input type="text" name="tname">
<button>查询</button>
<br>
<table border="1" style="margin: auto;width: 60%;margin-top: 30px;">
    <tr>
        <th>学生编号</th>
        <th>学生姓名</th>
        <th>年龄</th>
        <th>教师名称</th>
    </tr>
    <c:forEach var="pageinfo" items="${pageinfo.list}">
        <tr>
            <td>${pageinfo.id}</td>
            <td>${pageinfo.name}</td>
            <td>${pageinfo.age}</td>
            <td>${pageinfo.teacher.name}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a class="page" href="">上一页</a>
<a class="page" href="">下一页</a>
</body>
</html>
