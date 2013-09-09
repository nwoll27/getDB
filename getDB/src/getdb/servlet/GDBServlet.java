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
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		//Use this for changing the calling page URL:
		//RequestDispatcher dispatch = request.getRequestDispatcher("DTU.jsp" + queryString);
		
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *				the request sent by the client to the server
	 * @param response
	 *				the response sent by the server to the client
	 * @throws ServletException
	 *				if an error occurred 
	 * @throws IOException 
	 *				if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, ResultObject> data = new Hashtable<String, ResultObject>();
		GetDatabase getDB = new GetDatabase();
		
		
		data = getDB.retrieveData(properties);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/getDB_output.jsp");
		
		try {
			request.setAttribute("update", data);
			dispatch.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		/*
		 * Set up logfile
		 * Initialize properties(LoadProperties.getInstance(getServletContext().getInitParameter("ConfigPath"));
		 * ALTERNATELY LoadProperties.getProperties();
		 */
		properties = new TransactionProperties();
		properties.loadProperties();
	}
	
}
