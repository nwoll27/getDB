package getdb.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
	
	public static List<List<String>> selectAllFromTable(Connection con, String tableName, List<String> columnNames){
		String dataQuery;
		ResultSet rs;
		ResultSetMetaData rsmd;
		List<List<String>> dbRows;
		int numColumns;
		
		dataQuery = "SELECT * FROM " + tableName;
		dbRows = new ArrayList<List<String>>();		
		try{			
			rs = con.createStatement().executeQuery(dataQuery);
			rsmd = rs.getMetaData();
			numColumns = rsmd.getColumnCount();
			
			for(int i = 1; i <= numColumns; i++){
				columnNames.add(rsmd.getColumnName(i));
			}
			
			while(rs.next()){
				List<String> items = new ArrayList<String>();
				for(int i = 1; i<= numColumns; i++) {
					Object recordObject = rs.getObject(i);
					items.add(recordObject == null ? null : recordObject.toString());
				}
				dbRows.add(items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dbRows;
	}

}
