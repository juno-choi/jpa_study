package com.example.hellojpa;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Member {
    
    @Id 
    private Long id; 

    @Column(name = "name") 
    private String username; 

    private Integer age; 

    @Enumerated(EnumType.STRING) //DB에 없는 type을 사용하고 싶을땐 Enumerated
    private RoleType roleType; 

    @Temporal(TemporalType.TIMESTAMP) //날짜에 사용할 수 있는 어노테이션
    private Date createdDate; 

    @Temporal(TemporalType.TIMESTAMP) 
    private Date lastModifiedDate; 

    @Lob //varchar보다 큰 데이터를 넣을 필요가 있을때 사용하는 어노테이션
    private String description; 

    public Member(){

    }
}
