import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ClearedRequest;
import db.RolesDB;
import db.StatesDB;
import db.User;
import db.UserDB;

public class Signup extends HttpServlet{
   
   UserDB udb = new UserDB();
   RolesDB rdb = new RolesDB();
   StatesDB sdb = new StatesDB();
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User fresh;
      PrintWriter pw = response.getWriter();
      
      String name = request.getParameter("fname");
      
      String strAge = request.getParameter("age");
      if(strAge == null || strAge == "") {
         printFailure(pw);
         return;
      }
      
      int age = Integer.parseInt(strAge);
      
      String temp_role = request.getParameter("role");
      int role = rdb.getRoleID(temp_role);
      String temp_state = request.getParameter("state");
      int state = sdb.getStateID(temp_state);
      
      if(name == null || name == "") {
         printFailure(pw);
         return;
      }
      
      fresh = new User(name, age, role, state);
      
      boolean success = udb.addUser(fresh);
      if(success) {
         printSuccess(pw);
      }
      else {
         printFailure(pw);
      }
   }
   
   private void printFailure(PrintWriter pw) {
      pw.println("<html>");
      pw.println("<head>");
      pw.println("<title>Failure</title>");
      pw.println("</head>");
      pw.println("<body>");
      pw.println("<p style=\"color:red\">");
      pw.println("<font size=\"4\">SIGN UP FAILURE: Insufficient User Information Was Input</font>");
      pw.println("</p>");
      pw.println("</body>");
      pw.println("</html>");
   }
   
   private void printSuccess(PrintWriter pw) {
      pw.println("<html>");
      pw.println("<head>");
      pw.println("<title>Success</title>");
      pw.println("</head>");
      pw.println("<body>");
      pw.println("<p style=\"color:blue\">");
      pw.println("<font size=\"4\">You have successfully signed up</font>");
      pw.println("</p>");
      pw.println("</body>");
      pw.println("</html>");
   }
}
