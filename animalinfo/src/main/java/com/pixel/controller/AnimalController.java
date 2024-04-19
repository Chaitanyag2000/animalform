package com.pixel.controller;


import com.pixel.exception.RecordNotFoundException;
import com.pixel.model.Animal;
import com.pixel.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
@Slf4j
public class AnimalController {

    @Autowired
    private AnimalService animalServiceImpl;

   /* @PostMapping("/save")

    public ResponseEntity<Animal>save(@Valid @RequestBody Animal animal){
        return new ResponseEntity<>(animalServiceImpl.save(animal), HttpStatus.CREATED);
    }*/

    @PostMapping("/save")
    public ResponseEntity<Animal> save(@Valid @RequestPart("animal") Animal animal,
                                       @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        log.info("@@@@@@@@@@@@@@@@@@@@ INSIDE SAVE");
        return new ResponseEntity<>(animalServiceImpl.save(animal, imageFile), HttpStatus.CREATED);
    }

    @GetMapping("/findbyid/{animalId}")
    public ResponseEntity<Optional<Animal>> findById(@PathVariable int animalId){
        return ResponseEntity.ok(animalServiceImpl.findById(animalId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Animal>>findAll(){
        return ResponseEntity.ok(animalServiceImpl.findAll());
    }

    @PutMapping("/update/{animalId}")
    public ResponseEntity<Animal>update(@PathVariable int animalId,  @Valid @RequestBody Animal animal){

        Animal animal1 = animalServiceImpl.findById(animalId).orElseThrow(()->new RecordNotFoundException("ANIMAL ID DOES NOT EXITS"));

        animal1.setAnimalName(animal.getAnimalName());
        animal1.setAnimalCategory(animal.getAnimalCategory());
        animal1.setAnimalDescription(animal.getAnimalDescription());
        animal1.setAnimalLifeExpectancy(animal.getAnimalLifeExpectancy());


        return new ResponseEntity<>(animalServiceImpl.update(animal1), HttpStatus.CREATED);

    }

    @GetMapping("/sortbyanimalname")
    public ResponseEntity<List<Animal>>sortByAnimalName(){
        return ResponseEntity.ok(animalServiceImpl.findAll().stream().sorted(Comparator.comparing(Animal::getAnimalName)).toList());
    }

    @GetMapping("/sortbyanimalcategory")
    public ResponseEntity<List<Animal>>sortByAnimalCaregory(){
        return ResponseEntity.ok(animalServiceImpl.findAll().stream().sorted(Comparator.comparing(Animal::getAnimalCategory)).toList());
    }

    @GetMapping("/sortbyanimallifeexpectancy")
    public ResponseEntity<List<Animal>>sortByAnimalLifeExpectancy(){
        return ResponseEntity.ok(animalServiceImpl.findAll().stream().sorted(Comparator.comparing(Animal::getAnimalDescription)).toList());
    }

    @DeleteMapping("/deletebyanimalid/{animalId}")
    public ResponseEntity<String> deleteByAniamlId(@PathVariable int animalId){
        animalServiceImpl.deleteById(animalId);
        return ResponseEntity.ok("Id deleted successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String>deleteAll(){
        animalServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data deleted Successfully");
    }

}
