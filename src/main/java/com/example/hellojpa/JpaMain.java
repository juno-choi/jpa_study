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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

           /* Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            System.out.println("m1 == m2 > " +(m1.getClass()==m2.getClass()));
            //instanceof를 통해 class가 맞는지 검사해야함 ==은 false가 나옴
            System.out.println("m1 == Member.class > " +(m1 instanceof Member);
            System.out.println("m2 == Member.class > " +(m2 instanceof Member);*/

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = "+refMember.getClass());    //Proxy

            //facotry로 부터 persistence의 proxy instance 초기화 여부를 확인하는 method
            System.out.println("isLoaded = "+emf.getPersistenceUnitUtil().isLoaded(refMember));
            //proxy class 확인
            System.out.println("class = "+refMember.getClass());
            //proxy 강제 초기화
            //hibernate에서 제공하는 기능으로 jpa는 객체의 정보를 불러오면 자동으로 초기화된다
            Hibernate.initialize(refMember);


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