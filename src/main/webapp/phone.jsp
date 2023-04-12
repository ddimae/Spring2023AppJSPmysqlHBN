<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>webHBN - INN</title>
</head>
<body>
<input type="hidden" name="id" value="${empl.id}" required>
<p>Name<input name="name" value="" required></p>
<h1 style="alignment: center">INN +${empl.name}</h1>
<table style="alignment: center">
    <tr>
        <td><form action="add_inn">

        </form>
        </td>
        <th>Age</th>
        <th>Pol</th>
        <th>Salary</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
</table>
<table style="alignment: center">
    <tr>
        <td>Name</td>
        <th>Age</th>
        <th>Pol</th>
        <th>Salary</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
</table>

</body>
</html>
