package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="team_cod", nullable = false, length = 16, unique = true)
    private String teamCod;

    @Enumerated(EnumType.STRING)
    @Column(name = "pl", nullable = false, length = 12)
    @Convert(converter = ProgramLanguageConverter.class)
    private ProgramLanguage pl;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "work_in_team", joinColumns = @JoinColumn(name = "id_team"))
//    @MapKeyJoinColumn(name = "id_empl")
//    @Column(name = "YearStartIn", nullable = true)
//    private Map<Employee, LocalDate> depsInst = new HashMap<>();

}
