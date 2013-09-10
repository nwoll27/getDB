package getdb.process;

import getdb.servlet.TransactionProperties;
import getdb.util.GDBEnumLib.DatabaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GetDatabase {
	private List<String> tablesToRetrieve;
	private String sqlStatementInput;
	private TransactionProperties properties;
	private DatabaseType dbType;	
	
	public Map<String, ResultObject> retrieveData(TransactionProperties transactionProperties){
		Connection con= null;		
		ResultObject currentTableData = null;
		Map<String, ResultObject> resultTable = null;
		
		unloadProperties(transactionProperties);
		
		try{
			Class.forName(this.dbType.getDriverClass());
			System.out.println(dbType.toString() + " driver loaded!");
		} catch (Exception e){
			System.out.println("Failed to load " + dbType.toString() + "driver!");
			e.printStackTrace();
		}
		
		try{
			resultTable = new Hashtable<String, ResultObject>();
			con = getConnection();
			
			for(String table : tablesToRetrieve){
				currentTableData = new ResultObject();
				currentTableData.setTableName(table);
				
				//Collect data from DatabaseAccess methods
				
				resultTable.put(currentTableData.getTableName(), currentTableData);
			}
		} catch (Exception e) {
			
		}		
		
		return resultTable;
	}
	
	private void unloadProperties(TransactionProperties properties){
		this.properties = properties;
		dbType = properties.getDbType();
		tablesToRetrieve = properties.getTablesToRetrieve();
		sqlStatementInput = properties.getSqlStatementInput();
	}
	
	private Connection getConnection(){
		Connection con = null;
		
		try{
			con = DriverManager.getConnection(properties.getDbConnectionString(), properties.getUserID(), properties.getUserPass());			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
}
