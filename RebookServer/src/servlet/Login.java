package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;
        
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        
        session.setAttribute("username", request.getParameter("username"));
        session.setAttribute("password", request.getParameter("password"));
        
        dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LoginAnswer.jsp");
		dispatcher.forward(request, response);
	}

}
