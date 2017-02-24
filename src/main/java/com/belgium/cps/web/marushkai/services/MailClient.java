package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created by unlim_000 on 24.02.2017.
 */

@Service
public class MailClient {

    private JavaMailSender mailSender;
    private MailContentBuilder contentBuilder;

    @Autowired
    public MailClient(JavaMailSender mailSender, MailContentBuilder contentBuilder){
        this.mailSender = mailSender;
        this.contentBuilder = contentBuilder;
    }

    public void prepareAndSend(ContactForm contactForm){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(contactForm.getEmail());
            messageHelper.setTo("unlimited54321@mail.ru");
            messageHelper.setSubject("Parts-on-line request " + contactForm.getSubject());
            String content = contentBuilder.build(contactForm);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e){
            e.printStackTrace();
        }
    }
}
