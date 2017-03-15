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
    @Column(name = "description_ru")
    private String description_ru;
    @Column(name = "description_en")
    private String description_en;
    @Column(name = "description_fr")
    private String description_fr;
    @Column(name = "photo1")
    private String photo1;
    @Column(name = "photo2")
    private String photo2;
    @Column(name = "brochure")
    private String brochure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private LandingPage page;

    public void setPage(LandingPage landingPage) {
        this.page = landingPage;
        if (!landingPage.getModels().contains(this)) {
            landingPage.getModels().add(this);
        }
    }

    public String getDescription(String lang) {
        String langReturn = description_en;
        switch (lang) {
            case "ru":
                langReturn = description_ru;
                break;
            case "en":
                langReturn = description_en;
                break;
            case "fr":
                langReturn = description_fr;
                break;
        }
        return langReturn;
    }
}
