package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "context")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Context {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "key", length = 2048)
    private String key;
    @Column(name = "value", length = 2048)
    private String value;
}
