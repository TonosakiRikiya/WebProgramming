package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		String id =request.getParameter("id");

		System.out.println(id);

		UserDao userDao = new UserDao();
		User user=userDao.findByDetail(Integer.parseInt(id));
		request.setAttribute("user",user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete.jsp");
		dispatcher.forward(request, response);
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String id =request.getParameter("id");
			System.out.println(id);

			UserDao userDao = new UserDao();
			userDao.findByDelete(Integer.parseInt(id));

			response.sendRedirect("UserListServlet");
	}

}
