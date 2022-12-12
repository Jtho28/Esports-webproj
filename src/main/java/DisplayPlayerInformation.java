

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		String sqlTeamID;
		String sqlOrgID;
		String sqlLeagueID;
		String team = "";
		String org = "";
		String league = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
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
        
        sqlTeamID = "SELECT * FROM team WHERE manager_name=?";
        sqlOrgID = "SELECT org_id FROM team WHERE manager_name=?";
        sqlLeagueID = "SELECT league_id FROM team WHERE manager_name=?";
    
        
        try {
        	String player = keyword;
        	System.out.println(player);
        	statement1 = connection.prepareStatement(sqlTeamID);
        	statement1.setString(1, player);
        	statement2 = connection.prepareStatement(sqlOrgID);
        	statement2.setString(1, player);
        	statement3 = connection.prepareStatement(sqlLeagueID);
        	statement3.setString(1, player);
        	
        } catch (SQLException e2) {
        	
        	e2.printStackTrace();
        	
        }
        
        try {
        	
        	rs = statement1.executeQuery();
        	rs2 = statement2.executeQuery();
        	rs3 = statement3.executeQuery();
        	System.out.println(rs);
        	
        } catch (SQLException e3) {
        	
        	e3.printStackTrace();
        	
        }
        
        try {
        	while (rs.next()) {
        		team = rs.getString("team_id");
        		org = rs.getString("org_id");
        		league = rs.getString("league_id");
        	}
        } catch (SQLException e3) {
        	e3.printStackTrace();
        }
        
        out.println("<ul>" + 
        		"<li><a class =\"active\" href=\"Home2.html\">Home</a></li>" +
        		"<li><a href=\"PlayerSearch2.html\">Player Search</a></li>" +
        		"<li><a href=\"#\">Leagues</a></li>" + 
        		"<li><a href=\"#\">Organizations</a></li>" + 
        		"<li><a href=\"#\">Teams</a></li>" +
        		"</ul>" + 
        		"</nav>" + 
        		"<div class=\"popup\" id=\"popup\" style = \"position:absolute; left: 600px; top: 190px\" visibility=\"visible\">" +
        		"</div>" +
        		"<form action=\"DisplayPlayerInformation\" method=\"post\">" +
        		"<div class=\"form\" id=\"form\">\n"
        		+ "        <td>\n"
        		+ "            <p style = \"position:absolute; left: 700px; top: 210px; padding-bottom: 15px;\">Enter Player Name</p><br>\n"
        		+ "        </td>\n"
        		+ "        <tr>\n"
        		+ "            <td>\n"
        		+ "                <p style = \"position:absolute; left: 620px; top: 260px; padding-bottom: 15px;\">Player Name:</p>\n"
        		+ "            </td>\n"
        		+ "                <td>\n"
        		+ "                    <input style = \"position:absolute; left: 790px; top: 263px;\" type=\"text\" name=\"name\" id=\"playerName\"><br>\n"
        		+ "                </td>\n"
        		+ "        </tr>\n"
        		+ "        <tr>\n"
        		+ "            <td>\n"
        		+ "                <p style=\"position:absolute; left: 620px; top: 310px; padding-bottom: 15px;\">Player Team ID: " + team + "</p>\n"
        		+ "            </td>\n"
        		+ "        </tr>\n"
        		+ "        <tr>\n"
        		+ "            <td style=>\n"
        		+ "                <p style=\"position:absolute; left: 620px; top: 360px; padding-bottom: 15px;\">Player Org ID: " + org + "</p>\n"
        		+ "            </td>\n"
        		+ "        </tr>\n"
        		+ "        <tr>\n"
        		+ "            <td>\n"
        		+ "                <p style=\"position:absolute; left: 620px; top: 410px; padding-bottom: 15px\">Player League ID: " + league + "</p>\n"
        		+ "            </td>\n"
        		+ "        </tr>\n"
        		+ "        <button type=\"submit\" class=\"confirm\">Enter</button>\n"
        		+ "    </div>\n"
        		+ "</form>\n"
        		+ "\n"
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
