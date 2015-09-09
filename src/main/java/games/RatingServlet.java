package games;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.tools.examples.data.UserLogin;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserLogin userlogin;

	@Inject
	private RatingControler ratingControler;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		if (userlogin.getUser() != null) {
			try {
				String rating = request.getParameter("rating");
				if (rating != null) {
					if (Integer.parseInt(rating) <= 10 && Integer.parseInt(rating) >= 0) {
						System.out.println("tusom");
						ratingControler.setRating(Integer.parseInt(rating), "mineSweeper");
					} else {
						out.print("zle zadane hodnotenie");
					}
				}
				// int ratingNumber =
				// Integer.parseInt(request.getParameter("rating"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			out.print("<form action=\"?\">");
			out.print(" Rating From 1 - 10 <br>");
			out.print(" <input type=\"text\" name=\"rating\" >");
			out.print("<br><br>");
			out.print(
					"<input type=\"submit\" style=\"margin-bottom: 10px;\" class=\"btn btn-success\" value=\"Submit\">");
			out.print("</form> ");
		} else {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
