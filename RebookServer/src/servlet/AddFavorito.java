package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddFavorito")
public class AddFavorito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;
        
        session.setAttribute("idUser", request.getParameter("idUser"));
        session.setAttribute("idLivro", request.getParameter("idLivro"));
        
        dispatcher = request.getRequestDispatcher("WEB-INF/jsp/AddFavoritoAnswer.jsp");
		dispatcher.forward(request, response);
	}

}
