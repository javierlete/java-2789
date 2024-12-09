<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo MVC</title>
</head>
<body>

	<ul>
		<c:forEach items="${coleccion}" var="dato">
			<li>${dato}</li>
		</c:forEach>
	</ul>

</body>
</html>