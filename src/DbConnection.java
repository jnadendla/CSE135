import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
   
   String driver = "org.postgresql.Driver";

   String url = "jdbc:postgresql://localhost:5432/phoebe";
   String user = "phoebe";
   String password = "";

   /*String url = "jdbc:postgresql://localhost:5432/Accounts";
   String user = "postgres";
   String password = "cse135";*/

   
   Connection connection = null;
   
   public Connection aquireConnection() {
      if(connection != null)
         return connection;
      else {
         try {
            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, password);
         } catch (Exception e) {
            System.out.println("Error trying to aquire connection");
            e.printStackTrace();
         }
         return connection;
      }
   }

}
