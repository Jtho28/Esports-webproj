

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowOrgs
 */
@WebServlet("/ShowOrgs")
public class ShowOrgs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String dns = "ec2-18-205-149-151.compute-1.amazonaws.com";
    String url = "jdbc:mysql://" + dns + ":3306/esports";
    String user = "jacksonmyers";
    String password = "EGStickers87_";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrgs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql;
		
		String name = "";
		String id = "";
		String owner = "";
		Connection connection = null;
		PreparedStatement statement1 = null;
		ResultSet rs = null;
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Leagues";
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
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");
        
        sql = "SELECT * FROM organization";
        System.out.println(sql);
        
        try {
        	statement1 = connection.prepareStatement(sql);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        try {
        	
        	rs = statement1.executeQuery();
        	System.out.println(rs);
        	
        } catch (SQLException e3) {
        	
        	e3.printStackTrace();
        	
        }
        
        out.println("<ul>\n"
        		+ "        <li><a class =\"active\" href=\"Home2.html\">Home</a></li>\n"
        		+ "        <li><a href=\"PlayerSearch2.html\">Player Search</a></li>\n"
        		+ "        <li><a href=\"/webpro/ShowLeagues\">Leagues</a></li>\n"
        		+ "        <li><a href=\"/webpro/ShowOrgs\">Organizations</a></li>\n"
        		+ "        <li><a href=\"/webpro/ShowTeams\">Teams</a></li>\n"
        		+ "    </ul>\n"
        		+ "</nav>\n"
        		+ "\n"
        		+ "<table border=1 width=50% height=30%>"
        		+ "<tr><th>Organization Name</th><th>Org ID#</th><th>Owner</th>");
        
        try {
        	while (rs.next()) {
        		name = rs.getString("org_name");
        		id = rs.getString("org_id");
        		owner = rs.getString("owner_name");
        		
        		out.println("<tr><td>" + name + "</td><td>" + id + "</td><td>" + owner + "</td></tr>");
        	}
        } catch (SQLException e3) {
        	e3.printStackTrace();
        }
        
        System.out.println(name);
        
        out.println("\n"
        		+ "  \n"
        		+ "</body>\n"
        		+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
