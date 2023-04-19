package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkInterval {

    @Column(name = "work_start",nullable = false)
    private LocalDate workStart;
    @Column(name = "work_end")
    private LocalDate workEnd;

}
