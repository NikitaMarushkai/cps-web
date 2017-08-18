package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "used")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Used {
    @GenericGenerator(
            name = "usedSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "USED_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "usedSequenceGenerator")
    private long id;

    @Column(name = "year")
    private String year;

    @Column(name = "hours")
    private String hours;

    @Column(name = "model")
    private String model;

    @Column(name = "location")
    private String location;

    @Column(name = "photo")
    private String photo;
}
