package vn.ntkiet.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.ntkiet.services.impl.UserServiceImpl;

import static vn.ntkiet.utils.Constant.UPLOAD_DIRECTORY;
import static vn.ntkiet.utils.Constant.DEFAULT_FILENAME;

@WebServlet(name = "UploadFile", urlPatterns = "/uploadfile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadFileController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    public static final String UPDATEFILE = "/views/updateprofile.jsp";

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return DEFAULT_FILENAME;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String uploadPath = File.separator + UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ
        //String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY; //upload vào thư mục project

        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        //Up file
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                if (fileName.equals(DEFAULT_FILENAME))
                    break;
                part.write(uploadPath + File.separator + fileName);
            }
            String filePath = (uploadPath + File.separator + fileName).toString();
            request.setAttribute("message", "File " + fileName +  "đã được upload thành công vào " + filePath);
            UserServiceImpl userService = new UserServiceImpl();
            userService.updateFile(username, filePath);
        } catch (FileNotFoundException e) {
            request.setAttribute("message", "There was an error: " + e.getMessage());
        }
        getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/updateprofile");
    }
}
