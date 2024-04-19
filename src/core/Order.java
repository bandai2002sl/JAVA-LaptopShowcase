package core;

public class Order {
	final String[] status_name = {"Chờ xác nhận","Đã đóng gói","Đang vận chuyển","Đã giao hàng","giao hàng thất bại","Hủy"};
	private int id;
	private int user_id;
	private String customer_name;
	private String address;
	private String phone;
	private int total_price;
	private int status;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(int user_id, String customer_name, String address, String phone, int total_price){
		this.user_id = user_id;
		this.customer_name = customer_name;
		this.address = address;
		this.phone = phone;
		this.total_price = total_price;
	}
	public Order(int id,int user_id, String customer_name, String address, String phone, int total_price,int status){
		this.id = id;
		this.user_id = user_id;
		this.customer_name = customer_name;
		this.address = address;
		this.phone = phone;
		this.total_price = total_price;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public String getPhone() {
		return phone;
	}
	public int getStatus() {
		return status;
	}
	public int getTotal_price() {
		return total_price;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "name:"+this.customer_name;
	}
}
