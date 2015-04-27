import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CategoriesDB;
import db.ClearedRequest;

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
		PrintWriter pw = response.getWriter();

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		String action = request.getParameter("action");
		if (action != null && action.equals("insert")) {
			try {
				cdb.insert(name, description);
			} catch (SQLException e) {
				printFailure(pw);
				e.printStackTrace();
			}
		}
		if (action != null && action.equals("update")) {
			try {
				cdb.update(name, description, id);
			} catch (SQLException e) {
				printFailure(pw);
				e.printStackTrace();
			}
		}
		if (action != null && action.equals("delete")) {
			try {
				cdb.delete(id);
			} catch (SQLException e) {
				printFailure(pw);
				e.printStackTrace();
			}
		}
		ClearedRequest creq = new ClearedRequest(request);
		request.getRequestDispatcher("/Categories.jsp").forward(creq, response);
	}

	private void printFailure(PrintWriter pw) {
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Failure</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p style=\"color:red\">");
		pw.println("<font size=\"4\">CATEGORY FAILURE: Insufficient Information Was Input</font>");
		pw.println("</p>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
