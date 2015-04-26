import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StatesDB {
   private DbConnection db;
   
   public StatesDB() {
      db = new DbConnection();
   }
   
   public int getStateName(int id) {
      int state = 0;
      try {
         db.aquireConnection();
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
         ps.setInt(1, id);
         ResultSet result = ps.executeQuery();
         result.next();
         state = result.getInt(2);
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading role");
         e.printStackTrace();
      }      
      
      return state;
   }
   
   
   public int getStateID(String name) {
      int state = 0;
      try {
         db.aquireConnection();
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM states WHERE name = ?");
         ps.setString(1, name);
         ResultSet result = ps.executeQuery();
         result.next();
         state = result.getInt(1);
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading role");
         e.printStackTrace();
      }      
      
      return state;
   }
   
}
