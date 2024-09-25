package vn.ntkiet.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.ntkiet.configs.DBConnectMySQL;
import vn.ntkiet.dao.IUserDao;
import vn.ntkiet.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UserModel> findAll() {
		String sql = "select * from user";

		List<UserModel> list = new ArrayList<>();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next() /* Next từng dòng tới cuối bảng */) {

				UserModel user = new UserModel();
				//user.setId(rs.getInt("id"));
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				list.add(user);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findByID(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		UserModel user = new UserModel();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImage(rs.getString("image"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		UserModel user = new UserModel();
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRoleID(rs.getInt("roleId"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImage(rs.getString("image"));
				user.setPhone(rs.getString("phone"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO user(username, roleID, password, email, fullname, image, phone, createdDate) value (?,?,?,?,?,?,?,?)";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getRoleID());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getFullname());
            ps.setString(6, user.getImage());
            ps.setString(7, user.getPhone());
            ps.setDate(8, (Date) user.getCreatedDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void update(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void insertRegister(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistEmails(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
        UserModel user = new UserModel();
           try {
               conn = super.getDatabaseConnection();
               ps = conn.prepareStatement(sql);
               ps.setString(1, email);
               rs = ps.executeQuery();
               while (rs.next()) {
                   user.setId(rs.getInt("id"));
                   user.setUsername(rs.getString("username"));
                   user.setPassword(rs.getString("password"));
                   user.setImage(rs.getString("images"));
                   user.setFullname(rs.getString("fullname"));
                   user.setEmail(rs.getString("email"));
                   user.setPhone(rs.getString("phone"));
                   user.setRoleID(rs.getInt("roleid"));
                   user.setCreatedDate(rs.getDate("createdDate"));
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return user.getEmail() != null;
	}

	@Override
	public boolean checkExistPhones(String phone) {
		String sql = "SELECT * FROM user WHERE phone = ?";
        UserModel user = new UserModel();
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setImage(rs.getString("images"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRoleID(rs.getInt("roleid"));
                user.setCreatedDate(rs.getDate("createdDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.getPhone() != null;
	}

	@Override
	public void updatePassword(String username, String password) {
		String sql = "UPDATE user SET password = ? WHERE username = ?";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		List<UserModel> users = userDao.findAll();
		 
		for (UserModel user : users) {
			System.out.println(user);
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập vào tên user muốn tìm: ");
	    String x = scanner.next();
	    UserModel user = userDao.findByUsername(x);
		System.out.println(user);
	}

	@Override
	public void updateAccount(String username, String fullname, String phone) {
		String sql = "UPDATE user SET fullname = ?, phone = ? WHERE username = ?";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và ps (bạn cần quản lý tài nguyên đúng cách)
            try {
                if (ps != null) 
                	ps.close();
                if (conn != null) 
                	conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
	}

	@Override
	public void updateFile(String username, String image) {
		String sql = "UPDATE user SET image = ? WHERE username = ?";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và ps (bạn cần quản lý tài nguyên đúng cách)
            try {
                if (ps != null) 
                	ps.close();
                if (conn != null) 
                	conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

}
