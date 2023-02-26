package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			
			Member member = new Member();
			member.setName("member1");
			em.persist(member);
			
			em.flush();
			
			// 결과 0
			// dbconn.executeQuery("select * from member");
			
			List<Member> resultList = em.createNativeQuery("select MEMBER_ID , city , street , zipcode from MEMBER" , Member.class).getResultList();
			
			for (Member member1 : resultList) {
				System.out.println(" member1 = " + member1);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

}
