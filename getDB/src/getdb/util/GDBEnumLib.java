package getdb.util;

public class GDBEnumLib {
	public enum DatabaseType{
		MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver"), 
		DB2("com.ibm.as400.access.AS400JDBCDriver");
		
		private final String driverClass;
		
		DatabaseType(String driverClass){
			this.driverClass = driverClass;
		}
		
		public static DatabaseType evaluateDBType(String dbType){			
			return DatabaseType.valueOf(dbType.toUpperCase().trim());
		}
		
		public String getDriverClass(){
			return driverClass;
		}
	}

}
