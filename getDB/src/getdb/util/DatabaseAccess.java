package getdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

	public static Connection getConnection(String dbConnectionString,
			String userID, String password) {
		Connection con = null;

		try {
			con = DriverManager.getConnection(dbConnectionString, userID,
					password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static List<List<String>> selectAllFromTable(Connection con,
			String tableName, List<String> columnNames) {
		String dataQuery;
		ResultSet rs;
		ResultSetMetaData rsmd;
		List<List<String>> dbRows;
		int numColumns;

		dataQuery = "SELECT * FROM " + tableName;
		dbRows = new ArrayList<List<String>>();
		try {
			rs = con.createStatement().executeQuery(dataQuery);
			rsmd = rs.getMetaData();
			numColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numColumns; i++) {
				columnNames.add(rsmd.getColumnName(i));
			}

			while (rs.next()) {
				List<String> items = new ArrayList<String>();
				for (int i = 1; i <= numColumns; i++) {
					Object recordObject = rs.getObject(i);
					items.add(recordObject == null ? null : recordObject
							.toString());
				}
				dbRows.add(items);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dbRows;
	}

	public static List<String> selectTableNamesFromDB(Connection con,
			String dbName) {
		List<String> tableNames = new ArrayList<String>();
		String dataQuery;
		ResultSet rs;
		
		dataQuery = "USE " + dbName + " SELECT SCHEMA_NAME(schema_id)+'.'+name FROM sys.Tables";

		try {
			rs = con.createStatement().executeQuery(dataQuery);

			while (rs.next()) {
				tableNames.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tableNames;
	}
}
