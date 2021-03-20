package com.example.hellojpa;

import javax.persistence.*;

@Entity
//JPA 기본적략은 싱글 테이블 전략이다
//싱글 테이블의 장점은 성능이 제일 잘 나올 확률이 높다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//조인전략의 DTYPE이라는 컬럼으로 구분해서 테이블을 짤수 있도록 해주는 어노테이션 name으로 컬럼 이름도 변경할 수 있다. 하지만 DTYPE그대로 쓰느게 다른 개발자가 보기에 편함
@DiscriminatorColumn(name = "DIS_TYPE")
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
