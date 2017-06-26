package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.LandingPage;
import com.belgium.cps.web.marushkai.entities.Model;
import com.belgium.cps.web.marushkai.entities.forms.ModelForm;
import com.belgium.cps.web.marushkai.repositories.LandingPageRepository;
import com.belgium.cps.web.marushkai.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by unlim_000 on 11.06.2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ModelService modelService;
    private LandingPageRepository landingPageRepository;

    @Autowired
    public AdminController(ModelService modelService, LandingPageRepository landingPageRepository) {
        this.modelService = modelService;
        this.landingPageRepository = landingPageRepository;
    }

    private static final String UPLOADED_FOLDER = "C:\\Programming\\CPS\\images\\";

    @GetMapping("/")
    public String admin(@ModelAttribute("status") String status, org.springframework.ui.Model model) {
        model.addAttribute("pages", landingPageRepository.findAll());
        model.addAttribute("model", new ModelForm());
        model.addAttribute("status", status);
        return "admin";
    }

    @PostMapping("/uploadModel")
    public String uploadModel(@RequestParam("photo1_file") MultipartFile photo1_file,
                              @RequestParam("photo2_file") MultipartFile photo2_file,
                              @RequestParam("page_id") Integer id,
                              @RequestParam("model_name") String model_name,
                              @ModelAttribute ModelForm myModel, org.springframework.ui.Model model,
                              RedirectAttributes redirectAttributes) {

        LandingPage landingPage = landingPageRepository.findOne(id);
        try {
            String model_dir = landingPage.getMain_header_en().replaceAll(" ", "_");
            byte[] bytesFile1 = photo1_file.getBytes();
            byte[] bytesFile2 = photo2_file.getBytes();
            Path path1 = Paths.get(UPLOADED_FOLDER + model_dir + System.getProperty("file.separator") + photo1_file.getOriginalFilename());
            Path path2 = Paths.get(UPLOADED_FOLDER + model_dir + System.getProperty("file.separator") + photo2_file.getOriginalFilename());
            Files.createDirectories(Paths.get(UPLOADED_FOLDER + model_dir));
            Files.write(path1, bytesFile1);
            Files.write(path2, bytesFile2);
            Model modelToSave = new Model();
            modelToSave.setModel(model_name);
            modelToSave.setPage(landingPage);
            modelToSave.setPhoto1("/images/" + model_dir + "/" + photo1_file.getOriginalFilename());
            modelToSave.setPhoto2("/images/" + model_dir + "/" + photo2_file.getOriginalFilename());
            if (myModel.getBrochure().isEmpty()){
                modelToSave.setBrochure(null);
            } else {
                modelToSave.setBrochure(myModel.getBrochure());
            }
            modelToSave.setDescription_en(myModel.getDescription_en());
            modelToSave.setDescription_fr(myModel.getDescription_fr());
            modelToSave.setDescription_ru(myModel.getDescription_ru());
            modelService.save(modelToSave);
            redirectAttributes.addFlashAttribute("status", "You've uploaded new model with files: " + path1.toString() + ", " +
                    path2.toString());
            return "redirect:/admin/";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("status", e.getMessage() + "\n " + UPLOADED_FOLDER +
                    landingPage.getMain_header_en().replaceAll(" ", "_") +
                    System.getProperty("file.separator") + photo1_file.getOriginalFilename());
            e.printStackTrace();
            return "redirect:/admin/";
        }
    }
}
