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
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("Made it here");
      
      String name = request.getParameter("fname");
      
      String strAge = request.getParameter("age");
      int age = Integer.parseInt(strAge);
      
      String role = request.getParameter("role");
      String state = request.getParameter("state");
      
      User u = new User(name, age, role, state);
      
      udb.addUser(u); // add user  
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }
}
