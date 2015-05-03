package db;

public class Purchase {
   private int user;
   private int product;
   private String productName;
   private String sku;
   private String category;
   private int quantity;
   private double price;
   
   public Purchase(int user, int product, String productName, String sku, 
                   String category, int quantity, double price) {
      this.user = user;
      this.product = product;
      this.productName = productName;
      this.sku = sku;
      this.category = category;
      this.quantity = quantity;
      this.price = price;
   }
      
   public int getUserId() {
      return user;
   }
   
   public int getProductId() {
      return product;
   }
   
   public String getProductName() {
      return productName;
   }
   
   public String getSku() {
      return sku;
   }
   
   public String getCategory() {
      return category;
   }
   
   public int getQuantity() {
      return quantity;
   }
   
   public double getPrice() {
      return price;
   }
}
