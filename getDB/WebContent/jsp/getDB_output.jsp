<!DOCTYPE html>
<%@page import="java.util.Map" %>
<%@page import="java.util.Hashtable"%>
<%@page import="getdb.process.ResultObject"%>
<!-- @author Nick Woll <nwoll27> -->
<%
@SuppressWarnings("unchecked")
Map<String, ResultObject> resultMap = (Hashtable<String, ResultObject>)request.getAttribute("update");
StringBuffer resultHTML = new StringBuffer();
ResultObject currentResult;


for(String table : resultMap.keySet()){
	currentResult = resultMap.get(table);
	
	resultHTML.append("<div><table>");
	
	resultHTML.append("<tr>");
	for(String columnName : currentResult.getDbColumnNames()){
		resultHTML.append("<th>" + columnName + "</th>");
	}
	resultHTML.append("</tr>");	
	
	for(int i=0; i < currentResult.getDbRecordCount(); i++){
		resultHTML.append("<tr>");
		for(String field : currentResult.getDbRows().get(i)){
			resultHTML.append("<td>" + field + "</td>");
		}
		resultHTML.append("</tr>");
	}
	
	resultHTML.append("</table></div>");
}

%>
<html lang="en">
	<head>
		<meta charset="utf-8" />		
		<title>getDB - Results!</title>
	    <link rel="stylesheet" href="style/commonStyle.css"/>
		<link rel="stylesheet" href="style/outputStyle.css"/>
	</head>
	<body>
		<p>THIS IS OUTPUT WEEEEEE!</p>
		<%=resultHTML %>
	</body>
</html>