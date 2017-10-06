package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.Used;
import com.belgium.cps.web.marushkai.repositories.UsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("/dealer")
public class DealerController {

    private UsedRepository usedRepository;

    @Autowired
    public DealerController(UsedRepository usedRepository) {
        this.usedRepository = usedRepository;
    }

    @GetMapping("/")
    public String getDealerPage(@ModelAttribute("items") ArrayList<Used> items,
                                @ModelAttribute("status") String status, Model model) {
        if (items == null || items.isEmpty()) {
            model.addAttribute("items", new ArrayList<>());
            model.addAttribute("status", status);
        } else {
            model.addAttribute("items", items);
            model.addAttribute("status", "");
        }
        return "dealer";
    }

    @GetMapping("/find")
    public String getById(RedirectAttributes redirectAttributes, @RequestParam(name = "ids") String ids) {
        StringTokenizer tokenizedIds = new StringTokenizer(ids, ",");
        List<Long> readyIds = new ArrayList<>();
        String status = "Found items:";
        while (tokenizedIds.hasMoreElements()) {
            try {
                long currId = Long.parseLong(tokenizedIds.nextElement().toString().trim());
                readyIds.add(currId);
            } catch (NumberFormatException e) {
                status = "Error! Please use only the numbers in the input field";
                break;
            }
        }
        redirectAttributes.addAttribute("items", usedRepository.findAllByIdIn(readyIds));
        redirectAttributes.addAttribute("status", status);
        return "redirect:/dealer/";
    }
}
