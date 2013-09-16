package getdb.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getdb.servlet.TransactionProperties;
import getdb.process.GetDatabase;
import getdb.process.ResultObject;

public class GDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TransactionProperties properties = null;
	String statusMessage;

	/**
	 * Constructor of the servlet.
	 */
	public GDBServlet() {
		super();
	}

	/**
	 * Destruction of the servlet.
	 */
	public void destroy() {

		super.destroy();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("loadedTableNames", properties.getTablesInDatabase());
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request sent by the client to the server
	 * @param response
	 *            the response sent by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRemoteAddr());
		if(request.getParameterValues("tableSelections") == null){
			System.out.println("No tables selected!");
			statusMessage = "*No tables selected! Please select one or more tables to retrieve.";
			request.setAttribute("loadedTableNames", properties.getTablesInDatabase());
			request.setAttribute("status", statusMessage);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			properties.setTablesToRetrieve(request.getParameterValues("tableSelections"));
			Map<String, ResultObject> data = new Hashtable<String, ResultObject>();
			GetDatabase getDB = new GetDatabase();
			
			data = getDB.retrieveData(properties);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("jsp/getDB_output.jsp");

			try {
				request.setAttribute("update", data);
				dispatch.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Transaction successfully completed!");
				properties.loadProperties();
			}
		}		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		statusMessage = null;
		properties = new TransactionProperties();
		properties.loadProperties();
		
		try{
			properties.populateTablesInDatabase();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
