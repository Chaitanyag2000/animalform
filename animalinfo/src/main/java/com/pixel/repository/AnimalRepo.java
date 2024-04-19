package com.pixel.repository;

import com.pixel.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Integer> {


    List<Animal>findByAnimalName(String animalName);


}
