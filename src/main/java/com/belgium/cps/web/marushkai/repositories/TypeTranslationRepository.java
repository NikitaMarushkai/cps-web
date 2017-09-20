package com.belgium.cps.web.marushkai.repositories;

import com.belgium.cps.web.marushkai.entities.TypeTranslation;
import org.springframework.data.repository.CrudRepository;

public interface TypeTranslationRepository extends CrudRepository<TypeTranslation, Integer> {
    TypeTranslation getByType(String type);
}
