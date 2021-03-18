package com.example.hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MemberProduct {
    
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    //추가되는 컬럼을 해당 엔티티에서 추가해서 사용
    // count price regdate 등...
}
