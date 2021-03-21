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
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            /*
            cascade type을 all로 주면 parent만 영속성 관리해도 연결되어 있는 child가 모두 영속성 등록된다.
            em.persist(child1);
            em.persist(child2);
            */

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);


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