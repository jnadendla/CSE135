package db;

import java.sql.*;

public class ProductsDB {
	private DbConnection db;

	public ProductsDB() {
		db = new DbConnection();
		db.aquireConnection();
	}

	public ResultSet getProducts() throws SQLException {
		// Create the statement
		Statement statement = db.connection.createStatement();

		// Use the created statement to SELECT
		// the student attributes FROM the Student table.
		ResultSet rs = statement.executeQuery("SELECT * FROM products");

		return rs;
	}

	public ResultSet getProducts(String categoryId) throws SQLException {
		// Create the statement
		Statement statement = db.connection.createStatement();

		// Use the created statement to SELECT
		// the student attributes FROM the Student table.
		ResultSet rs = statement
				.executeQuery("SELECT * FROM products WHERE category = "
						+ categoryId);

		return rs;
	}

	public ResultSet getProducts(String categoryId, String name)
			throws SQLException {
		
		System.out.println("catgoryId: " + categoryId);
		System.out.println("name: " + name);
		// Create the statement
		Statement statement = db.connection.createStatement();

		ResultSet rs = null;

		if (categoryId != null && !categoryId.isEmpty() && name != null
				&& !name.isEmpty()) { // filter both
			rs = statement
					.executeQuery("SELECT * FROM products WHERE category = "
							+ categoryId + " AND name LIKE \'%" + name + "%\'");
		} else if (categoryId != null && !categoryId.isEmpty()
				&& (name == null || name.isEmpty())) { // filter category
			rs = statement
					.executeQuery("SELECT * FROM products WHERE category = "
							+ categoryId);
		} else if ((categoryId == null || categoryId.isEmpty()) && (name != null
				&& !name.isEmpty())) { // filter by name
			System.out.println("filtering by name");
			rs = statement
					.executeQuery("SELECT * FROM products WHERE name LIKE \'%"
							+ name + "%\'");
		} else { // no filters
			rs = statement.executeQuery("SELECT * FROM products");
		}

		return rs;
	}

	public void insert(String name, String sku, String price, String category)
			throws SQLException {
		// Begin transaction
		db.connection.setAutoCommit(false);

		// Create the prepared statement and use it to
		// INSERT student values INTO the students table.
		PreparedStatement pstmt = db.connection
				.prepareStatement("INSERT INTO products (name, sku, price, category) VALUES (?, ?, ?, ?)");

		pstmt.setString(1, name);
		pstmt.setString(2, sku);
		pstmt.setObject(3, price, java.sql.Types.NUMERIC, 2);
		pstmt.setInt(4, Integer.parseInt(category));

		int rowCount = pstmt.executeUpdate();

		// Commit transaction
		db.connection.commit();
		db.connection.setAutoCommit(true);
	}

	public void update(String name, String sku, String price, String category,
			String id) throws SQLException {
		// Begin transaction
		db.connection.setAutoCommit(false);

		// Create the prepared statement and use it to
		// UPDATE student values in the Students table.
		PreparedStatement pstmt = db.connection
				.prepareStatement("UPDATE products SET name = ?, sku = ?, price = ?,"
						+ " category = ? WHERE id = ?");

		pstmt.setString(1, name);
		pstmt.setString(2, sku);
		pstmt.setObject(3, price, java.sql.Types.NUMERIC, 2);
		pstmt.setInt(4, Integer.parseInt(category));
		System.out.println(category);
		pstmt.setInt(5, Integer.parseInt(id));

		int rowCount = pstmt.executeUpdate();

		// Commit transaction
		db.connection.commit();
		db.connection.setAutoCommit(true);
	}

	public void delete(String id) throws SQLException {
		// Begin transaction
		db.connection.setAutoCommit(false);

		// Create the prepared statement and use it to
		// DELETE students FROM the Students table.
		PreparedStatement pstmt = db.connection
				.prepareStatement("DELETE FROM products WHERE id = ?");

		pstmt.setInt(1, Integer.parseInt(id));
		int rowCount = pstmt.executeUpdate();

		// Commit transaction
		db.connection.commit();
		db.connection.setAutoCommit(true);
	}
}
