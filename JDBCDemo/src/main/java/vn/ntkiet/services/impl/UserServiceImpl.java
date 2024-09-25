package vn.ntkiet.services.impl;

import vn.ntkiet.configs.DBConnectMySQL;
import vn.ntkiet.dao.CheckLogin;
import vn.ntkiet.dao.IUserDao;
import vn.ntkiet.dao.impl.UserDaoImpl;
import vn.ntkiet.models.UserModel;
import vn.ntkiet.services.IUserService;

public class UserServiceImpl implements IUserService {
	// Lấy toàn bộ hàm trong tầng Dao của user
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel checkLogin(String username, String password) {
		CheckLogin checkLogin = new CheckLogin();
		if (checkLogin.checkLogin(username, password)) {
			return userDao.findByUsername(username);
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void insertUser(UserModel userModel) {
		userDao.insert(userModel);
	}

	@Override
	public boolean registerUser(String username, int role, String password, String email, String phone) {
		if (checkExistUser(username).getId() != 0 || checkExistEmail(email) || checkExistPhone(phone)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new UserModel(username, password, "null", email, "null", role, phone, date));
		return true;

	}

	@Override
	public UserModel checkExistUser(String username) {
        return userDao.findByUsername(username);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmails(email);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		 return userDao.checkExistPhones(phone);
	}

	@Override
	public void updatePassword(String username, String password) {
		userDao.updatePassword(username, password);
	}

	public static void main(String[] args) {
		IUserService userService = new UserServiceImpl();
		UserModel user = userService.checkLogin("admin", "123");
		if (user != null) {
			System.out.println("Đăng nhập thành công với tài khoản: " + user.getUsername());
		} else {
			System.out.println("Đăng nhập thất bại");
		}
		
//		UserModel user2 = userService.findByUsername("guest");
//		if (user2.getId() != 0)
//			System.out.println(user2);
//		else
//			System.out.println("false");
		
		if (userService.registerUser("guest", 2, "123", "guest@gmail.com", "0989821812"))
			System.out.println("true");
		else
			System.out.println("false");
	}

	public void updateAccount(String username, String fullname, String phone) {
		userDao.updateAccount(username, fullname, phone);
	}

	@Override
	public void updateFile(String username, String images) {
		userDao.updateFile(username, images);
		
	}
}
