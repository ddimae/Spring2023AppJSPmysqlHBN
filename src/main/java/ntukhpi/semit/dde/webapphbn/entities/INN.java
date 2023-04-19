package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="inns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class INN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inn")
    private Long id;

    @Column(name = "number", unique = true, columnDefinition ="bigint not null DEFAULT 1000000000, CONSTRAINT min_number CHECK (number>=1000000000)")   //
    private Long number;

    @Column(nullable=false)
    //@ColumnDefault(value=" ") //<===== Mistake!!!!
    private String issued;

    @Column(name = "date_issued",nullable = false)
    private LocalDate dateIssued;

    @OneToOne
    @JoinColumn(name = "id_employee",nullable = false,updatable = false,unique = true)
    private Employee owner;



}
