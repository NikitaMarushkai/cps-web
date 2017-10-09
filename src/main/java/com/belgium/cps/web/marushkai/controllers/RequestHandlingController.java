package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import com.belgium.cps.web.marushkai.repositories.ContextRepository;
import com.belgium.cps.web.marushkai.services.MailClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * Created by unlim_000 on 24.02.2017.
 */

@Controller
public class RequestHandlingController {

    private MailClient mailClient;
    private ContextRepository contextRepository;

    @Autowired
    private RequestHandlingController(MailClient mailClient, ContextRepository contextRepository) {
        this.mailClient = mailClient;
        this.contextRepository = contextRepository;
    }

    @PostMapping("/mail")
    public ModelAndView processMailForm(Model model, @ModelAttribute ContactForm contactForm) {
        mailClient.prepareAndSend(contactForm);
        model.addAttribute("contactform", new ContactForm());
        return new ModelAndView("redirect:" + contactForm.getReturnUrl());
    }

    @GetMapping("/download/{file}")
    public void downloadFile(@PathVariable String file, HttpServletResponse response) {
        String src = contextRepository.findByKey("brochures").getValue().concat(FileSystems.getDefault().getSeparator()
                + file + ".pdf");
        try {
            response.setContentType("application/pdf");
            InputStream is = new FileInputStream(src);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
