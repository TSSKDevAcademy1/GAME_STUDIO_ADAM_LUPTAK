package Puzzle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.tools.examples.data.UserLogin;
import org.jboss.tools.examples.data.UserRepository;
import org.jboss.tools.examples.service.ScoreRegistration;
import org.jboss.tools.examples.service.UserRegistration;

import mini.Clue;
import mini.Field;
import mini.GameState;
import mini.Mine;
import mini.Tile;
import mini.Tile.State;
import score.Score;
import score.ScorePokus;

/**
 * Servlet implementation class hello2
 */
@WebServlet("/hello2")
public class hello2 extends HttpServlet {
	private static final String A_HREF_HTTP_LOCALHOST_8080_GAMES_HELLO2_X = "<a href=\"?x=";
	private static final String FIELD_SESSION = "fieldStones";
	private static final long serialVersionUID = 1L;

	
	@Inject
	private UserLogin userlogin;

	@Inject
	private UserRepository userRepositry;

	@Inject
	private ScoreRegistration scoreRegistration;

	@Inject
	private UserRegistration userRegistration;
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public hello2() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NumberTilesField field = (NumberTilesField) request.getSession().getAttribute(FIELD_SESSION);

		if (field == null) {
			field = new NumberTilesField(4, 4);
			ImagePreparator.cutImage(4, 4, "D:/workspace/games1/src/main/webapp/resources/gfx/logo.png",
					"D:/workspace/games1/src/main/webapp/resources/gfx/puzzleImages/puz");
			request.getSession().setAttribute(FIELD_SESSION, field);
		}
		String mark = "";

		PrintWriter out = response.getWriter();
		boolean custom = request.getParameter("custom") != null;

		response.setContentType("text/html");
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print(
				"<!-- Latest compiled and minified CSS --><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"><!-- Optional theme --><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\"><!-- Latest compiled and minified JavaScript --><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>");
		out.print("<title>Title of the document</title>");
		out.print("</head>");
		out.print("<body>");
		String game = request.getParameter("newGame");
		// System.out.println(request.getParameter("x"));
		// System.out.println(request.getParameter("y"));
		try {
			mark = request.getParameter("mark");
			int row = Integer.parseInt(request.getParameter("x"));
			int column = Integer.parseInt(request.getParameter("y"));

			if (row >= 0 && column >= 0 && !"1".equals(mark)) {
				afterClickMove(row, column, field);
				field.isSolved();
			}
			if (row >= 0 && column >= 0 && "1".equals(mark)) {

			}
		} catch (Exception e) {
			System.out.println("nejde");
		}
		String color = "green";

		if (field.getGamteState() == Puzzle.GameState.SOLVED) {

			request.getSession().setAttribute(FIELD_SESSION, null);
			color = "red";
			
			Random random = new Random();

			int score = random.nextInt(1000);

			ScorePokus scoree = new ScorePokus();
			scoree.setScore(score);
			Score scoree1 = new Score();
			scoree1.setScore(score);
			scoree1.addUser(userlogin.getUser());
			try {
				if (userlogin.getUser() != null) {
					scoreRegistration.addScore(scoree);
					scoreRegistration.addScore1(scoree1, "puzzle", userlogin.getUser());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		if ("2".equals(game)) {
			request.getSession().setAttribute(FIELD_SESSION, null);
			field = new NumberTilesField(4, 4);

		}
		String rows = request.getParameter("rows");
		System.out.println(rows);
		try {
			if (!"".equals(rows) || rows != null) {
				int riadky = Integer.parseInt(request.getParameter("rows"));
				int stlpce = Integer.parseInt(request.getParameter("columns"));
				
				field = new NumberTilesField(riadky,
						stlpce
						);
				ImagePreparator.cutImage(riadky, stlpce, "D:/workspace/games1/src/main/webapp/resources/gfx/logo.png",
						"D:/workspace/games1/src/main/webapp/resources/gfx/puzzleImages/puz");
				
				request.getSession().setAttribute(FIELD_SESSION, field);
				
			}
		} catch (Exception e) {

		}
		out.print("<div style=\"text-align: center  \">");
		out.print(
				"<div style=\"background-color: darkturquoise; margin-top: 0; padding-top: 1em;padding-bottom: 1em;margin-bottom: 10px;\">");
		if (userlogin.getUser() != null) {
			out.print("<h1>15 Puzzle Game " + userlogin.getUser().getName() + "</h1>");
		} else {
			out.print("<h1>15 Puzzle Game - not logged</h1>");
		}
		out.print("</div>");

		out.printf("<h2 style=\"color: %s\">GameState: %s</h2>", color, field.getGamteState());

		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				// out.print("<img src=\"resources/gfx/closed.png \">");

				out.print(A_HREF_HTTP_LOCALHOST_8080_GAMES_HELLO2_X + i + "&y=" + j + "\">");
				String imageType = imageGet(field.getNumberTiles(i, j));
				System.out.println(imageType);
				out.print("<img src=\"resources/gfx/puzzleImages/" + imageType
						+ "\" alt=\"Go to W3Schools!\" width=\"80\" height=\"80\" style=\"cursor: default;vertical-align: text-bottom\" >");

				out.print("</a>");

			}

			out.print("</br>");
		}
		out.print("</div>");
		System.out.println(mark);
		System.out.print(field.toString());
		out.print("<div class=\"row, text-center\" style=\"margin-top: 1em;\"; te>");
		out.print("<div class=\"col-sm-12\" style= \"text-align: center;\">");
		out.print("<form action=\"?\" method=\"get\">");
		out.print(
				"<button type=\"submit\" class=\"btn btn-success\" name=\"newGame\" value=\"2\" style=\"margin-left: 1px;margin-bottom: 1em;\" >New Game</button>");
		out.print(
				"<button type=\"submit\" class=\"btn btn-warning\" name=\"custom\" style=\"margin-left: 11px;margin-bottom: 1em;\">CustomGame</button>");
		out.print("</form>");
		out.print("	<footer>");
		out.print("<p>Made by ©Adam Luptak, ");
		out.print(
				"Contact information: <a href=\"mailto:adamluptakosice@gmail.com\">adamluptakosice@gmail.com</a>.<br><a href=\"http://localhost:8080/games1/main.jsf\">MainPage</a></p>");
		out.print("</footer>");
		out.print("</div>");

		out.print("</div>");

		if (custom) {
			out.print("<form action=\"?\" method=\"get\">");
			out.print("<div class=\"row, text-center\" style=\"margin-top: 1em;\"; te>");
			out.print("<div class=\"col-sm-12\" style= \"text-align: center;\">");
			out.print(
					"Rows count &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type=\"text\" name=\"rows\" maxlength=\"4\" style=\"margin-left: 20px;\"  size=\"4\"><br><br>");
			out.print(
					"Columns count &nbsp &nbsp <input type=\"text\" name=\"columns\" maxlength=\"4\" style=\"margin-left: 11px;\" size=\"4\"><br><br>");
			out.print("<input type=\"submit\" class=\"btn btn-success\" value=\"Submit\">");
			out.print("<button type=\"submit\" class=\"btn btn-danger\" style=\"margin-left: 11px;\">Cancel</button>");

			out.print("</form>");

			out.print("</div>");
			out.print("</div>");
		}

		out.print("</div>");

		out.print("</body>");
		out.print("</html>");
	}

	private String imageGet(NumberTile tile) {

		return "puz" + Integer.toString(tile.getValue()) + ".png";

	}

	public void afterClickMove(int buttonRow, int buttonCloumn, NumberTilesField playingField) {

		int row = buttonRow;
		int column = buttonCloumn;
		int[] position = playingField.huntForPosition(0);
		int counter = 0;
		for (int r = position[0] - 1; r <= position[0] + 1; r++) {
			for (int c = position[1] - 1; c <= position[1] + 1; c++) {

				if ((r >= 0 && c >= 0 && c < playingField.getColumnCount() && r < playingField.getRowCount())) {
					if (playingField.getNumberTiles(row, column).getValue() == playingField.getNumberTiles(r, c)
							.getValue()) {

						if (position[0] - 1 == r && position[1] == c) {
							playingField.moveDown();
						} else if (position[0] == r && position[1] + 1 == c) {
							playingField.moveToLeft();
						} else if (position[0] == r && position[1] - 1 == c) {
							playingField.moveToRight();
						} else if (position[0] + 1 == r && position[1] == c) {
							playingField.moveUp();
						}

					}
				}
			}
		}

	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
