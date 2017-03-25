package com.belgium.cps.web.marushkai.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by unlim_000 on 28.02.2017.
 */
@Entity
@Table(name = "landingpages")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LandingPage {

    @Id
    @Column(name = "id") private int id;
    @Column(name = "main_header_ru") private String main_header_ru;
    @Column(name = "main_header_en") private String main_header_en;
    @Column(name = "main_header_fr") private String main_header_fr;
    @Column(name = "logo_description_ru") private String logo_description_ru;
    @Column(name = "logo_description_en") private String logo_description_en;
    @Column(name = "logo_description_fr") private String logo_description_fr;
    @Column(name = "description_header_ru") private String description_header_ru;
    @Column(name = "description_header_en") private String description_header_en;
    @Column(name = "description_header_fr") private String description_header_fr;
    @Column(name = "photo_header_ru") private String photo_header_ru;
    @Column(name = "photo_header_en") private String photo_header_en;
    @Column(name = "photo_header_fr") private String photo_header_fr;
    @Column(name = "photo_description_ru") private String photo_description_ru;
    @Column(name = "photo_description_en") private String photo_description_en;
    @Column(name = "photo_description_fr") private String photo_description_fr;
    @Column(name = "video_header_ru") private String video_header_ru;
    @Column(name = "video_header_en") private String video_header_en;
    @Column(name = "video_header_fr") private String video_header_fr;
    @Column(name = "first_background") private String first_background;
    @Column(name = "second_background") private String second_background;
    @Column(name = "third_background") private String third_background;
    @Column(name = "fourth_background") private String fourth_background;
    @Column(name = "video_ref") private String video_ref;
    @Column(name = "descr_photo_1") private String descr_photo_1;
    @Column(name = "descr_photo_2") private String descr_photo_2;
    @Column(name = "descr_photo_3")
    private String descr_photo_3;
    @Column(name = "type")
    private String type;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "page")
    private List<Model> models;

    @OneToMany(mappedBy = "page")
    private List<SliderPhoto> sliderPhotos;

    public void addModel(Model model) {
        this.models.add(model);
        if (model.getPage() != this) {
            model.setPage(this);
        }
    }

    public void addPhoto(SliderPhoto photo) {
        this.sliderPhotos.add(photo);
        if (photo.getPage() != this) {
            photo.setPage(this);
        }
    }

    public String getMainHeader(String lang){
        String langReturn = main_header_en;
        switch (lang){
            case "ru":
                langReturn = main_header_ru;
                break;
            case "en":
                langReturn = main_header_en;
                break;
            case "fr":
                langReturn = main_header_fr;
                break;
        }
        return langReturn;
    }

    public String getLogoDescription(String lang){
        String langReturn = logo_description_en;
        switch (lang){
            case "ru":
                langReturn = logo_description_ru;
                break;
            case "en":
                langReturn = logo_description_en;
                break;
            case "fr":
                langReturn = logo_description_fr;
                break;
        }
        return langReturn;
    }

    public String getDescriptionHeader(String lang){
        String langReturn = description_header_en;
        switch (lang){
            case "ru":
                langReturn = description_header_ru;
                break;
            case "en":
                langReturn = description_header_en;
                break;
            case "fr":
                langReturn = description_header_fr;
                break;
        }
        return langReturn;
    }

    public String getPhotoHeader(String lang){
        String langReturn = photo_header_en;
        switch (lang){
            case "ru":
                langReturn = photo_header_ru;
                break;
            case "en":
                langReturn = photo_header_en;
                break;
            case "fr":
                langReturn = photo_header_fr;
                break;
        }
        return langReturn;
    }

    public String getPhotoDescription(String lang){
        String langReturn = photo_description_en;
        switch (lang){
            case "ru":
                langReturn = photo_description_ru;
                break;
            case "en":
                langReturn = photo_description_en;
                break;
            case "fr":
                langReturn = photo_description_fr;
                break;
        }
        return langReturn;
    }

    public String getVideoHeader(String lang){
        String langReturn = video_header_en;
        switch (lang){
            case "ru":
                langReturn = video_header_ru;
                break;
            case "en":
                langReturn = video_header_en;
                break;
            case "fr":
                langReturn = video_header_fr;
                break;
        }
        return langReturn;
    }
}
