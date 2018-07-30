<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>luv2code company Home Page</title>
	</head>
	<body>
		<h2>luv2code company Home Page</h2>
		
		<hr/>
		
		<p>
			Welcome to the luv2code company home page!
		</p>
		
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		
			<input type="submit" value="Logout" />
			
		</form:form>
	</body>
</html>