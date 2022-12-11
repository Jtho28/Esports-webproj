

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayPlayerInformation
 */
@WebServlet("/DisplayPlayerInformation")
public class DisplayPlayerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String dns = "ec2-18-205-149-151.compute-1.amazonaws.com";
    String url = "jdbc:mysql://" + dns + ":3306/esports";
    String user = "jacksonmyers";
    String password = "EGStickers87_";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPlayerInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("name");
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Fetch Playerlayer Details";
        String docType = "<!DOCTYPE html>";
        
        out.println(docType + 
    			"<html>\n" +
        		"<head>" +
    			"<meta charset=\"ISO-8859-1\">" +
        		"<title>" + title + "</title>" +
    			"<link rel=\"stylesheet\" type = \"text/css\" href=\"PlayerSearch.css\" screen=\"media\">" +
        		"</head>" +
    			"<body>" +
        		"<nav>" +
    			"<label class=\"logo\">eSports Website</label>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
