package vn.ntkiet.controllers;

import com.mysql.cj.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.ntkiet.services.IUserService;
import vn.ntkiet.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String REGISTER = "/views/register.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			resp.sendRedirect(req.getContextPath() + "/admin");
			return;
		}
		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/admin");
					return;
				}
			}
		}
		req.getRequestDispatcher(REGISTER).forward(req, resp);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String alertMsg = "";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		int role = 0;
		String selectedRole = req.getParameter("role");
		if (selectedRole.equals("Administrator")) {
		    role = 1;
		} else if (selectedRole.equals("Manager")) {
		    role = 3;
		} else if (selectedRole.equals("User")) {
		    role = 2;
		} else {
		    alertMsg = "Role không hợp lệ!";
		    req.setAttribute("alert", alertMsg);
		    req.getRequestDispatcher(REGISTER).forward(req, resp);
		    return;
		}
		
		UserServiceImpl service = new UserServiceImpl();
		
		if (service.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(REGISTER).forward(req, resp);
			return;
		}
		if (service.checkExistUser(username).getId() != 0) {
			alertMsg = "Tài khoản đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(REGISTER).forward(req, resp);
			return;
		}
		if (service.checkExistPhone(phone)) {
			alertMsg = "Số điện thoại đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(REGISTER).forward(req, resp);
			return;
		}
		boolean isSuccess = service.registerUser(username, role, password, email, phone);
		if (isSuccess) {			
			//req.setAttribute("alert", alertMsg);
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(REGISTER).forward(req, resp);
		}
	}
}