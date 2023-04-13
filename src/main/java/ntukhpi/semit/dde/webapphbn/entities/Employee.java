package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Java POJO class for storing data about Employee
 * Correspond to table Employee in database
 * <p>
 * POJO class must have default constructor, getters, setters
 */

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Comparable<Employee> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  //Long!!! not long, not int
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "pol", nullable = false)
    @ColumnDefault(value = "true")
    private boolean pol;

    //@Column(name = "age", columnDefinition = "value>=18 AND value<=70 defauit 18",nullable=false)
    //@ColumnDefault(value = "18")
    @Column(name = "age", columnDefinition = "int not null DEFAULT 18, CONSTRAINT range_age CHECK (age>=18 AND age<=75)")
    // @ColumnDefault(value = "18")
    private int age;

    @Column(name = "salary", columnDefinition = "DOUBlE(10,2) not null DEFAULT 6500.00, CONSTRAINT int_salary CHECK (salary>=6500)")
    //@ColumnDefault(value = "6500")
    private Double salary;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name = "work_in_team", joinColumns = @JoinColumn(name = "id_empl"))
//	@MapKeyJoinColumn(name = "id_inst")
//	@Column(name = "YearStartIn", nullable = true)
//	private Map<Team, LocalDate> instsOfDep = new HashMap<>();


    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }


}