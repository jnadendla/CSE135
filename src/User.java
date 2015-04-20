
public class User {
   public String name;
   public int age;
   public String role;
   public String state;
   
   public User(String name, int age, String role, String state) {
      this.name = name;
      this.age = age;
      this.role = role;
      this.state = state;
   }
      
   public String getName() {
      return name;
   }
   
   public int getAge() {
      return age;
   }
   
   public String getRole() {
      return role;
   }
   
   public String getState() {
      return state;
   }
}
