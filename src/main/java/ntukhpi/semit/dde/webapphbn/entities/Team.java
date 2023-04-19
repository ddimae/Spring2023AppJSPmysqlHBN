package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "team_cod", nullable = false, length = 16, unique = true)
    @NonNull
    @ToString.Include
    private String teamCod;

    @Enumerated(EnumType.STRING)
    @Column(name = "pl", nullable = false, length = 12)
    @Convert(converter = ProgramLanguageConverter.class)
    @NonNull
    @ToString.Include
    private ProgramLanguage pl;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "work_in_team", joinColumns = @JoinColumn(name = "id_team"))
    @MapKeyJoinColumn(name = "id_empl")
    private Map<Employee, WorkInterval> employeesOfTeam = new HashMap<>();

    //For Map<Employee, WorkInterval>
    public Map<Employee, WorkInterval> getEmployeesTeam() {
        return Collections.unmodifiableMap(employeesOfTeam);
    }

    public void addEmployee(Employee employee, WorkInterval workInterval) {
        employeesOfTeam.put(employee, workInterval);
    }

    public void delEmployee(Employee employee) {
        employeesOfTeam.remove(employee);
    }

}
