package db;
import java.sql.*;


public class RolesDB {
   private DbConnection db;
   
   public RolesDB() {
      db = new DbConnection();
      db.aquireConnection();
   }
   
   public ResultSet getRoles() throws SQLException {
      // Create the statement
      Statement statement = db.connection.createStatement();

      // Use the created statement to SELECT
      // the student attributes FROM the Student table.
      ResultSet rs = statement.executeQuery("SELECT * FROM roles");
      
      return rs;
  }
   
   public int getRoleName(int id) {
      int role = 0;
      try {
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
         ps.setInt(1, id);
         ResultSet result = ps.executeQuery();
         result.next();
         role = result.getInt(2);
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading role");
         e.printStackTrace();
      }      
      
      return role;
   }
   
   public int getRoleID(String name) {
      int role = 0;
      try {
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM roles WHERE role = ?");
         ps.setString(1, name);
         ResultSet result = ps.executeQuery();
         result.next();
         role = result.getInt(1);
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading role");
         e.printStackTrace();
      }      
      
      return role;
   }
   
}
