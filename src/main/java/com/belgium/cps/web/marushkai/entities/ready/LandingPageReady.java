package com.belgium.cps.web.marushkai.entities.ready;

import com.belgium.cps.web.marushkai.entities.Model;
import com.belgium.cps.web.marushkai.entities.SliderPhoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by unlim_000 on 02.03.2017.
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LandingPageReady {
    private int id;
    private String main_header;
    private String logo_description;
    private String description_header;
    private String photo_header;
    private String photo_description;
    private String video_header;
    private String first_background;
    private String second_background;
    private String third_background;
    private String fourth_background;
    private String video_ref;
    private String descr_photo_1;
    private String descr_photo_2;
    private String descr_photo_3;
    private String type;
    private String title;
    private String meta_descr;
    private List<ModelReady> models;
    private List<SliderPhoto> sliderPhotos;
}
