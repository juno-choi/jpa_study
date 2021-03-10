package com.example.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    
    @Id
    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
