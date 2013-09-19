package getdb.servlet;

import getdb.util.DatabaseAccess;
import getdb.util.GDBEnumLib.*;

import java.sql.Connection;
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
	private List<String> tablesInDatabase;

	public void loadProperties() {
		initDefault();
	}

	private void initDefault() {
		tablesToRetrieve = new ArrayList<String>();
		dbType = DatabaseType.MSSQL;
		dbConnectionString = "jdbc:sqlserver://sampledb2008.czvcx7o6sksn.us-west-2.rds.amazonaws.com:1433";
		dbName = "AdventureWorks";
		userID = "getDB";
		userPass = "getDB";
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
	
	public void setTablesToRetrieve(String[] tableSelections) {
		for(int i = 0; i < tableSelections.length; i++){
			tablesToRetrieve.add(tableSelections[i]);			
		}
	}
	
	public void addTableToRetrieve(String tableName){
		tablesToRetrieve.add(tableName);
	}
	
	public void flushTablesToRetrieve(){
		tablesToRetrieve.clear();
	}

	public List<String> getTablesInDatabase() {
		return tablesInDatabase;
	}

	public void populateTablesInDatabase() throws Exception {		
		Connection con = null;
		
		try {
			Class.forName(this.dbType.getDriverClass());
			System.out.println(dbType.toString() + " driver loaded!");
		} catch (Exception e) {
			System.out.println("Failed to load " + dbType.toString()
					+ " driver!");
			e.printStackTrace();
		}


		try {
			con = DatabaseAccess.getConnection(
					getDbConnectionString(), getUserID(), getUserPass());
			
			this.tablesInDatabase = DatabaseAccess.selectTableNamesFromDB(con, getDbName());
		} catch (Exception e) {
			throw new Exception("Failed to retrieve tables from "
					+ getDbName() + " on server "
					+ getDbConnectionString(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw new Exception("Failed to close connection while populating table list!", e);
			}
		}
	}

	public String getSqlStatementInput() {
		return sqlStatementInput;
	}

}
