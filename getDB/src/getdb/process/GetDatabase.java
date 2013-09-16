package getdb.process;

import getdb.servlet.TransactionProperties;
import getdb.util.DatabaseAccess;
import getdb.util.GDBEnumLib.DatabaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GetDatabase {
	private String dbConnectionString;
	private List<String> tablesToRetrieve;
	private String sqlStatementInput;
	private TransactionProperties properties;
	private DatabaseType dbType;

	public Map<String, ResultObject> retrieveData(
			TransactionProperties transactionProperties) {
		Connection con = null;
		ResultObject currentTableData = null;
		Map<String, ResultObject> resultTable = null;
		List<String> currentColumnNames = null;

		unloadProperties(transactionProperties);

		try {
			Class.forName(this.dbType.getDriverClass());
			System.out.println(dbType.toString() + " driver loaded!");
		} catch (Exception e) {
			System.out.println("Failed to load " + dbType.toString()
					+ " driver!");
			e.printStackTrace();
		}

		try {
			resultTable = new Hashtable<String, ResultObject>();

			con = DatabaseAccess.getConnection(dbConnectionString,
					properties.getUserID(), properties.getUserPass());

			for (String table : tablesToRetrieve) {
				currentTableData = new ResultObject();
				currentColumnNames = new ArrayList<String>();
				currentTableData.setTableName(table);

				currentTableData.setDbRows(DatabaseAccess.selectAllFromTable(
						con, table, currentColumnNames));
				currentTableData.setDbColumnNames(currentColumnNames);

				resultTable.put(currentTableData.getTableName(),
						currentTableData);
			}
		} catch (Exception e) {

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultTable;
	}

	private void unloadProperties(TransactionProperties properties) {
		this.properties = properties;
		dbType = properties.getDbType();
		tablesToRetrieve = properties.getTablesToRetrieve();
		sqlStatementInput = properties.getSqlStatementInput();
		dbConnectionString = properties.getDbConnectionString()
				+ ";databaseName=" + properties.getDbName();
	}

	@Deprecated
	private Connection getConnection() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(dbConnectionString,
					properties.getUserID(), properties.getUserPass());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}
