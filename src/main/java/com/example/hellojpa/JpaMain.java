package com.example.hellojpa;

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
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
            tx.commit();
            */
            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("최준호");

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            //이전에는 외래키의 값을 넣어줬다면
            //member.setTeam_id(team.getId());
            //team 객체 그대로 넣어줘도 jpa가 알아서 찾아준다.
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            
            //이전에는 가져온 외래키 값으로 해당 팀을 찾아갔다면
            //Long findTeamId = findMember.getTeam_id();
            //Team findTeam = em.find(Team.class, findTeamId);
            //객체 자체를 가져오기 때문에 team을 그대로 가져다 쓰면 된다. jpa가 알아서 join해서 가져온다.
            List<Member> members = findMember.getTeam().getMembers();
            
            for(Member m : members){
                System.out.println("m = "+m.getUsername());
            }

            //

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{
            //자원 반납
            em.close();
        }
        //자원 반납
        emf.close();
    }
}