package db;
import java.sql.*;

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

}
