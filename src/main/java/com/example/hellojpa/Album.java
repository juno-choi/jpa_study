package com.example.hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//join 전략시 dtype에 들어갈 value값을 지정할수 있는 annotation
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;

}
