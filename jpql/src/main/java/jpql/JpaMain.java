package jpql;

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
			Team teamA = new Team();
			teamA.setName("TeamA");
			em.persist(teamA);
			
			Team teamB = new Team();
			teamB.setName("TeamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamA);  
			em.persist(member2);
			
			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setTeam(teamB);  
			em.persist(member3);
			
			em.flush();
			em.clear();

			String query = "select distinct t From Team t join fetch t.members ";

			List<Team> result = em.createQuery(query, Team.class)
					.getResultList();
			
			System.out.println("result : " + result.size());

			for (Team team : result) {
				System.out.println("member : " + team.getName() + ", " + team.getMembers().size());
				for (Member member : team.getMembers()) {
					System.out.println(" -> member : " + member);
				}
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
