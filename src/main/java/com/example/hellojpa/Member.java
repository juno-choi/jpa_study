package com.example.hellojpa;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class Member extends BaseEntity{

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id; 

    @Column(name = "USERNAME") 
    private String username;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    //fetch type을 지정해주면 해당 객체를 froxy로 조회함
    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
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
