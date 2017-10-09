package com.belgium.cps.web.marushkai.entities.ready;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by unlim_000 on 01.02.2017.
 */
@Getter @Setter @AllArgsConstructor
public class ProductReady {
    private int id;
    private String image;
    private String imageDescr;
    private String header;
    private String description;
    private String reference;
//    private int price;
}
