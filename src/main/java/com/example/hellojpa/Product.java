package com.example.hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
    
    @Id
    @GeneratedValue
    private String id;
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
