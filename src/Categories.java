import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CategoriesDB;
import db.ClearedRequest;
import db.ProductsDB;

public class Categories extends HttpServlet {
	CategoriesDB cdb = new CategoriesDB();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		String action = request.getParameter("action");
		if (action != null && action.equals("insert")) {

			if (name == null || name.trim().isEmpty() || description == null
					|| description.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: categories/insert");
				
			} else if(cdb.containsCategory(name)) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: category already exists");
				
			}
			else {
				try {
					cdb.insert(name, description);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (action != null && action.equals("update")) {

			if (id == null || id.trim().isEmpty() || name == null
					|| name.trim().isEmpty() || description == null
					|| description.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: categories/update");
			} else {
				try {
					cdb.update(name, description, id);
				} catch (SQLException e) {
					// printFailure(pw);
					e.printStackTrace();
				}
			}
		}
		if (action != null && action.equals("delete")) {
			if (id == null || id.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: categories/delete/id");
			} else {
				try {
					ProductsDB pdb = new ProductsDB();
					ResultSet rs = pdb.getProducts(id);
					
					if (rs.next() == false) {
						cdb.delete(id);
					} else {
						request.setAttribute("error",
								"Data modification failure: categories/delete/products");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		ClearedRequest creq = new ClearedRequest(request);
		request.getRequestDispatcher("/Categories.jsp").forward(creq, response);
	}
}
