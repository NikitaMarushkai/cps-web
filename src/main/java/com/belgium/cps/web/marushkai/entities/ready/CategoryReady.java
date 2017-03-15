package com.belgium.cps.web.marushkai.entities.ready;

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
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryReady {
    private int id;
    private String link;
    private String imglink;
    private String imgdescr;
    private String description;
    private String hovertext;
    private String category;
}
