package br.com.demoServlet.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.demoServlet.jdbc.dao.UserDao;
import br.com.demoServlet.models.User;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	private UserDao userDao = new UserDao();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		String page = "users.jsp";
		
		if (id != null && action != null && action.equals("remove")) {
			Integer userId = Integer.parseInt(id);
			userDao.remove(userId);
		}
		
		if ((action != null && action.equals("update")) || (action != null && action.equals("store"))) {
			if (id != null) {
				Integer userId = Integer.parseInt(id);
				request.setAttribute("user", userDao.index(userId));
			}
			page = "userReg.jsp";
		} else {
			request.setAttribute("users", userDao.show());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/"+page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		User user = new User(null, name, email);
		
		if (id != null && !id.equals("")) {
			user.setId(Integer.parseInt(id));
		}
		
		if (user.getId() != null) {
			userDao.update(user);
		} else {
			userDao.store(user);
		}
		
		request.setAttribute("users", userDao.show());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/users.jsp");
		dispatcher.forward(request, response);
	}

}
