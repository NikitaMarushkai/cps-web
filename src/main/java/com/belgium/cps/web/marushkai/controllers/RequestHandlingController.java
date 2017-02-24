package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import com.belgium.cps.web.marushkai.services.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by unlim_000 on 24.02.2017.
 */

@Controller
public class RequestHandlingController {

    private MailClient mailClient;

    @Autowired
    private RequestHandlingController(MailClient mailClient){
        this.mailClient = mailClient;
    }

    @PostMapping("/mail")
    public String processMailForm(Model model, @ModelAttribute ContactForm contactForm){
        mailClient.prepareAndSend(contactForm);
        model.addAttribute("contactform", new ContactForm());
        return "landing-page";
    }
}
