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
 * Created by unlim_000 on 01.02.2017.
 */

@Entity
@Table(name = "product")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id @Column(name = "id") private int id;
    @Column(name = "image") private String image;
    @Column(name = "imagedescr_ru") private String imageDescr_ru;
    @Column(name = "imagedescr_en") private String imageDescr_en;
    @Column(name = "imagedescr_fr") private String imageDescr_fr;
    @Column(name = "header_ru") private String header_ru;
    @Column(name = "header_en") private String header_en;
    @Column(name = "header_fr") private String header_fr;
    @Column(name = "description_ru") private String description_ru;
    @Column(name = "description_en") private String description_en;
    @Column(name = "description_fr") private String description_fr;
//    @Column(name = "price") private int price;

    public String getImageDescr(String lang){
        String langReturn = imageDescr_en;
        switch (lang){
            case "ru":
                langReturn = imageDescr_ru;
                break;
            case "en":
                langReturn = imageDescr_en;
                break;
            case "fr":
                langReturn = imageDescr_fr;
                break;
        }
        return langReturn;
    }

    public String getHeader(String lang){
        String langReturn = header_en;
        switch (lang){
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
