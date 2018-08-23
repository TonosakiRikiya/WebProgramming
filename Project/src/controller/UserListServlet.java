package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 if(request.getSession().getAttribute("userInfo") == null) {
			 response.sendRedirect("LoginServlet");
			 return;
		 }

		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();


		request.setAttribute("userList", userList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 request.setCharacterEncoding("UTF-8");
		 String loginId =request.getParameter("login_id");
		 String userName =request.getParameter("name");
		 String birthDate1 = request.getParameter("birth_date1");
		 String birthDate2 = request.getParameter("birth_date2");

		UserDao userDao = new UserDao();
		List<User> userList = userDao.findSearch(loginId, userName, birthDate1,birthDate2);
		request.setAttribute("userList", userList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}

}
