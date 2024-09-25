package vn.ntkiet.services;

import vn.ntkiet.models.UserModel;

public interface IUserService {
	UserModel checkLogin(String username, String password);
	UserModel findByUsername(String username);
	void insertUser(UserModel userModel);
    boolean registerUser(String username, int role, String password, String email, String phone);
    UserModel checkExistUser(String username);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    void updatePassword(String username, String password);
    void updateAccount(String username, String fullname, String phone);
    void updateFile(String username, String images);
}
