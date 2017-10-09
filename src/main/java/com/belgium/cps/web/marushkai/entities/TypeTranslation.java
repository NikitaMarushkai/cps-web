package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "type_translation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeTranslation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @Column(name = "type_en")
    private String type_en;

    @Column(name = "type_ru")
    private String type_ru;

    @Column(name = "type_fr")
    private String type_fr;


    public String getTypeTranslated(String lang) {
        String langReturn = type_en;
        switch (lang) {
            case "ru":
                langReturn = type_ru;
                break;
            case "en":
                langReturn = type_en;
                break;
            case "fr":
                langReturn = type_fr;
                break;
        }
        return langReturn;
    }
}
