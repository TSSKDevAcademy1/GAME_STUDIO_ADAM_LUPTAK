package games;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.tools.examples.data.UserLogin;

/**
 * Servlet implementation class ComentarServlet
 */
public class ComentarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserLogin userlogin;

	@Inject
	private ComentarControler comentarControler;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		if (userlogin.getUser() != null) {
			try {
				String coment = request.getParameter("comment");
				if (coment != null) {

					System.out.println("tusom");

					comentarControler.nastavKoment(coment, "mineSweeper");
				}
				// int ratingNumber =
				// Integer.parseInt(request.getParameter("rating"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			out.print("<h1>Put your coment here</h1>");

			out.print("<form action=\"?\">");
			out.print("  First name:<br>");
			out.print("  <textarea rows=\"4\" cols=\"50\" name=\"comment\" > </textarea>");
			out.print("  <br>");
			out.print("  <br><br>");
			out.print("  <input type=\"submit\" class=\"btn btn-success\" value=\"Submit\">");
			out.print("</form>");

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
