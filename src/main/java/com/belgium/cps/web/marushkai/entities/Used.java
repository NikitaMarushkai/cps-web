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

    @Column(name = "engine_hours")
    private String engine_hours;

    @Column(name = "separator_hours")
    private String separator_hours;

    @Column(name = "model")
    private String model;

    @Column(name = "location")
    private String location;

    @Column(name = "photo", length = 1024)
    private String photo;

    @Column(name = "type")
    private String type;

    @Column(name = "dealer_info", length = 1024)
    private String dealer_info;

    @Column(name = "dealer_logo", length = 1024)
    private String dealer_logo;
}
