package db;
import java.sql.*;

public class CategoriesDB {
	private DbConnection db;

	public CategoriesDB() {
		db = new DbConnection();
		db.aquireConnection();
	}

	public ResultSet getCategories() throws SQLException {
		// Create the statement
		Statement statement = db.connection.createStatement();

		// Use the created statement to SELECT
		// the student attributes FROM the Student table.
		ResultSet rs = statement.executeQuery("SELECT * FROM categories");
		
		return rs;
	}

	public void insert(String name, String description) throws SQLException {
		// Begin transaction
		db.connection.setAutoCommit(false);

		// Create the prepared statement and use it to
		// INSERT student values INTO the students table.
		PreparedStatement pstmt = db.connection
				.prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)");

		pstmt.setString(1, name);
		pstmt.setString(2, description);

		int rowCount = pstmt.executeUpdate();

		// Commit transaction
		db.connection.commit();
		db.connection.setAutoCommit(true);
	}

	public void update(String name, String description, String id)
			throws SQLException {
		// Begin transaction
		db.connection.setAutoCommit(false);

		// Create the prepared statement and use it to
		// UPDATE student values in the Students table.
		PreparedStatement pstmt = db.connection
				.prepareStatement("UPDATE categories SET name = ?, description = ?"
						+ " WHERE id = ?");

		pstmt.setString(1, name);
		pstmt.setString(2, description);
		pstmt.setInt(3, Integer.parseInt(id));

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
				.prepareStatement("DELETE FROM categories WHERE id = ?");

		pstmt.setInt(1, Integer.parseInt(id));
		int rowCount = pstmt.executeUpdate();

		// Commit transaction
		db.connection.commit();
		db.connection.setAutoCommit(true);
	}
}
