package hellojpa;

import java.util.List;
import java.util.Set;

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
			member.setName("Kim");
			member.setHomeAddress(new Address("home city" , "street1" , "123456"));
			
			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");
			
			member.getAddressHistory().add(new AddressEntity("old1" , "street1" , "111111"));
			member.getAddressHistory().add(new AddressEntity("old2" , "street2" , "222222"));
			
			em.persist(member);
			
			em.flush();
			em.clear();
			
			System.out.println("========== START ==========");
			Member findMember = em.find(Member.class, member.getId());

//			homeCity -> newCity
//			findMember.getHomeAddress().setCity("newCity"); (X)
			Address address = findMember.getHomeAddress();
			findMember.setHomeAddress(new Address("new city" , address.getStreet() , address.getZipcode()));
			
//			치킨 - > 한식
			findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("한식");
			
//			equals 로 값 찾음 address 안에 재정의 되어 있음
//			findMember.getAddressHistory().remove(new Address("old1" , "street1" , "111111"));
//			findMember.getAddressHistory().add(new Address("new city1" , "street1" , "111111"));
			
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
