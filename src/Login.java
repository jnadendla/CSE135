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

import db.ClearedRequest;
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

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
         return;
      }
      else{
         request.setAttribute("failure", "Unknown user, please try again");
      }
      
      ClearedRequest creq = new ClearedRequest(request);
      request.getRequestDispatcher("/Login.jsp").forward(creq, response);
   }
}
