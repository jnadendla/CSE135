<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String userName = (String) session.getAttribute("userName");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Order</title>
</head>
<body>
    <p>
        <font size="6"> Order Product </font>
    </p>
    <p style="color: green;">
        <font size="3"> Hello </font> <font size="3">
        <%
        //print welcom user
        out.print(userName);
        %>
        </font>
    </p>
    <table>
        <tr>
            <td valign="top">
                <%-- -------- Include menu HTML code -------- --%> <jsp:include
                    page="/menu.html" />
            </td>
        </tr>
        <table border="1">
            <tr>
	            <th>ID</th>
	            <th>Name</th>
	            <th>SKU</th>
	            <th>Price</th>
	            <th>Category</th>
	            <th>Quantity</th>   
            </tr>
            <tr>
                <%
                    Integer id = (Integer) session.getAttribute("productId");
                    int productID = (int)id;
	                String productName = (String) session.getAttribute("pname");
	                String sku = (String) session.getAttribute("sku");
	                String price = (String) session.getAttribute("price");
	                String category = (String) session.getAttribute("category");
                %>
                <form action="ProductOrder" method="post">
                    <td><%=productID %></td>
                    <td><%=productName %></td>
                    <td><%=sku %></td>
                    <td><%=price %></td>
                    <td><%=category %></td>
                    <td><input name="quantity" input="text" size="15"/></td>
                    <td><input type="submit" value="Add to Cart"/></td>
                </form>
            </tr>
        </table><br>
        <table border="1">
            <%
                //Get items from cart
	            LinkedList<Purchase> purchases = (LinkedList)session.getAttribute("purchases");
	            if(purchases != null || purchases.isEmpty()) {   
            %>
            <caption>Shopping Cart</caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>SKU</th>
                <th>Price</th>
                <th>Category</th>
                <th>Quantity</th>
            </tr>
            <%@ page import="java.util.LinkedList"%>
            <%@ page import="db.Purchase"%>
            <%
                   double total_price = 0.0;
                   //All the items in the shopping cart we want to display
                   for(int i=0; i < purchases.size(); i++) {
                      Purchase p = purchases.get(i);
                      
                      int pid = p.getProductId();
                      String _name = p.getProductName();
                      String _sku = p.getSku();
                      double _price = p.getPrice();
                      String _category = p.getCategory();
                      int _quantity = p.getQuantity();
                      total_price += _price;
            %>
            <tr>
                <form action="PrductOrder" method="post">
                    <td><%=pid %></td>
                    <td><%=_name%></td>
                    <td><%=_sku %></td>
                    <td><%=_price %></td>
                    <td><%=_category %></td>
                    <td><%=_quantity%></td>
                </form>
            </tr>
            <%
                   }
                   //round to 2 decimal places
                   int temp = (int)(total_price * Math.pow(10 , 2));  
                   total_price = ((double)temp)/Math.pow(10 , 2); 
            %>
            <tr>
                <td><p><b>Total Price:</b></p></td>
                <td><%=total_price %></td>
            </tr>
            <%
               }
            %>
        </table>
    </table>
</body>
</html> 
