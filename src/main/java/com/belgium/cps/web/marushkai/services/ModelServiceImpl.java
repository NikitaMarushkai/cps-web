package com.belgium.cps.web.marushkai.services;

import com.belgium.cps.web.marushkai.entities.Model;
import com.belgium.cps.web.marushkai.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by unlim_000 on 11.06.2017.
 */

@Service
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    @Transactional
    public void save(Model model) {
        modelRepository.save(model);
    }
}
