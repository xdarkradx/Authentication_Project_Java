
public class Employee {
	String name;
	String role;
	String hashedpassword;
	
	public Employee() {
		name = null;
		role = null;
		hashedpassword = null;
	}
	
	public Employee(String name, String role, String hashedpassword) {
		this.name = name;
		this.role = role;
		this.hashedpassword = hashedpassword;
	}

	public String getName() {
		return this.name;
	}
	public String getRole() {
		return this.role;
	}
	public String getHashedPassword() {
		return this.hashedpassword;
	}
}
