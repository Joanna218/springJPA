package com.example.springJPA.dao;

import com.example.springJPA.model.UniversityClass;
import org.springframework.data.repository.CrudRepository;

public interface UniversityClassDao extends CrudRepository<UniversityClass, Long> {
}
