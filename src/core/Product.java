package core;

import java.util.Date;
import java.util.List;
public class Product {
	final String[] status_product = {"Còn hàng","Hết hàng"};
	private int id;
	private String name;
	private float origin_price;
	private float sale_price;
	private int discount_percent;
	private int amount;
	private int user_id;
	private int category_id;
	private int status;
	private String option;
	private String category_name = "";
	private String img;
	public Product() {
		
	}
	public Product(String option) {
		this.option = option;
	}
	public Product(int id, String name, int amount, float origin_price, float sale_price, int discount_percent, int user_id, int category_id, int status, String option, String img) {
		this.id = id;
		this.name = name;
		this.amount =amount;
		this.origin_price = origin_price;
		this.sale_price = sale_price;
		this.discount_percent = discount_percent;
		this.user_id = user_id;
		this.category_id = category_id;
		this.option = option;
		this.img = img;
	}
	public Product( String name,int amount, float origin_price,float sale_price,int discount_percent,int user_id,int category_id,int status,String option, String img) {
		this.name = name;
		this.amount =amount;
		this.origin_price = origin_price;
		this.sale_price = sale_price;
		this.discount_percent = discount_percent;
		this.user_id = user_id;
		this.category_id = category_id;
		this.option = option;
		this.img = img;
	}
	public Product( String name,int amount, float origin_price,float sale_price,int discount_percent,int user_id,int category_id,int status,String img) {
		this.name = name;
		this.amount =amount;
		this.origin_price = origin_price;
		this.sale_price = sale_price;
		this.discount_percent = discount_percent;
		this.user_id = user_id;
		this.category_id = category_id;
		this.img = img;
	}
	//get
	public String getCategory_name() {
		return category_name;
	}
	
	public String getOption() {
		return option;
	}
	public int getAmount() {
		return amount;
	}
	public int getId() {
		return id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public int getDiscount_percent() {
		return discount_percent;
	}
	public String getName() {
		return name;
	}
	public float getOrigin_price() {
		return origin_price;
	}
	public float getSale_price() {
		return sale_price;
	}
	public int getStatus() {
		return status;
	}
	public int getUser_id() {
		return user_id;
	}
	public String getImg() {
		return img;
	}
	//set
	public void setImg(String img) {
		this.img = img;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrigin_price(Float origin_price) {
		this.origin_price = origin_price;
	}
	public void setSale_price(Float sale_price) {
		this.sale_price = sale_price;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
