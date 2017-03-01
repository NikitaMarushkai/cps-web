package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by unlim_000 on 02.03.2017.
 */
@Entity
@Table(name = "models")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Model {

    @Id
    @Column(name = "id") private int id;
    @Column(name = "model") private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private LandingPage page;

    public void setPage(LandingPage landingPage) {
        this.page = landingPage;
        if (!landingPage.getModels().contains(this)) {
            landingPage.getModels().add(this);
        }
    }
}
