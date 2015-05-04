

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

public class ProductOrder extends HttpServlet {
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
       
       //Get all the info about this purchase occurring
       int user = udb.getUserId((String)session.getAttribute("userName"));
       Integer id = (Integer) session.getAttribute("productId");
       int product = (int)id;
       String productName = (String) session.getAttribute("pname");
       String sku = (String) session.getAttribute("sku");
       int quantity = Integer.parseInt(request.getParameter("quantity"));
       String priceStr = (String) session.getAttribute("price");
       String category = (String) session.getAttribute("category");
       double price = Double.parseDouble(priceStr);
       double total = price * (double)quantity;
       
       Purchase p = new Purchase(user, product, productName, sku, category, quantity, total);

       //LinkedList is actually the shopping cart, so cart is only session based because
       //we store the cart in the session
       LinkedList<Purchase> purchases = (LinkedList)session.getAttribute("purchases");
       if(purchases == null)
          purchases = new LinkedList<Purchase>();

       purchases.add(p);
       session.setAttribute("purchases", purchases); //override current purchases object in session

       ClearedRequest creq = new ClearedRequest(request);

       request.getRequestDispatcher("/ProductBrowser.jsp").forward(creq,
             response);
    }
}
