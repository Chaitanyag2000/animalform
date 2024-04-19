package com.pixel.service;

import com.pixel.model.Animal;
import com.pixel.repository.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepo animalRepoImpl;

  /*  public Animal save(Animal animal){
        return animalRepoImpl.save(animal);
    }*/
   public Animal save(Animal animal, MultipartFile imageFile) throws IOException {
       if (imageFile != null && !imageFile.isEmpty()) {
           animal.setAnimalImage(imageFile.getBytes());
       }
       return animalRepoImpl.save(animal);
   }



    public Optional<Animal> findById(int animalId){
        return animalRepoImpl.findById(animalId);
    }

    public List<Animal>findAll(){
        return animalRepoImpl.findAll();
    }

    public List<Animal>findByAnimalName(String animalName){
        return animalRepoImpl.findByAnimalName(animalName);
    }

    public Animal update(Animal animal){
        return animalRepoImpl.save(animal);
    }


    public void deleteById(int animalId){
        animalRepoImpl.deleteById(animalId);
    }

    public void deleteAll(){
        animalRepoImpl.deleteAll();
    }
}
