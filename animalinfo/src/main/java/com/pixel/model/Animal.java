package com.pixel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANIMAL")
public class Animal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "animal_id")
    private int animalId;

    @NotNull(message = "PLEASE ENTER THE ANIMAL NAME")
    @Column(name = "animal_name")
    private String animalName;

    @NotNull(message = "PLEASE ENTER THE ANIMAL CATEGORY")
    @Column(name = "animal_category")
    private String animalCategory;

    @NotNull(message = "PLEASE ENTER THE DESCRIPTION")
   @Size(min = 10, message = "DESCRIPTION MUST BE GREATER THEN 10 APLHABETS")
    @Column(name = "animal_description")
    private String animalDescription;

    @NotNull(message = "PLEASE ENTER THE LIFE EXPECTANCY")
    @Column(name = "animal_life_expectancy")
    private String animalLifeExpectancy;

    @Lob
    @Column(name = "animal_image", columnDefinition = "LONGBLOB")
    private byte[] animalImage;

}


