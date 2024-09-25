package vn.ntkiet.models;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String fullname;	
	private String email;
	private String image;
	private int roleID;
	private String phone;
	private Date createdDate;
	
	public UserModel() {
		super();
	}

	
	public UserModel(int id, String username, String password, String fullname, String email, String image, int roleID,
			String phone, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.image = image;
		this.roleID = roleID;
		this.phone = phone;
		this.createdDate = createdDate;
	}

	public UserModel(String username, String password, String fullname, String email, String image, int roleID,
			String phone, Date createdDate) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.image = image;
		this.roleID = roleID;
		this.phone = phone;
		this.createdDate = createdDate;
	}

	public UserModel(String username, String password, String fullname, String email, int roleID) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.roleID = roleID;
	}

	public UserModel(String username, String fullname, String email) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", email=" + email + ", image=" + image + ", roleID=" + roleID + ", phone=" + phone + ", createdDate="
				+ createdDate + "]";
	}
	
	
}
