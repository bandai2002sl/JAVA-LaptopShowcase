package core;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Laptop extends Product {
	private String cpu;
	private String ram;
	private String screen;
	private String graphicsCard;
	private String os;
	public Laptop() {

	}
	public Laptop(String option) {
		super(option);
	}
	public Laptop(int id, String name,int amount, float origin_price,float sale_price,int discount_percent,int user_id,int category_id,int status,String option,String img) {
		super(id, name, amount, origin_price, sale_price, discount_percent, user_id, category_id, status, option,img);
	}
	public Laptop( String name,int amount, float origin_price,float sale_price,int discount_percent,int user_id,int category_id,int status,String option,String img) {
		super(name, amount, origin_price, sale_price, discount_percent, user_id, category_id, status, option,img);
	}
	public Laptop( String name,int amount, float origin_price,float sale_price,int discount_percent,int user_id,int category_id,int status,String img) {
		super(name, amount, origin_price, sale_price, discount_percent, user_id, category_id, status,img);
	}
	
	@Override
	public String getOption() {
		return super.getOption();
	}
	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return super.getAmount();
	}
	@Override
	public int getCategory_id() {
		// TODO Auto-generated method stub
		return super.getCategory_id();
	}
	@Override
	public int getDiscount_percent() {
		// TODO Auto-generated method stub
		return super.getDiscount_percent();
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	@Override
	public float getOrigin_price() {
		// TODO Auto-generated method stub
		return super.getOrigin_price();
	}
	@Override
	public float getSale_price() {
		// TODO Auto-generated method stub
		return super.getSale_price();
	}
	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return super.getStatus();
	}
	@Override
	public int getUser_id() {
		// TODO Auto-generated method stub
		return super.getUser_id();
	}
	public String getCpu() {
		return cpu;
	}
	public String getGraphicsCard() {
		return graphicsCard;
	}
	public String getOs() {
		return os;
	}
	public String getRam() {
		return ram;
	}
	public String getScreen() {
		return screen;
	}
	
	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		super.setAmount(amount);
	}
	@Override
	public void setCategory_id(int category_id) {
		// TODO Auto-generated method stub
		super.setCategory_id(category_id);
	}
	@Override
	public void setDiscount_percent(int discount_percent) {
		// TODO Auto-generated method stub
		super.setDiscount_percent(discount_percent);
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}
	@Override
	public void setOption(String option) {
		// TODO Auto-generated method stub
		super.setOption(option);
	}
	@Override
	public void setOrigin_price(Float origin_price) {
		// TODO Auto-generated method stub
		super.setOrigin_price(origin_price);
	}
	@Override
	public void setSale_price(Float sale_price) {
		// TODO Auto-generated method stub
		super.setSale_price(sale_price);
	}
	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		super.setStatus(status);
	}

	@Override
	public void setUser_id(int user_id) {
		// TODO Auto-generated method stub
		super.setUser_id(user_id);
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setGraphicsCard(String graphicsCard) {
		this.graphicsCard = graphicsCard;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public void optionToAttribute() {
		 Object obj = JSONValue.parse(getOption());
	        JSONObject jsonObject = (JSONObject) obj;
	        setCpu((String) jsonObject.get("cpu"));
	        setRam((String) jsonObject.get("ram"));
	        setGraphicsCard((String) jsonObject.get("graphicsCard"));
	        setOs((String) jsonObject.get("os"));
	        setScreen((String) jsonObject.get("screen"));
	}
	public void optionToString() {
		JSONObject obj = new JSONObject();
	        obj.put("cpu", getCpu());
	        obj.put("ram", getRam());
	        obj.put("os", getOs());
	        obj.put("graphicsCard", getGraphicsCard());
	        obj.put("screen", getScreen());
        setOption(obj.toJSONString());
	}
}
