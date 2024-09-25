package vn.ntkiet.dao;

import java.util.List;

import vn.ntkiet.models.UserModel;

public interface IUserDao {

	List<UserModel> findAll();
	UserModel findByID(int id);
	UserModel findByUsername(String username);
	void insert(UserModel user);
	void update(UserModel user);
	void delete(UserModel user);
	void insertRegister(UserModel user);
	boolean checkExistEmails(String email);
    boolean checkExistPhones(String phone);
    void updatePassword(String username, String password);
	void updateAccount(String username, String fullname, String phone);
	void updateFile(String username, String images);
}
