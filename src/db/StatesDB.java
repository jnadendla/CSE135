package db;
import java.sql.*;


public class StatesDB {
   private DbConnection db;
   
   public StatesDB() {
      db = new DbConnection();
      db.aquireConnection();
   }
   
   public ResultSet getStates() throws SQLException {
      // Create the statement
      Statement statement = db.connection.createStatement();

      // Use the created statement to SELECT
      // the student attributes FROM the States table.
      ResultSet rs = statement.executeQuery("SELECT * FROM states");
      
      return rs;
  }
   
   public int getStateName(int id) {
      int state = 0;
      try {
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
