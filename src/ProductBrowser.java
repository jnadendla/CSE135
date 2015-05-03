

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Category;
import db.ProductsDB;
import db.ClearedRequest;
import db.Purchase;
import db.UserDB;

public class ProductBrowser extends HttpServlet {
    ProductsDB pdb = new ProductsDB();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       UserDB udb = new UserDB();
       ProductsDB pdb = new ProductsDB();
       
       String action = request.getParameter("action");
       ClearedRequest creq = new ClearedRequest(request);

       if (action != null && action.equals("search")) {
           request.getRequestDispatcher("/ProductBrowser.jsp").forward(request,
                   response);
       } else {
          int user = udb.getUserId((String)session.getAttribute("userName"));
          int product = Integer.parseInt(request.getParameter("id"));
          String name = request.getParameter("pname");
          String sku = request.getParameter("sku");
          String price = request.getParameter("price");
          String category = request.getParameter("category");
          
          session.setAttribute("userId", user);
          session.setAttribute("productId", product);
          session.setAttribute("pname", name);
          session.setAttribute("sku", sku);
          session.setAttribute("price", price);
          session.setAttribute("category", category);
           
          response.sendRedirect("ProductOrder.jsp");
          }
    }
}
