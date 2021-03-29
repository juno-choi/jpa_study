package com.example.hellojpa;

import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain{
    public static void main(String[] args) {
        //db와의 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //트랜잭션 정의
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFood().add("치킨");
            member.getFavoriteFood().add("족발");
            member.getFavoriteFood().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "10000"));
            member.getAddressHistory().add(new Address("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("===============================");
            Member findMember = em.find(Member.class, member.getId());

            //값 타입 수정
            //값 타입을 수정할때는 인스턴스 통째로 갈아 끼워줘야한다.
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipCode()));

            //컬렉션 타입일 경우의 수정
            //컬렉션인 Set<String>의 경우 String 자체가 값이기 때문에 해당 값을 삭제하고 수정할 값을 넣어준다
            //delete 후 insert가 일어남
            findMember.getFavoriteFood().remove("치킨");
            findMember.getFavoriteFood().add("한식");

            //equals가 구현되어 있어야 정상적으로 객체끼리의 비교가 가능하다.
            //remove에서의 객체타입은 같은 객체를 equals로 비교하여 true인 경우 삭제시켜준다.
            //값 타입 컬렉션 대신 새로운 Entity를 추가하여 일대다 관계를 고려하는게 나을수 있다.
            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));


            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally{
            //자원 반납
            em.close();
        }
        //자원 반납
        emf.close();
    }
}