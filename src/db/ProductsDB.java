package db;
import java.sql.*;
import java.util.LinkedList;

public class PurchaseDB {
   private DbConnection db;
   
   public PurchaseDB() {
      db = new DbConnection();
      db.aquireConnection();
   }
   
   public void insert(Purchase item) throws SQLException {
      // Begin transaction
      db.connection.setAutoCommit(false);
      
      // Create the prepared statement and use it to
      // INSERT student values INTO the students table.
      PreparedStatement pstmt = db.connection
              .prepareStatement("INSERT INTO purchased (usr, product, quantity, price) VALUES (?, ?, ?, ?)");

      pstmt.setInt(1, item.getUserId());
      pstmt.setInt(2, item.getProductId());
      pstmt.setInt(3, item.getQuantity());
      pstmt.setDouble(4, item.getPrice());
      
      int rowCount = pstmt.executeUpdate();

      // Commit transaction
      db.connection.commit();
      db.connection.setAutoCommit(true);
   }
   
   public LinkedList<Purchase> getPurchases() throws SQLException {
      // Create the statement
      Statement statement = db.connection.createStatement();
      LinkedList<Purchase> purchasedList = new LinkedList<Purchase>();

      // Use the created statement to SELECT
      // the purchases attributes FROM the Purchase table.
      ResultSet rs = statement.executeQuery("SELECT * FROM purchased");
      
      while(rs.next()) {
         ProductsDB pdb = new ProductsDB();
         CategoriesDB cdb = new CategoriesDB();
         
         int user = rs.getInt("usr");
         int quantity = rs.getInt("quantity");
         double price = rs.getDouble("price");
         
         int pid = rs.getInt("product");
         ResultSet prod_set = pdb.getProduct(pid);
         if(prod_set.next()) {
            String name = prod_set.getString("name");
            String sku = prod_set.getString("sku");
            int categoryId = prod_set.getInt("category");
            System.out.print(categoryId);
            ResultSet cat_set = cdb.getCategory(categoryId);
            if(cat_set.next()) {
               String category = cat_set.getString("name");
               Purchase p = new Purchase(user, pid, name, sku, category, quantity, price);
               purchasedList.add(p);
            }
         }
      }

      return purchasedList;
   }

}
