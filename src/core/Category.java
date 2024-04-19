package core;

import java.util.Date;

public class Category {
	private int id;
	private String name;
	private Date created_at;
	private Date updated_at;
	public Category(String name) {
		this.name = name;
	}
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Category(String name, Date created_at, Date updated_at) {
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public Category(int id, String name, Date created_at, Date updated_at) {
		this.id =id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Tên: "+this.name +"\nNgày tạo: "+this.created_at;
	}
}
