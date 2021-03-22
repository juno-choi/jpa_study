package com.example.hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {
    
    @Id @GeneratedValue
    private Long id;
    
    private String name;

    //mappedBy는 읽기 전용
   /* @OneToOne(mappedBy = "locker")
    private Member member;*/
}
