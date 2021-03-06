<!DOCTYPE html>
<!-- @author Nick Woll <nwoll27> -->

<%@page import="java.util.Map"%>
<%@page import="java.util.Hashtable"%>
<%@page import="getdb.process.ResultObject"%>

<%
@SuppressWarnings("unchecked")
Map<String, ResultObject> resultMap = (Hashtable<String, ResultObject>)request.getAttribute("update");
StringBuffer resultHTML = new StringBuffer();
ResultObject currentResult;


for(String table : resultMap.keySet()){
	currentResult = resultMap.get(table);
	
	resultHTML.append("<h3>"+table+"</h3>\n<section>\n<table>\n");
	
	resultHTML.append("<tr>");
	for(String columnName : currentResult.getDbColumnNames()){
		resultHTML.append("<th>" + columnName + "</th>");
	}
	resultHTML.append("</tr>\n");	
	
	for(int i=0; i < currentResult.getDbRecordCount(); i++){
		resultHTML.append("\n<tr>");
		for(String field : currentResult.getDbRows().get(i)){
			resultHTML.append("<td>" + field + "</td>");
		}
		resultHTML.append("</tr>");
	}
	
	resultHTML.append("</table>\n</section>\n\n");
}

%>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>getDB - Results!</title>
		<link rel="stylesheet" href="style/commonStyle.css" />
		<link rel="stylesheet" href="style/outputStyle.css" />
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/redmond/jquery-ui.css"/>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			$(function() {
				$( "#summary" ).accordion({ collapsible:false, heightStyle:"content" });
				
				$( "#results" ).accordion({ collapsible:true, active:false, heightStyle:"content" });		
			});
		</script>
	</head>
	<body>
		<header>
			<h1>getDB!</h1>
			<p class="subHead">Query Results</p>
		</header>
		<div id="bodyContainer">		
			<div class="sectionSpacer"></div>
			<article id="resultsArticle">
				<div id="summary">
					<h3>Table Summary</h3>
					<section>
						<div>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Donec hendrerit felis accumsan turpis pretium tempor. Duis eu
								turpis nunc, ut euismod nisl. Aliquam erat volutpat. Proin eu
								eros mollis dui fringilla sodales. Curabitur venenatis tincidunt
								felis ac congue.</p>
						</div>
					</section>
				</div>
				<div class="sectionSpacer"></div>
				<div id="results">
					<%=resultHTML %>
				</div>
			</article>
			
			<FORM>
			<INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
			<div class="sectionSpacer"></div>	
		</div>
		<footer>
			<div id="footerContent">
				<p>This application is hosted on Apache Tomcat 7. The
					AdventureWorks database used by this application is hosted on
					Amazon Web Services' RDS, running an instance of MS SQL Server
					2008 Express.</p>
			</div>
		</footer>
	
	</body>
</html>