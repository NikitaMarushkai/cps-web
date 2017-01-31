package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.Gizmo;
import com.belgium.cps.web.marushkai.GizmoChild;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by unlim_000 on 29.01.2017.
 */

@Controller
public class BaseController {


    @RequestMapping("/")
    public String index(){
        return "index";
    }
//    @RequestMapping("/")
//    public String index(Model model){
//        Gizmo gizmo = new Gizmo();
//        gizmo.getChildren().add(new GizmoChild());
//        model.addAttribute("gizmo", new Gizmo());
//        return "hello";
//    }
//
//    @RequestMapping("/save")
//    public String save(Gizmo gizmo) {
//        System.out.println(gizmo.getField1());
//        System.out.println(gizmo.getField2());
//        for(GizmoChild child : gizmo.getChildren()) {
//            System.out.println(child.getChildField1());
//            System.out.println(child.getChildField2());
//        }
//        return "redirect:/";
//    }
}
