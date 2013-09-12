package getdb.process;

import java.util.ArrayList;
import java.util.List;

public class ResultObject {
	private boolean processSucceeded;
	
	private String tableName;
	private String transaction;
	private int dbRecordCount;
	private int dbColumnCount;
	private String statusMessage;
	
	private List<String> dbColumnNames;
	private List<List<String>> dbRows;

	public ResultObject(){
		this.dbColumnNames = new ArrayList<String>();
		this.dbRows = new ArrayList<List<String>>();
		this.dbRecordCount = 0;
	}

	
	public boolean isProcessSucceeded() {
		return processSucceeded;
	}

	public void setProcessSucceeded(boolean processSucceeded) {
		this.processSucceeded = processSucceeded;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	
	public int getDbRecordCount() {
		return dbRecordCount;
	}

	public int getDbColumnCount() {
		return dbColumnCount;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<String> getDbColumnNames() {
		return dbColumnNames;
	}

	public void setDbColumnNames(List<String> dbColumnNames) {
		this.dbColumnNames = dbColumnNames;
		this.dbColumnCount = dbColumnNames.size();
	}

	public List<List<String>> getDbRows() {
		return dbRows;
	}

	public void setDbRows(List<List<String>> dbRows) {
		this.dbRows = dbRows;
		this.dbRecordCount = dbRows.size();
	}
	
	public String toString(){
		return getTransaction() + " - " + (isProcessSucceeded()?"SUCCEEDED!":"Not executed.");
	}
	
}
