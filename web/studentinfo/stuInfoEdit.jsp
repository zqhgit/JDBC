<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/23
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息编辑页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/stuInfoServlet?method=edit" method="post">
    <input type="hidden" name="id" value="${stuInfo.id}"/>
    <input type="hidden" name="flag" value="${stuInfo.flag}"/>
    <table>
        <caption>学生信息编辑表</caption>
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="name" value="${stuInfo.name}"/>
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="男" <c:if test="${stuInfo.sex=='男'}">checked</c:if>/>男
                <input type="radio" name="sex" value="女" <c:if test="${stuInfo.sex=='女'}">checked</c:if>/>女
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>
                <input type="number" name="age" value="${stuInfo.age}" placeholder="请输入年龄"/>
            </td>
        </tr>
        <tr>
            <td>院系</td>
            <td>
                <input type="text" name="ys" value="${stuInfo.ys}" placeholder="请输入院系"/>
            </td>
        </tr>
        <tr>
            <td>班级</td>
            <td>
                <input type="text" name="className" value="${stuInfo.className}" placeholder="请输入班级"/>
            </td>
        </tr>
        <tr>
            <td>入学日期</td>
            <td>
                <input type="date" name="hireDate" value="${stuInfo.hireDate}"/>
            </td>
        </tr>
        <tr>
            <td>电话</td>
            <td>
                <input type="text" name="tel" value="${stuInfo.tel}" placeholder="请输入电话"/>
            </td>
        </tr>
        <tr>
            <td>籍贯</td>
            <td>
                <input type="text" name="jg" value="${stuInfo.jg}" placeholder="请输入籍贯"/>
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
