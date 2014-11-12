<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="route" uri="/springmvc-router" %>
           
<html>
  <head>
  	<title>BodyWired API - Documentation</title>
  	<link type="text/css" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
  </head>

  <body>
	<h1>Routes</h1>

	<table border="1" class="table table-striped">
		<tr>
			<th>Name</th>
			<th>URL</th>
		</tr>
		<c:forEach var="item" items="${routes}" varStatus="status">
			<tr>
				<td>${item.name}</td>
				<td>${item.url}</td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>