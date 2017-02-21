package com.belgium.cps.web.marushkai.repositories;

import com.belgium.cps.web.marushkai.entities.Category;
import com.belgium.cps.web.marushkai.entities.ready.CategoryReady;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by unlim_000 on 11.02.2017.
 */
public interface CategoryRepository extends CrudRepository<Category, Integer>{
}
