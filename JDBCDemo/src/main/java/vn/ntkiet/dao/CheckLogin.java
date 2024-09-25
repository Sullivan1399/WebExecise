package vn.ntkiet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.ntkiet.configs.DBConnectMySQL;
import vn.ntkiet.dao.impl.UserDaoImpl;
import vn.ntkiet.models.UserModel;

public class CheckLogin extends DBConnectMySQL{

	public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            UserDaoImpl usersDao = new UserDaoImpl();
            UserModel user = usersDao.findByUsername(username);
            if (user == null) {
                throw new Exception("User not found.");
            }
            else if (username != null && password != null) {
                conn = super.getDatabaseConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
            else if (username == null || password == null) {
                throw new Exception("Username or password is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        CheckLogin checkLogin = new CheckLogin();
        System.out.println(checkLogin.checkLogin("NTK", "123"));
    }
}
