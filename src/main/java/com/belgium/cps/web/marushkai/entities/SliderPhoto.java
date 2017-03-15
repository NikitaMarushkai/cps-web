package com.belgium.cps.web.marushkai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by unlim_000 on 02.03.2017.
 */
@Entity
@Table(name = "slider_photos")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class SliderPhoto {

    @Id
    @Column(name = "id") private int id;
    @Column(name = "photo") private String photo;
    @Column(name = "photo_tooltip")
    private String photo_tooltip;
    @Column(name = "photo_descr")
    private String photo_descr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private LandingPage page;

    public void setPage(LandingPage landingPage) {
        this.page = landingPage;
        if (!landingPage.getSliderPhotos().contains(this)) {
            landingPage.getSliderPhotos().add(this);
        }
    }
}
