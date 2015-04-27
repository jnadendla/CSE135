import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SeekableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import db.User;
import db.UserDB;

public class Login extends HttpServlet{
   
   HttpSession session;
   UserDB udb = new UserDB();
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      session = request.getSession();
      
      User found;
      String name = request.getParameter("fname");

      found = udb.getUser(name);
      
      if(found != null) {
         session.setAttribute("userName", found.name);
         session.setAttribute("userAge", found.age);
         session.setAttribute("userRole", found.role);
         session.setAttribute("userState", found.state);
         response.sendRedirect("Home.jsp");
      }
      else{
         PrintWriter pw = response.getWriter();
         pw.println("<html>");
         pw.println("<head>");
         pw.println("<title>Failure</title>");
         pw.println("</head>");
         pw.println("<body>");
         pw.println("<p style=\"color:red\">");
         pw.println("<font size=\"4\">The provided name </font>");
         pw.println("<font size=\"4\">" + name +"</font>");
         pw.println("<font size=\"4\"> is not known</font>");
         pw.println("</p>");
         pw.println("</body>");
         pw.println("</html>");
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }
}
