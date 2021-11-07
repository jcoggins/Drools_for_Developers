package inference;

public class Customer {
	private Person emp;

	public Customer(Person emp) {
		this.emp = emp;
	}
	
	public Person getEmp() {
		return emp;
	}

	public void setEmp(Person emp) {
		this.emp = emp;
	}	
}
