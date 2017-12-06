package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.Context;
import com.belgium.cps.web.marushkai.entities.Used;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UsedParserServiceImpl implements UsedParserService {

    private UsedRepository usedRepository;
    private ContextRepository contextRepository;


    @Autowired
    public UsedParserServiceImpl(UsedRepository usedRepository, ContextRepository contextRepository) {
        this.usedRepository = usedRepository;
        this.contextRepository = contextRepository;
    }


    private Document renderPage(String category) throws InterruptedException {
        String url = contextRepository.findByKey("used_connection").getValue() + category;
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability("phantomjs.binary.path", contextRepository.findByKey("phantomjs_bin").getValue());
        WebDriver ghostDriver = new PhantomJSDriver(capabilities);
        try {
            ghostDriver.get(url);
            Thread.sleep(20000);
            return Jsoup.parse(ghostDriver.getPageSource());
        } finally {
            ghostDriver.quit();
        }
    }

    @Override
    public void parse() {
        usedRepository.deleteAll();
        String[] categories = {"combines", "tractors", "forage-harvesters",
                "balers"};
        Stream<String> categoriesStream = Arrays.stream(categories);
        categoriesStream.parallel().forEach(cat -> {
            Document doc = null;
            int totalPages = 0;
            try {
                doc = this.renderPage("/" + cat + "?layout=list");
                int totalNumbers = Integer.parseInt(doc.body()
                        .select("div.result-counter.pull-right > div").text().substring(7).
                                replaceAll(",", ""));
                totalPages = (int) Math.ceil(totalNumbers / 10);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("No data of total items");
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception in renderPage method, message: " + e.getMessage());
                e.printStackTrace();
            }
            for (int i = 1; i <= totalPages; i++) {
                try {
                    doc = this.renderPage("/" + cat + "?layout=list&p=" + i);
                } catch (InterruptedException e) {
                    System.err.println("Interrupted exception in renderPage method, message: " + e.getMessage());
                    e.printStackTrace();
                }
                Elements items = doc.body().select("li.item");
                items.forEach(item -> {
                    Used usedEntity = new Used();
                    try {
                        usedEntity.setYear(item.select("div.feature > div > span.info > div").text());
                    } catch (NullPointerException e) {
                        usedEntity.setYear("On request");
                    }
                    usedEntity.setModel(item.select("div.hidden-xs.col-sm-6.col-md-5.title-container > h3 > a").text());
                    usedEntity.setPhoto(item.select("div.preview-image.col-sm-3.cursor-pointer > a > div").attr("style")
                            .replace("background-image: url(", "").replace(");", ""));
                    String location = null;
                    String fullDealerInfo = null;
                    String dealerLogo = null;
                    try {
                        location = item.select("p.icons-line.ng-binding").last().text();
                        fullDealerInfo = item.select("p.icons-line.ng-binding").stream().map(Element::text)
                                .collect(Collectors.joining(", "));
                    } catch (NullPointerException e) {
                        location = "On request";
                        fullDealerInfo = "Not found";
                    }
                    try {
                        dealerLogo = item.select("div.dealer-logo > a > img").last().attr("src");
                    } catch (NullPointerException e) {
                        dealerLogo = "No dealer image";
                    }
                    if (dealerLogo == null || dealerLogo.isEmpty() || dealerLogo.equals(" ")) {
                        usedEntity.setDealer_logo("No dealer image");
                    } else {
                        usedEntity.setDealer_logo(dealerLogo);
                    }
                    if (fullDealerInfo == null || fullDealerInfo.isEmpty() || fullDealerInfo.equals(" ")) {
                        usedEntity.setDealer_info("Not found");
                    } else {
                        usedEntity.setDealer_info(fullDealerInfo);
                    }
                    if (location == null || location.isEmpty() || location.equals(" ")) {
                        usedEntity.setLocation("On request");
                    } else {
                        usedEntity.setLocation(location);
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
                        usedEntity.setSeparator_hours(features.get("separatorHours"));
                    }
                    usedEntity.setEngine_hours(features.getOrDefault("engineHours", "On request"));
                    usedEntity.setType(cat);
                    usedRepository.save(usedEntity);
                });
            }
        });
    }
}
