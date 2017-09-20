package com.belgium.cps.web.marushkai.controllers;

import com.belgium.cps.web.marushkai.entities.Used;
import com.belgium.cps.web.marushkai.repositories.UsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class AjaxController {

    private final UsedRepository usedRepository;

    @Autowired
    public AjaxController(UsedRepository usedRepository) {
        this.usedRepository = usedRepository;
    }

    //?page=..&size=..
    @RequestMapping(value = "/usedPages", method = RequestMethod.GET)
    Page<Used> list(Pageable pageable, @RequestParam("filter") String filter) {
        Page<Used> useds = null;
        if (filter == null || filter.equals("") || filter.isEmpty()) {
            useds = usedRepository.findAll(pageable);
        } else {
            useds = usedRepository.findAllByType(pageable, filter);
        }
        return useds;
    }

    @RequestMapping(value = "/totalRows", method = RequestMethod.GET)
    String getTotalPage(@RequestParam("filter") String filter) {
        long number = 0;
        if (filter == null || filter.isEmpty() || filter.equals("")) {
            number = usedRepository.count();
        } else {
            number = usedRepository.countAllByType(filter);
        }
        return number + "";
    }
}
