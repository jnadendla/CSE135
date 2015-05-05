import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Category;
import db.ProductsDB;
import db.ClearedRequest;

public class Products extends HttpServlet {
	ProductsDB pdb = new ProductsDB();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		String sku = request.getParameter("sku");
		String price = request.getParameter("price");
		String category = request.getParameter("category");

		String action = request.getParameter("action");
		ClearedRequest creq = new ClearedRequest(request);

		if (action != null && action.equals("search")) {
			request.getRequestDispatcher("/Products.jsp").forward(request,
					response);
		} else if (action != null && action.equals("insert")) {

			if (name == null || name.trim().isEmpty() || sku == null
					|| sku.trim().isEmpty() || price == null
					|| price.trim().isEmpty() || category == null
					|| category.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: products/insert");
			} else {
				try {

					pdb.insert(name, sku, price, category);

					request.setAttribute("success",
							"Successfully added " + name);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Products.jsp").forward(creq,
					response);
		} else if (action != null && action.equals("update")) {

			if (id == null || id.trim().isEmpty() || name == null
					|| name.trim().isEmpty() || sku == null
					|| sku.trim().isEmpty() || price == null
					|| price.trim().isEmpty() || category == null
					|| category.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: products/update");
			} else {
				try {

					pdb.update(name, sku, price, category, id);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Products.jsp").forward(creq,
					response);
		} else if (action != null && action.equals("delete")) {

			if (id == null || id.trim().isEmpty()) {
				// show data modification failure
				request.setAttribute("error",
						"Data modification failure: products/delete");
			} else {
				try {
					pdb.delete(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Products.jsp").forward(creq,
					response);
		}
	}
}
