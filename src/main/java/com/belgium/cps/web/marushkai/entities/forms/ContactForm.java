package com.belgium.cps.web.marushkai.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by unlim_000 on 24.02.2017.
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ContactForm {

    private String name;
    private String email;
    private String subject;
    private String message;
    private String categoryName;
    private String returnUrl;
    private String mail;

}
