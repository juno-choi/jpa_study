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

            /*Address address = new Address("city","street","123");
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address);
            em.persist(member2);

            //이렇게 나오는 버그는 찾기 힘듦 임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험함
            //부작용 발생 위험이 큼
            //이렇게 코딩하게되면 member1과 member2의 city가 모두 new city로 변경되버림
            //대신의 값을 복사해서 사용해야함
            member.getHomeAddress().setCity("new city");*/

            //--------------------------------------
            /*Address address = new Address("city","street","123");
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //이런식으로 새로운 객체로 만들어서 복사해서 사용해야함
            //항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할 수 있다.
            //문제는 직접 정의한 값 타입은 자바 기본 타입이 아니라 객체 타입이다.
            //객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
            //값 타입은 불변 객체로 설계해야함
            //생성자로만 값을 설정하고 setter를 만들지 않으면 됨
            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipCode());
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address2);
            em.persist(member2);
            //set 자체가 불가능해짐
            //member.getHomeAddress().setCity("new city");*/

            //--------------------------------------
            Address address = new Address("city","street","123");
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //대신에 값을 변경하고 싶을때 새로운 인스턴스를 만들어서 넣어야함
            Address newAddress = new Address("new city", address.getStreet(), address.getZipCode());
            member.setHomeAddress(newAddress);

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