package ntukhpi.semit.dde.webapphbn.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * Java POJO class for storing data about Employee
 * Correspond to table Employee in database
 *
 * POJO class must have default constructor, getters, setters
*/

@Entity
@Table(name = "employees")
public class Employee implements Comparable<Employee> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;  //Long!!! not long, not int
	@Column(name = "name", nullable=false, unique=true, length = 50)
	private String name;

	@Column(name = "pol", nullable=false)
	@ColumnDefault(value = "true")
	private boolean pol;

	//@Column(name = "age", columnDefinition = "value>=18 AND value<=70 defauit 18",nullable=false)
	//@ColumnDefault(value = "18")
	@Column(name="age", columnDefinition ="int not null DEFAULT 18, CONSTRAINT range_age CHECK (age>=18 AND age<=75)")
	// @ColumnDefault(value = "18")
	private int age;

	@Column(name = "salary", columnDefinition ="DOUBlE(10,2) not null DEFAULT 6500.00, CONSTRAINT int_salary CHECK (salary>=6500)")
	//@ColumnDefault(value = "6500")
	private Double salary;



//	//new 06-04-23
//	public Employee(String name, boolean pol, int age, Double salary, INN inn) {
//		this.name = name;
//		this.pol = pol;
//		this.age = age;
//		this.salary = salary;
//		this.inn = inn;
//	}
//
//	//new 06-04-23
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
//	private INN inn;
//	//new 06-04-23
//	public INN getInn() {
//		return inn;
//	}
//	//new 06-04-23
//	public void setInn(INN inn) {
//		this.inn = inn;
//	}


	public Employee(){

	}

	public Employee(Long id, String name, boolean pol, int age, Double salary) {
		this.id = id;
		this.name = name;
		this.pol = pol;
		this.age = age;
		this.salary = salary;
	}

	//Getter
	public long getId() {
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

	public Double getSalary() {
		return salary;
	}

	//Setter
	public void setId(Long id) {
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

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee: " +
				 id +
				" === " + name + ' ' +
				"(" + (pol?"m":"w") +
				", " + age +
				") - " + salary +
				 " UAH";
	}

	@Override
	public int compareTo(Employee o) {
		return this.name.compareTo(o.name);
	}


}