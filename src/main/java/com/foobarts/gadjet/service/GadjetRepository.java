package com.foobarts.gadjet.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foobarts.gadjet.service.entity.Gadjet;

public interface GadjetRepository extends JpaRepository<Gadjet, Integer>{

}
