import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet{
   
   UserDB udb = new UserDB();
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User fresh;
      String name = request.getParameter("fname");
      
      String strAge = request.getParameter("age");
      if(strAge == null || strAge == "") {
         doPost(request, response);
         return;
      }
      
      int age = Integer.parseInt(strAge);
      
      String role = request.getParameter("role");
      String state = request.getParameter("state");
      
      if(name == null || role == null || state == null ||
         name == "" || role == "" || state == "") {
         doPost(request, response);
         return;
      }
      
      fresh = new User(name, age, role, state);
      
      boolean success = udb.addUser(fresh);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendRedirect("ErrorSignup.jsp");
   }
}
