package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.action.GetIntegerAction;


public class HRMDB {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection conn;
	// Viết contructor cho phép tạo đối tượng với các tham số cần thiết
	public HRMDB(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	long millis = System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis); 
	//User
	public boolean addUser(User user) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO Users (username,password,email,name,phone,address,role) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getName());
			pst.setString(5, user.getPhone());
			pst.setString(6, user.getAddress());
			pst.setInt(7, user.getRole());
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		Statement statement = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		rs = statement.executeQuery("Select * from Users");
		int id;
		String username;
		String password;
		String email;
		String name;
		String phone;
		String address;
		int role;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			id = rs.getInt(1);
			username = rs.getString(2);
			password = rs.getString(3);
			email = rs.getString(4);
			name = rs.getString(5);
			phone = rs.getString(6);
			address =rs.getString(7);
			role = rs.getInt(8);
			// Thêm vào list
			userList.add(new User(id,username,password,email,name,phone,address,role));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return userList;
	}
	public User getUser(User user) {
		Statement statement = null;
		ResultSet rs = null;
		
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		String sql = "Select * from Users WHERE username = "+"\""+user.getUsername()+"\""+" AND password = "+"\""+user.getPassword()+"\"";
		rs = statement.executeQuery(sql);
		int id;
		String username;
		String password;
		String email;
		String name;
		String phone;
		String address;
		int role;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			id = rs.getInt(1);
			username = rs.getString(2);
			password = rs.getString(3);
			email = rs.getString(4);
			name = rs.getString(5);
			phone = rs.getString(6);
			address =rs.getString(7);
			role = rs.getInt(8);
			// Thêm vào list
			User userLogin = new User(id,username,password,email,name,phone,address,role);
			return userLogin;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return user;
	}
	public boolean updateUser(User user) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "UPDATE Users SET username = ?,password = ?,email = ?,name = ?,phone = ?,address =?,role = ? "+"WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(8, user.getId());
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getName());
			pst.setString(5, user.getPhone());
			pst.setString(6, user.getAddress());
			pst.setInt(7, user.getRole());
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean deleteUser(int id) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "DELETE FROM Users WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	
	//category
	public List<Category> getCategoryList() {
		List<Category> categoryList = new ArrayList<Category>();
		Statement statement = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		rs = statement.executeQuery("Select * from Categories");
		int id;
		String name;
		Date created_at;
		Date updated_at;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			id = rs.getInt(1);
			name = rs.getString(2);
			created_at = rs.getDate(3);
			updated_at = rs.getDate(4);
			// Thêm vào list
			categoryList.add(new Category(id, name, created_at, updated_at));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return categoryList;
	}
	public boolean updateCategory(Category category) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "UPDATE Categories SET name = ?,updated_at = ? "+"WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(3, category.getId());
			pst.setString(1, category.getName());
			pst.setDate(2, date);
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean deleteCategory(int id) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "DELETE FROM Categories WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean addCategory(Category category) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO Categories (name,created_at,updated_at) VALUES(?,?,?)";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, category.getName());
			pst.setDate(2, date);
			pst.setDate(3, date);
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public Category getCategory(int id) {
		Statement statement = null;
		ResultSet rs = null;
		Category category = null;
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		rs = statement.executeQuery("Select name from Categories WHERE id = "+"\""+id+"\"");
		String name;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			name = rs.getString(1);
			category = new Category(id, name);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return category;
	}
	//order
	public boolean addOrder(Order order) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO Orders (user_id,customer_name,address,phone,total_price,status) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setInt(1, order.getUser_id());
			pst.setString(2, order.getCustomer_name());
			pst.setString(3, order.getAddress());
			pst.setString(4, order.getPhone());
			pst.setInt(5, order.getTotal_price());
			pst.setInt(6, 0);
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean updateOrder(Order order) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "UPDATE Orders SET user_id = ?,customer_name = ?,address = ?,phone = ?,total_price = ?,status = ? "+"WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(7, order.getId());
			pst.setInt(1, order.getUser_id());
			pst.setString(2, order.getCustomer_name());
			pst.setString(3, order.getAddress());
			pst.setString(4, order.getPhone());
			pst.setInt(5, order.getTotal_price());
			pst.setInt(6, order.getStatus());
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public List<Order> getOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		Statement statement = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		rs = statement.executeQuery("Select * from Orders");
		int id;
		int user_id;
		String customer_name;
		String address;
		String phone;
		int total_price;
		int status;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			id = rs.getInt(1);
			user_id = rs.getInt(2);
			customer_name = rs.getString(3);
			address = rs.getString(4);
			phone = rs.getString(5);
			total_price = rs.getInt(6);
			status = rs.getInt(7);
			
			// Thêm vào list
			orderList.add(new Order(id, user_id, customer_name, address, phone,total_price,status));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return orderList;
	}
	public boolean deleteOrder(int id) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "DELETE FROM Orders WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	//Product
	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<Product>();
		Statement statement = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		statement = conn.createStatement();
		rs = statement.executeQuery("Select * from Products");
		int id;
		String name;
		int amount;
		float origin_price;
		float sale_price;
		int user_id;
		int discount_percent;
		int category_id;
		int status;
		String option;
		String img;
		// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
		while (rs.next()) {
			id = rs.getInt(1);
			name = rs.getString(2);
			amount = rs.getInt(3);
			origin_price = rs.getFloat(4);
			sale_price = rs.getFloat(5);
			discount_percent = rs.getInt(6);
			user_id = rs.getInt(7);
			category_id = rs.getInt(8);
			status = rs.getInt(9);
			option = rs.getString(10);
			img = rs.getString(11);
			// Thêm vào list
		productList.add(new Product(id,name,amount,origin_price,sale_price,discount_percent,user_id,category_id,status,option,img));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return productList;
	}
	public boolean addProduct(Product product) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO Products (name,amount,origin_price,sale_price,user_id,discount_percent,category_id,status,option,img) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, product.getName());
			pst.setInt(2, product.getAmount());
			pst.setFloat(3, product.getOrigin_price());
			pst.setFloat(4, product.getSale_price());
			pst.setInt(5, product.getUser_id());
			pst.setInt(6, product.getDiscount_percent());
			pst.setInt(7, product.getCategory_id());
			pst.setInt(8, product.getStatus());
			pst.setString(9, product.getOption());
			pst.setString(10, product.getImg());
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean deleteProduct(int id) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "DELETE FROM Products WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean updateProduct(Product product) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "UPDATE Products SET name=?,amount=?,origin_price=?,sale_price=?,user_id=?,discount_percent=?,category_id=?,status=?,option=?,img=?"+"WHERE id = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setInt(11, product.getId());
			pst.setString(1, product.getName());
			pst.setInt(2, product.getAmount());
			pst.setFloat(3, product.getOrigin_price());
			pst.setFloat(4, product.getSale_price());
			pst.setInt(5, product.getUser_id());
			pst.setInt(6, product.getDiscount_percent());
			pst.setInt(7, product.getCategory_id());
			pst.setInt(8, product.getStatus());
			pst.setString(9, product.getOption());
			pst.setString(10, product.getImg());
			int i = pst.executeUpdate();
			if (i == 1) {
			result = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}
	public boolean checkHR(User user) {
			for (User u : getUserList()) {
				if(user.getUsername().equals(u.getUsername())) {
					return true;
			}
		}
		return false;
	}
	
}
