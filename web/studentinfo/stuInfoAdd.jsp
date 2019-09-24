<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/23
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息新增页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/stuInfoServlet?method=add" method="post">
    <table>
        <caption>学生信息表新增</caption>
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="name" placeholder="请输入姓名"/>
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="男"/>男
                <input type="radio" name="sex" value="女"/>女
            </td>
        </tr>

        <tr>
            <td>年龄</td>
            <td>
                <input type="number" name="age" placeholder="请输入年龄"/>
            </td>
        </tr>

        <tr>
            <td>院系</td>
            <td>
                <input type="text" name="ys" placeholder="请输入院系"/>
            </td>
        </tr>
        <tr>
            <td>班级</td>
            <td>
                <input type="text" name="className" placeholder="请输入班级"/>
            </td>
        </tr>
        <tr>
            <td>入学日期</td>
            <td>
                <input type="date" name="hireDate"/>
            </td>
        </tr>
        <tr>
            <td>电话</td>
            <td>
                <input type="text" name="tel" placeholder="请输入电话"/>
            </td>
        </tr>
        <tr>
            <td>籍贯</td>
            <td>
                <input type="text" name="jg" placeholder="请输入籍贯"/>
            </td>
        </tr>
        <tr>

            <td colspan="2">
                <input type="submit" value="保存"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
