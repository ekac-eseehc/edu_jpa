package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			
			Member member1 = new Member();
			member1.setName("Kim");
			em.persist(member1);
			
			em.flush();
			em.clear();
			
			Member refMember = em.getReference(Member.class, member1.getId());
			System.out.println("refMember = " + refMember.getClass());
			Hibernate.initialize(refMember);
//			System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
			
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
