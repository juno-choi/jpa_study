package com.example.hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Member {
    
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    @Column(name = "USERNAME")
    private String username;

    //실제 RDBMS처럼 관계가 있는 외래키를 갖고 있을 경우 객체지향적인 코드를 만들기 어렵다.
    //@Column(name = "TEAM_ID")
    //private Long team_id;
    
    //그래서 외래키를 갖는 컬럼을 갖고 있는게 아닌 테이블 자체에서 join하는 column을 jpa에게 알려준다.
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
