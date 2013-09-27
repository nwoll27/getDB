<!DOCTYPE html>
<!-- @author Nick Woll <nwoll27> -->

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
	<head>
		<meta charset="utf-8" />		
		<title>getDB - Database Retrieval Utility</title>
		<link rel="stylesheet" href="style/commonStyle.css"/>
		<link rel="stylesheet" href="style/inputStyle.css"/>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/redmond/jquery-ui.css"/>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			$(function() {
				$( "#tabs" ).tabs();
				
				$( "#tableRef" ).accordion({ collapsible:true, active:false, heightStyle:"content" });				
			});
		</script>
	</head>
	<body>
		<header>
			<h1>getDB!</h1>
			<p class="subHead">Data retrieval tool</p>
		</header>
		<div id="bodyContainer">
			<section id="instructionBar">
				<p>getDB! is a simple Java EE utility that utilizes .jsp and
					servlets to provide a UI for interacting with databases.</p>
				<p>The goal of the application is to illustrate basic
					proficiencies in HTML, web application design, Java, database
					administration, and SQL.</p>
				<p>To get started, please select one or more tables from the list
					and click "Retrieve." To enter your own query against the AdventureWorks database, 
					click the tab labeled "Custom Query" and enter SQL into the text box. 
					Click "CustomConnect" to enter your own database connection and query information.</p>					
				<p><b>NOTE:</b> Entering a custom query into the form will cause getDB
					to ignore any tables you have selected.</p>	
			</section>
			
			<section id="formBody">
				<form id="Retrieve" method="post">
					<div id="tabs">
						<ul>
							<li><a href="#tabs-1">Retrieve</a></li>
							<li><a href="#tabs-2">Custom Query</a></li>
							<li><a href="#tabs-3">CustomConnect</a></li>
						</ul>
						<div id="tabs-1">
							<h2>Select a Table</h2>
							<h4>(Ctrl+click to select more than one)</h4>
							<div id="statusMessage"><c:out value="${status}"/></div>
							<div>
								<select id="tableSelect" name="tableSelections"  multiple="multiple">
									<c:forEach items="${loadedTableNames}" var="option">
										<option value="<c:out value="${option}"/>"><c:out value="${option}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="sectionSpacer"></div>				
							<div>
								<input id="submit" type="submit" value="Retrieve Tables" name="submit"/>
							</div>
						</div>
						<div id="tabs-2">
							<h2>Enter SQL for AdventureWorks DB</h2>
							<h4>(This account's access is Read-Only)</h4>
							<div id="tableRefBox">
								<div id="tableRef">
									<h3>Click to Show/Hide a list of tables</h3>
									<section>
										<ul>
											<c:forEach items="${loadedTableNames}" var="option">
												<li><c:out value="${option}"/></li>
											</c:forEach>
										</ul>
									</section>
								</div>
							</div>
							<div class="sectionSpacer"></div>
							<textarea class="customQueryBox" name="customQuery" rows="8"></textarea>	
							<div class="sectionSpacer"></div>
							<div>
								<input id="submit" type="submit" value="Execute Statement" name="submit"/>
							</div>					
						</div>
						<div id="tabs-3">
							<h2>Enter Custom Connection data</h2>
							<div class="customConnectRow">
							<span>
								<label>Database Type
									<select disabled="disabled">
										<option value="MSSQL" selected="selected">MSSQL</option>									
									</select>
								</label>
								</span>
								<span>
								<label>Database Name
									<input id="textDBName" type="text" name="dbName"/>
								</label>
								</span>
							</div>
							<div class="customConnectRow">
								<label>Database URL
									<input id="textConnectionString" type="text" name="connectionString" value="jdbc:sqlserver://"/>
								</label>
							</div>							
							<div class="customConnectRow">
								<label>User ID
									<input id="textUserID" type="text" name="userID"/>
								</label>
								<label>Password
									<input id="textUserPass" type="text" name="userPass"/>
								</label>
							</div>
							<div class="sectionSpacer"></div>			
							<textarea class="customQueryBox" name="customConnectQuery" rows="8"></textarea>
							<div class="sectionSpacer"></div>
							<div>
								<input id="submit" type="submit" value="Execute Custom Query" name="submit"/>
							</div>
						</div>
					</div>
					
				</form>
			</section>
			<div class="sectionSpacer"></div>			
		</div>
		<footer>
				<div id="footerContent">
					<p>This application is hosted on Apache Tomcat 7. The
						AdventureWorks database used by this application is hosted on Amazon
						Web Services' RDS, running an instance of MS SQL Server 2008
						Express.</p>
				</div>
		</footer>
	</body>
</html>