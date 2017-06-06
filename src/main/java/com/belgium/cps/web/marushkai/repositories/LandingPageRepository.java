package com.belgium.cps.web.marushkai.repositories;

import com.belgium.cps.web.marushkai.entities.LandingPage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by unlim_000 on 02.03.2017.
 */
public interface LandingPageRepository extends CrudRepository<LandingPage, Integer> {

    LandingPage findByType(String type);

}