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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());
            //지연 로딩 LAZY를 사용해서 프록시로 조회
            //class를 확인하면 proxy로 가져옴
            //즉시 로딩 EAGER를 사용하면 한번에 join된 객체를 다 가져온다. 그러므로 froxy가 아닌 entity자체를 가져옴
            System.out.println("m = "+ m.getTeam().getClass());

            m.getTeam().getName();
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