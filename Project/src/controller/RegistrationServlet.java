package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;



/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 request.setCharacterEncoding("UTF-8");


		 String login_id = request.getParameter("login_id");
		 String password = request.getParameter("password");
		 String password2 = request.getParameter("password2");
		 String name = request.getParameter("name");
		 String birth_date_str = request.getParameter("birth_date");
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date birth_date = null;
		try {
			birth_date = new Date(format.parse(birth_date_str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String now = formatDate.format(new java.util.Date());


		UserDao userDao = new UserDao();


		 if(login_id.isEmpty() || password.isEmpty() ||name.isEmpty() ||birth_date_str.isEmpty()) {
			 request.setAttribute("errmsg", "入力された値は正しくありません");

			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
				dispatcher.forward(request, response);
				return;
		 }

		if(!(password.equals(password2))) {
			 request.setAttribute("errmsg", "入力された値は正しくありません");

			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
				dispatcher.forward(request, response);
				return;
		}
		User user=userDao.findByLogin_idInfo(login_id);
		 String login_Id = request.getParameter("login_id");
		if(login_id !=login_Id) {
			 request.setAttribute("errmsg", "入力された値は正しくありません");

			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
				dispatcher.forward(request, response);
				return;


		}

		userDao.findByRegistrationInfo(login_id, password, name, birth_date, now, now);
		response.sendRedirect("UserListServlet");
	}

}
