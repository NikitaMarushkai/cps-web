package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by unlim_000 on 11.02.2017.
 */
@Entity
@Table(name = "category")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Category {
    @Id
    @Column(name = "id") private int id;
    @Column(name = "link") private String link;
    @Column(name = "imglink") private String imglink;
    @Column(name = "imgdescr") private String imgdescr;
    @Column(name = "description_en") private String description_en;
    @Column(name = "description_ru") private String description_ru;
    @Column(name = "description_fr") private String description_fr;
    @Column(name = "hovertext") private String hovertext;
    @Column(name = "category")
    private String category;
    @Column(name = "header_ru")
    private String header_ru;
    @Column(name = "header_en")
    private String header_en;
    @Column(name = "header_fr")
    private String header_fr;

    public String getHeader(String lang) {
        String langReturn = header_en;
        switch (lang) {
            case "ru":
                langReturn = header_ru;
                break;
            case "en":
                langReturn = header_en;
                break;
            case "fr":
                langReturn = header_fr;
                break;
        }
        return langReturn;
    }

    public String getDescription(String lang){
        String langReturn = description_en;
        switch (lang){
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
