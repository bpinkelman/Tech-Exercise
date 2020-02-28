
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AssignmentInsert")
public class AssignmentInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AssignmentInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String Name = request.getParameter("Name");
      String DueDate = request.getParameter("DueDate");
      String completion = request.getParameter("completion");
      String Class = request.getParameter("Class");
      

      Connection connection = null;
      String insertSql = " INSERT INTO Assignments (id, Name, DueDate, completion, Class ) values (default, ?, ?, ?, ?)";
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, Name);
         preparedStmt.setString(2, DueDate);
         preparedStmt.setString(3, completion);
         preparedStmt.setString(4, Class);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name</b>: " + Name + "\n" + //
            "  <li><b>DueDate</b>: " + DueDate + "\n" + //
            "  <li><b>completion</b>: " + completion + "\n" + //
            "  <li><b>Class #</b>: " + Class + "\n" + //
            

            "</ul>\n");

      out.println("<a href=/planner-pinkelman/simpleFormSearch.html>Search Classes</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}