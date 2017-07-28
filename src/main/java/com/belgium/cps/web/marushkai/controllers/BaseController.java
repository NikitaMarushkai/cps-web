package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.Category;
import com.belgium.cps.web.marushkai.entities.LandingPage;
import com.belgium.cps.web.marushkai.entities.Product;
import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import com.belgium.cps.web.marushkai.entities.ready.CategoryReady;
import com.belgium.cps.web.marushkai.entities.ready.LandingPageReady;
import com.belgium.cps.web.marushkai.entities.ready.ModelReady;
import com.belgium.cps.web.marushkai.entities.ready.ProductReady;
import com.belgium.cps.web.marushkai.repositories.CategoryRepository;
import com.belgium.cps.web.marushkai.repositories.LandingPageRepository;
import com.belgium.cps.web.marushkai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by unlim_000 on 29.01.2017.
 */

@Controller
public class BaseController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LandingPageRepository landingPageRepository;

    //LocaleContextHolder
    @RequestMapping("/")
    public String index(Model model) {
        String currLang = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        // Getting Randomly 3 products
//        Random random = new Random();
//        int productNumber = (int) productRepository.count();
//        int k = random.nextInt(productNumber) + 1;
//        ArrayList<ProductReady> products = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Product product = productRepository.findOne(k);
//            ProductReady productReady = new ProductReady(product.getId(), product.getImage(), product.getImageDescr(currLang),
//                    product.getHeader(currLang), product.getDescription(currLang), product.getPrice());
//            products.add(productReady);
//            int newK = random.nextInt(productNumber) + 1;
//            while (newK == k) {
//                newK = random.nextInt(productNumber) + 1;
//            }
//            k = newK;
//        }
        ArrayList<ProductReady> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(new ProductReady(product.getId(), product.getImage(),
                product.getImageDescr(currLang), product.getHeader(currLang), product.getDescription(currLang), product.getReference())));
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/category/{cat}/")
    public String getCategory(@PathVariable String cat, Model model) {
        ArrayList<CategoryReady> categories = new ArrayList<>();
        String currLang = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        for (Category category : categoryRepository.findByCategory(cat)) {
            categories.add(new CategoryReady(category.getId(), category.getLink(), category.getImglink(),
                    category.getImgdescr(), category.getDescription(currLang), category.getHovertext(), category.getCategory(), category.getHeader(currLang),
                    category.getBackground()));
        }
        model.addAttribute("items", categories);
        model.addAttribute("header", categories.get(0).getHeader());
        model.addAttribute("imagepath", categories.get(0).getBackground());
        return "category";
    }

    @RequestMapping("/product/{type}")
    public String getLp(@PathVariable String type, Model model) {
        model.addAttribute("contactform", new ContactForm());
        String currLang = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        LandingPage landing = landingPageRepository.findByType(type);
        ArrayList<ModelReady> models = new ArrayList<>();
        landing.getModels().forEach(model1 -> models.add(new ModelReady(model1.getId(),
                model1.getModel(), model1.getDescription(currLang), model1.getPhoto1(), model1.getPhoto2(), model1.getBrochure())));
        LandingPageReady landingPage = new LandingPageReady(landing.getId(), landing.getMainHeader(currLang),
                landing.getLogoDescription(currLang), landing.getDescriptionHeader(currLang), landing.getPhotoHeader(currLang), landing.getPhotoDescription(currLang),
                landing.getVideoHeader(currLang), landing.getFirst_background(), landing.getSecond_background(), landing.getThird_background(),
                landing.getFourth_background(), landing.getVideo_ref(), landing.getDescr_photo_1(), landing.getDescr_photo_2(), landing.getDescr_photo_3(), landing.getType(),
                landing.getTitle(), landing.getMetaDescr(currLang), models, landing.getSliderPhotos());
        model.addAttribute("imagepath1", landingPage.getFirst_background());
        model.addAttribute("imagepath2", landingPage.getSecond_background());
        model.addAttribute("imagepath3", landingPage.getThird_background());
        model.addAttribute("imagepath4", landingPage.getFourth_background());
        model.addAttribute("title", landingPage.getTitle());
        model.addAttribute("return", type);
        model.addAttribute("landingpage", landingPage);
        return "landing-page";
    }

    @RequestMapping("/catalogue/")
    public String getCatalogue(Model model) {
        model.addAttribute("contactform", new ContactForm());
        return "catalogue";
    }

    @RequestMapping("/contacts/")
    public String getContacts(Model model) {
        model.addAttribute("contactform", new ContactForm());
        return "contacts";
    }

    @RequestMapping("/about_us/")
    public String aboutUs(Model model) {
        return "about_us";
    }

    @RequestMapping("/older/")
    public String getOlder(Model model) {
        return "older";
    }
}
