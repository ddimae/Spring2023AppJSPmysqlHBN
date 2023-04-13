package ntukhpi.semit.dde.webapphbn.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="phone_number", nullable=false, length=10,unique = true)
    private String phoneNumber;
    //https://www.javaguides.net/2018/11/hibernate-5-enum-type-mapping-example.html

    @Enumerated(EnumType.STRING)
    //HOME, MOBILE, OFFICE
    @Column(name = "number_type", nullable = false, length = 10)
    @Convert(converter = PhoneNumberTypeConverter.class)
    //@ColumnDefault(value="MOBILE")
    // https://ask-dev.ru/info/36428/setting-default-values-for-columns-in-jpa
    // How to set values by default ?!
    private PhoneNumberType phoneNumberType;

    @Column(name = "active",nullable = false)
    @ColumnDefault(value="TRUE")
    private boolean activePhone = true;

    //КАК Сдеалать так, чтобЫ только один телефон был основным = финансовым???!!!
    @Column(name = "main",nullable = false)
    @ColumnDefault(value="FALSE")
    private boolean mainPhone; //main

    //https://www.javaguides.net/2022/02/spring-data-jpa-one-to-many-bidirectional-mapping.html
    // default fetch type for ManyToOne: EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    //updatable = false <==== Not take part in UPDATE ====> you can not change owner of this number. only delete
    @JoinColumn(name = "employee_id",nullable = false,updatable = false)
    private Employee owner;


}
