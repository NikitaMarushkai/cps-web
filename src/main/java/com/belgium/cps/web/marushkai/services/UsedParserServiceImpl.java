package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.Context;
import com.belgium.cps.web.marushkai.repositories.ContextRepository;
import com.belgium.cps.web.marushkai.repositories.UsedRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UsedParserServiceImpl implements UsedParserService {

    private UsedRepository usedRepository;
    private ContextRepository contextRepository;
    //Checks if connected successfully
//    private int status;

    @Autowired
    public UsedParserServiceImpl(UsedRepository usedRepository, ContextRepository contextRepository) {
        this.usedRepository = usedRepository;
        this.contextRepository = contextRepository;
    }


    private Document renderPage(String category) throws InterruptedException {
        String url = contextRepository.findByKey("used_connection").getValue() + category;
//        String url = contextRepository.findByKey("used_connection").getValue() + "/tractors";
//        String url = contextRepository.findByKey("used_connection").getValue() + "/combines";
//        String url = contextRepository.findByKey("used_connection").getValue() + "/forage-harvesters";
//        String url = contextRepository.findByKey("used_connection").getValue() + "/balers";
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability("phantomjs.binary.path", contextRepository.findByKey("phantomjs_bin").getValue());
        WebDriver ghostDriver = new PhantomJSDriver(capabilities);
        try {
            ghostDriver.get(url);
            Thread.sleep(30000);
            return Jsoup.parse(ghostDriver.getPageSource());
        } finally {
            ghostDriver.quit();
        }
    }

    @Override
    public void parse() throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            Document doc = this.renderPage("/combines?layout=list&p=" + i);
            System.out.println(Integer.parseInt(doc.body()
                    .select("div.result-counter.pull-right > div").text().substring(7)));
            Elements items = doc.body().select("li.item");
            items.forEach(item -> {
                System.out.println("year: " + item.select("div.feature > div > span.info > div").text());
                System.out.println("model: " + item.select("div.hidden-xs.col-sm-6.col-md-5.title-container > h3 > a").text());
                System.out.println("photo: " + item.select("div.preview-image.col-sm-3.cursor-pointer > a > div").attr("style")
                        .replace("background-image: url(", "").replace(");", ""));
                String location = item.select("p.icons-line.ng-binding").last().text();
                if (location == null || location.isEmpty() || location.equals(" ")) {
                    System.out.println("location: on request");
                } else {
                    System.out.println("location: " + location);
                }
                Map<String, String> features = new HashMap<>();
                item.select("div.feature-wrapper").forEach(feature -> {
                    if (feature.select("span.feature-label").first().text().contains("Engine Hours")) {
                        features.put("engineHours", feature.select("div.unitFeature.ng-isolate-scope").first().text());
                    }

                    if (feature.select("span.feature-label").first().text().contains("Separator hours")) {
                        features.put("separatorHours", feature.select("div.unitFeature.ng-isolate-scope").first().text());
                    }
                });
                if (features.containsKey("separatorHours")) {
                    System.out.println("sep hours: " + features.get("separatorHours"));
                }
                if (features.containsKey("engineHours")) {
                    System.out.println("engine hours: " + features.get("engineHours"));
                } else {
                    System.out.println("engine hours: on request");
                }
                System.out.println("------------------------");
            });
        }
    }
}
