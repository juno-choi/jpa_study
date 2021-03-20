package com.example.hellojpa;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Member extends BaseEntity{
    
    @Id 
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id; 

    @Column(name = "USERNAME") 
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Integer age; 

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING) //DB에 없는 type을 사용하고 싶을땐 Enumerated
    private RoleType roleType; 

    @Lob //varchar보다 큰 데이터를 넣을 필요가 있을때 사용하는 어노테이션
    private String description; 

    public Member(){

    }

}
