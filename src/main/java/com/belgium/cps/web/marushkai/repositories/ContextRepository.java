package com.belgium.cps.web.marushkai.repositories;

import com.belgium.cps.web.marushkai.entities.Context;
import org.springframework.data.repository.CrudRepository;

public interface ContextRepository extends CrudRepository<Context, Integer> {

    Context findByKey(String key);

}
