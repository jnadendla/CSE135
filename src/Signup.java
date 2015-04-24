import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet{
   
   UserDB udb = new UserDB();
   RolesDB rdb = new RolesDB();
   StatesDB sdb = new StatesDB();
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User fresh;
      String name = request.getParameter("fname");
      
      String strAge = request.getParameter("age");
      if(strAge == null || strAge == "") {
         response.sendRedirect("SignupFailure.jsp");
         return;
      }
      
      int age = Integer.parseInt(strAge);
      
      String temp_role = request.getParameter("role");
      int role = rdb.getRoleID(temp_role);
      String temp_state = request.getParameter("state");
      int state = sdb.getStateID(temp_state);
      
      if(name == null || name == "") {
         response.sendRedirect("SignupFailure.jsp");
         return;
      }
      
      fresh = new User(name, age, role, state);
      
      boolean success = udb.addUser(fresh);
      if(success) {
         response.sendRedirect("SignupSuccess.jsp");
      }
      else {
         response.sendRedirect("SignupFailure.jsp");
      }
      doPost(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }
}
