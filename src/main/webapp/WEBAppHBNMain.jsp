<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HBN 2023 DDE</title>
</head>
<body>
<h1>Hibernate Demo Project 2023</h1>
<h2>PhD Associate Professor Dmytro Dvukhhlavov</h2>
<h2>SEMIT, CSIT, NTU "KhPI"</h2>
<br><br><br>
<h3>Сьогодні <%=java.time.LocalDate.now() %></h3>
<br><br>
<form action="employees" method="GET">
    <input type="submit" value="Employees">
</form>
</body>
</html>