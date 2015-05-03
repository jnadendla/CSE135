<ul>
    <a name="categories"
        href="JavaScript:window.location='Categories.jsp';">Categories</a>
    <a name="products" href="JavaScript:window.location='Products.jsp';">Products</a>
    <a name="browse" href="JavaScript:window.location='Products.jsp';">Browse
        Products</a>
    <%
    Integer val = (Integer)session.getAttribute("userRole");
    int role = val;
    if(role == 2) {
    %>
    <a name="cart" href="JavaScript:window.location='ShoppingCart.jsp';">Buy Shopping Cart</a>
    <%
    }
    %>
</ul>
