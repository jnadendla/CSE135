package db;

public class Category {
	public int id;
	public String name;
	public String description;

	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
