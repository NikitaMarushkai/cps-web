package com.belgium.cps.web.marushkai.entities.forms;

import com.belgium.cps.web.marushkai.entities.LandingPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by unlim_000 on 12.06.2017.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelForm {
    private String model;
    private String description_ru;
    private String description_en;
    private String description_fr;
    private String photo1;
    private String photo2;
    private String brochure;
    private LandingPage page;
}
