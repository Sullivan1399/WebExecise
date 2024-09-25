package vn.ntkiet.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.ntkiet.models.UserModel;
import vn.ntkiet.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/updateprofile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class UpdateProfileController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public static final String UPDATEPROFILE = "/views/updateprofile.jsp";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        if (username == null || username.equals("") || fullname == null || fullname.equals("") || phone == null || phone.equals("")) {
            request.setAttribute("message", "Vui lòng nhập đầy đủ thông tin!");
            response.sendRedirect((request.getContextPath() + "/updateprofile"));
            return;
        }
        if (phone.length() != 10) {
            request.setAttribute("message", "Số điện thoại phải có 10 chữ số!");
            response.sendRedirect((request.getContextPath() + "/updateprofile"));
            return;
        }
        try {
            UserServiceImpl userService = new UserServiceImpl();
            userService.updateAccount(username, fullname, phone);
            UserModel user = userService.findByUsername(username);
            request.setAttribute("message",
                    "Thông tin tài khoản đã được cập nhật! \n" +
                            "Username: " + user.getUsername() + ",\n" +
                            "Fullname: " + user.getFullname() + ",\n" +
                            "Phone: " + user.getPhone() + ",\n" +
                            "Image: " + user.getImage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Có lỗi xảy ra!");
        }
        response.sendRedirect(request.getContextPath() + "/updateprofile?message=" + URLEncoder.encode((String) request.getAttribute("message"), "UTF-8"));

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username from cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        UserServiceImpl userService = new UserServiceImpl();
        UserModel users = userService.findByUsername(username);

        request.setAttribute("username", username);
        request.setAttribute("fullname", users.getFullname());
        request.setAttribute("phone", users.getPhone());
        request.setAttribute("message", request.getParameter("message"));
        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher(UPDATEPROFILE).forward(request, response);
    }
}
