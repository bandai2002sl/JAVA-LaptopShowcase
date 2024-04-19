package core;

public class User {
	final String[] role_name = {"Admin","Nhân viên","Khách hàng"};
	private int id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String phone;
	private String address;
	private int role;
	private String roleString = getRoleString();
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public User(int id,String username,String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public User(String username,String password,String email,String name,String phone,String address,int role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	public User(int id,String username,String password,String email,String name,String phone,String address,int role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public int getRole() {
		return role;
	}
	public String getUsername() {
		return username;
	}
	public String getRoleString() {
		return role_name[this.getRole()];
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User)obj;
			if(getPassword().equals(user.getPassword()) && getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "===Thông tin===" + "\nMã: "+this.id + "\nVai tro: " + roleString;
	}
}