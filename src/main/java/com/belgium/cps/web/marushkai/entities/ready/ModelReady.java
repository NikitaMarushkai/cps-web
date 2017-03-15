package com.belgium.cps.web.marushkai.entities.ready;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by unlim_000 on 14.03.2017.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelReady {

    private int id;
    private String model;
    private String description;
    private String photo1;
    private String photo2;
    private String brochure;
}
