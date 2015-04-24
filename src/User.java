
public class User {
   public String name;
   public int age;
   public int role;
   public int state;
   
   public User(String name, int age, int role, int state) {
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
   
   public int getRole() {
      return role;
   }
   
   public int getState() {
      return state;
   }
}
