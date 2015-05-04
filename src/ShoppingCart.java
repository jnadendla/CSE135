

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
import db.PurchaseDB;
import db.UserDB;

public class ShoppingCart extends HttpServlet {
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
       PurchaseDB purchasedb = new PurchaseDB();
       
       //Get card information, and only proceed if card has been entered
       String cardStr = request.getParameter("card");
       if(cardStr == null || cardStr == "")
          return;
       
       int card = Integer.parseInt(cardStr);
       
       //Get items from cart and place them in the DataBase
       //LinkedList is actually the shopping cart.
       LinkedList<Purchase> cart = (LinkedList)session.getAttribute("purchases");
       if(cart == null) {
          //bail out if no cart was ever built
          cart = new LinkedList<Purchase>();
          session.setAttribute("purchases", cart);
          return;
       }
       
       for(int i=0; i < cart.size(); ++i) {
          Purchase p = cart.get(i);
          try {
            purchasedb.insert(p);
            cart.remove(i);
            --i;
         } catch (SQLException e) {
            //error inserting into shopping cart
            e.printStackTrace();
         }
       }

       session.setAttribute("purchases", cart); //override current purchases object in session

       response.sendRedirect("PurchaseComplete.jsp");
    }
}
