package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.Context;
import com.belgium.cps.web.marushkai.repositories.ContextRepository;
import com.belgium.cps.web.marushkai.repositories.UsedRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class UsedParserServiceImpl implements UsedParserService {

    private UsedRepository usedRepository;
    private ContextRepository contextRepository;
//    private Document baseDoc;
    //Checks if connected successfully
//    private int status;

    @Autowired
    public UsedParserServiceImpl(UsedRepository usedRepository, ContextRepository contextRepository) {
        this.usedRepository = usedRepository;
        this.contextRepository = contextRepository;
//        try {
//            this.baseDoc = Jsoup.connect(contextRepository.findByKey("used_connection").getValue())
//                    .get();
//            this.status = 1;
//        } catch (IOException e) {
//            this.status = 0;
//        }
    }


    private Document renderPage() {
        String url = contextRepository.findByKey("used_connection").getValue() + "/tractors";
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability("phantomjs.binary.path", "C:/Programming/CPS/phantomjs-2.1.1-windows/bin/phantomjs.exe");
//        capabilities.setCapability("phantomjs.page.settings.resourceTimeout", "15000");
        WebDriver ghostDriver = new PhantomJSDriver(capabilities);
        try {
            ghostDriver.get(url);
            Thread.sleep(30000);
            return Jsoup.parse(ghostDriver.getPageSource());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            ghostDriver.quit();
        }
    }

    @Override
    public void parse() {
        System.out.println(this.renderPage().body().html());
    }
}
