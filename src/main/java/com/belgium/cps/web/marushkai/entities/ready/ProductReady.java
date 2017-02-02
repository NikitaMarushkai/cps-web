package com.belgium.cps.web.marushkai.entities.ready;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by unlim_000 on 01.02.2017.
 */
@Getter @Setter @AllArgsConstructor
public class ProductReady {
    int id;
    String image;
    String imageDescr;
    String header;
    String description;
    int price;
}
