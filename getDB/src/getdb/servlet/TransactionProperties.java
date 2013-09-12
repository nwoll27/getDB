package getdb.servlet;

import getdb.util.GDBEnumLib.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionProperties {
	private DatabaseType dbType;
	private String dbConnectionString;
	private String dbName;
	private String userID;
	private String userPass;
	private List<String> tablesToRetrieve;
	private String sqlStatementInput;
	
	public void loadProperties(){
		initDefault();
	}
	
	private void initDefault(){
		tablesToRetrieve = new ArrayList<String>();
		dbType = DatabaseType.MSSQL;
		dbConnectionString = "jdbc:sqlserver://sampledb.czvcx7o6sksn.us-west-2.rds.amazonaws.com:1433";		
		dbName = "Client_Email_ListA";
		userID = "sa";
		userPass = "Password1";
		tablesToRetrieve.add("ClientEmailList_recipienttype");
		sqlStatementInput = null;
	}

	public DatabaseType getDbType() {
		return dbType;
	}

	public String getDbConnectionString() {
		return dbConnectionString;
	}

	public String getDbName() {
		return dbName;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserPass() {
		return userPass;
	}

	public List<String> getTablesToRetrieve() {
		return tablesToRetrieve;
	}

	public String getSqlStatementInput() {
		return sqlStatementInput;
	}
	
}
