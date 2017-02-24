package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by unlim_000 on 24.02.2017.
 */

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    MailContentBuilder(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public String build(ContactForm contactForm){
        Context context = new Context();
        context.setVariable("email", contactForm.getEmail());
        context.setVariable("subject", contactForm.getSubject());
        context.setVariable("name", contactForm.getName());
        context.setVariable("message", contactForm.getMessage());
        return templateEngine.process("mailTemplate", context);
    }
}
