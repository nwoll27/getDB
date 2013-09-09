package getdb.process;

import getdb.servlet.TransactionProperties;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GetDatabase {
	List<String> tablesToRetrieve;
	Map<String, ResultObject> resultTable;
	
	public Map<String, ResultObject> retrieveData(TransactionProperties properties){
		
		resultTable = new Hashtable<String, ResultObject>();
		ResultObject currentTableData = null;
		
		for(String table : tablesToRetrieve){
			currentTableData = new ResultObject();
			currentTableData.setTableName(table);
			
			//Collect data from DatabaseAccess methods
			
			resultTable.put(currentTableData.getTableName(), currentTableData);
		}		
		
		return resultTable;
	}

}
