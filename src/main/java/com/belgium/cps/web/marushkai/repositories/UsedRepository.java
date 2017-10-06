package com.belgium.cps.web.marushkai.repositories;

import com.belgium.cps.web.marushkai.entities.Used;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsedRepository extends PagingAndSortingRepository<Used, Long> {

    @Query("SELECT DISTINCT u.type FROM Used u")
    List<String> getDistinctTypes();

    long countAllByType(String type);

    Page<Used> findAllByType(Pageable pageable, String type);

    List<Used> findAllByIdIn(List<Long> ids);
}
