package db;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDB {
   private DbConnection db;
   
   public UserDB() {
      db = new DbConnection();
   }
   
   public boolean addUser(User account) {   
      db.aquireConnection();
      try {
         if(getUser(account.name) != null) {
            return false;
         }
         
         PreparedStatement ps = db.connection.prepareStatement("INSERT INTO users "
               + "(name, age, role, state) VALUES (?,?,?,?)");
         ps.setString(1, account.name);
         ps.setInt(2, account.age);
         ps.setInt(3, account.role);
         ps.setInt(4, account.state);
         ps.execute();
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error adding user");
         e.printStackTrace();
         return false;
      }
      
      return true;
   }
   
   public User getUser(int id) {
      User ret = null;
      try {
         db.aquireConnection();
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM users WHERE id = ?");
         ps.setInt(1, id);
         ResultSet result = ps.executeQuery();
         if(result.next()) {
            ret = new User(result.getString(2), result.getInt(3),
                           result.getInt(4), result.getInt(5));
         }
         
         db.connection.commit();
         db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading user");
         e.printStackTrace();
      }      
      
      return ret;
   }
   
   public User getUser(String name) {
      User ret = null;
      try {
         db.aquireConnection();
         PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM users WHERE name = ?");
         ps.setString(1, name);
         ResultSet result = ps.executeQuery();
         //if(result.next()) {
         result.next();
            ret = new User(result.getString(2), result.getInt(3),
                           result.getInt(4), result.getInt(5));
         //}
         
         db.connection.commit();
         //db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading user");
         e.printStackTrace();
      }     
      
      return ret;
   }
   
}
