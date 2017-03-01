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
    @Column(name = "big_logo_ru") private String big_logo_ru;
    @Column(name = "big_logo_en") private String big_logo_en;
    @Column(name = "big_logo_fr") private String big_logo_fr;
    @Column(name = "logo_description_ru") private String logo_description_ru;
    @Column(name = "logo_description_en") private String logo_description_en;
    @Column(name = "logo_description_fr") private String logo_description_fr;
    @Column(name = "description_header_ru") private String description_header_ru;
    @Column(name = "description_header_en") private String description_header_en;
    @Column(name = "description_header_fr") private String description_header_fr;
    @Column(name = "description_header_small_ru") private String description_header_small_ru;
    @Column(name = "description_header_small_en") private String description_header_small_en;
    @Column(name = "description_header_small_fr") private String description_header_small_fr;
    @Column(name = "description_ru") private String description_ru;
    @Column(name = "description_en") private String description_en;
    @Column(name = "description_fr") private String description_fr;


}
