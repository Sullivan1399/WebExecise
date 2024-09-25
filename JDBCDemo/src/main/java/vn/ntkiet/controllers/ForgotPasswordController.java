package vn.ntkiet.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.ntkiet.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String FORGOTPASSWORD = "/views/forgotPassword.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String newpassword = req.getParameter("new-password");
        String repeatpassword = req.getParameter("repeat-password");
        UserServiceImpl service = new UserServiceImpl();
        String error = "";
        boolean hasError = false;
        if (service.checkExistUser(username) == null) {
            error = "Tên đăng nhập không tồn tại!";
            hasError = true;
        }
        if (!service.checkExistEmail(email)) {
            error = "Email không tồn tại!";
            hasError = true;
        }
        if (!newpassword.equals(repeatpassword)) {
            error = "Mật khẩu không trùng khớp!";
            hasError = true;
        }
        if (hasError) {
            req.setAttribute("error", error);
            req.getRequestDispatcher(FORGOTPASSWORD).forward(req, resp);
        }
        else {
            service.updatePassword(username, newpassword);
            req.setAttribute("success", "Đổi mật khẩu thành công!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(FORGOTPASSWORD).forward(req, resp);
    }
}
