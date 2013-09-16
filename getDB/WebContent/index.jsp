<!DOCTYPE html>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
	<head>
		<meta charset="utf-8" />		
		<title>getDB - Database Retrieval Utility</title>
		<link rel="stylesheet" href="style/commonStyle.css"/>
		<link rel="stylesheet" href="style/inputStyle.css"/>
	</head>
	<body>
		<form id="Retrieve" action=" " method="post">
		<p>Hello World!</p>
		<div id="statusMessage"><c:out value="${status}"/></div>
		<div>
			<select id="tableSelect" name="tableSelections"  multiple="multiple">
				<c:forEach items="${loadedTableNames}" var="option">
					<option value="<c:out value="${option}"/>"><c:out value="${option}"/></option>
				</c:forEach>
			</select>
		</div>
		<div>
			<input id="submit" type="submit" value="Retrieve Tables" name="submit"/>
		</div>
		</form>
	</body>
</html>