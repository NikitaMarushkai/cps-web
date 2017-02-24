package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.Category;
import com.belgium.cps.web.marushkai.entities.Product;
import com.belgium.cps.web.marushkai.entities.forms.ContactForm;
import com.belgium.cps.web.marushkai.entities.ready.CategoryReady;
import com.belgium.cps.web.marushkai.entities.ready.ProductReady;
import com.belgium.cps.web.marushkai.repositories.CategoryRepository;
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

    //LocaleContextHolder
    @RequestMapping("/")
    public String index(Model model){
        Random random = new Random();
        int productNumber = (int)productRepository.count();
        int k = random.nextInt(productNumber) + 1;
        String currLang = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        ArrayList<ProductReady> products = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Product product = productRepository.findOne(k);
            ProductReady productReady = new ProductReady(product.getId(), product.getImage(), product.getImageDescr(currLang),
                    product.getHeader(currLang), product.getDescription(currLang), product.getPrice());
            products.add(productReady);
            int newK = random.nextInt(productNumber) + 1;
            while (newK == k){
                newK = random.nextInt(productNumber) + 1;
            }
            k = newK;
        }
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/category/agriculture/")
    public String getCategory(Model model){
        ArrayList<CategoryReady> categories = new ArrayList<>();
        String currLang = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        for (Category category : categoryRepository.findAll()){
            categories.add(new CategoryReady(category.getId(), category.getLink(), category.getImglink(),
                    category.getImgdescr(), category.getDescription(currLang), category.getHovertext()));
        }
        model.addAttribute("items", categories);
        model.addAttribute("imagepath", "/images/backgrounds/agri_cat_bacground.jpg");
        return "category";
    }

    @RequestMapping("/product/{type}")
    public String getLp(@PathVariable String type, Model model){
        model.addAttribute("contactform", new ContactForm());
        return "landing-page";
    }
}
