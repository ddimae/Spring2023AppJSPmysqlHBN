package ntukhpi.semit.dde.webapphbn.model;
/**
 * Java POJO class for storing data about Employee
 * Correspond to table Employee in database
 *
 * POJO class must have default constructor, getters, setters
*/
public class EmployeeBase implements Comparable<EmployeeBase> {

	private int id;
	private String name;
	private boolean pol;
	private int age;
	private double salary;

	public EmployeeBase(){

	}

	public EmployeeBase(int id, String name, boolean pol, int age, double salary) {
		this.id = id;
		this.name = name;
		this.pol = pol;
		this.age = age;
		this.salary = salary;
	}

	//Getter
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isPol() {
		return pol;
	}

	public int getAge() {
		return age;
	}

	public double getSalary() {
		return salary;
	}

	//Setter
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPol(boolean pol) {
		this.pol = pol;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee: " +
				 id +
				" === " + name + '\'' +
				"(" + (pol?"m":"w") +
				", " + age +
				") - " + salary +
				 " UAH";
	}

	@Override
	public int compareTo(EmployeeBase o) {
		return this.name.compareTo(o.name);
	}
}