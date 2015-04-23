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
	  System.out.println("Adding user " + account);
	  
      db.aquireConnection();
      try {
      	 if(account == getUser(account.name))
            return false;
            
         PreparedStatement ps = db.connection.prepareStatement("INSERT INTO users "
               + "(name, age, role, state) VALUES (?,?,?,?)");
         ps.setString(1, account.name);
         ps.setInt(2, account.age);
         ps.setString(3, account.role);
         ps.setString(4, account.state);
         ps.execute();
         
         db.connection.commit();
         db.connection.close();
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
         java.sql.Statement s = db.connection.createStatement();
         ResultSet result = s.executeQuery("SELECT " + id + " FROM users");
         ret = new User(result.getString(1), result.getInt(2),
               result.getString(3), result.getString(4));
         
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
         java.sql.Statement s = db.connection.createStatement();
         ResultSet result = s.executeQuery("SELECT " + name + " FROM users");
         ret = new User(result.getString(1), result.getInt(2),
               result.getString(3), result.getString(4));
         
         db.connection.commit();
         db.connection.close();
      } catch (SQLException e) {
         System.out.println("Error reading user");
         e.printStackTrace();
      }
      
      
      return ret;
   }
   
}
