package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;


public class JpaMain {
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

        	Book book = new Book();
        	book.setName("JPA");
        	book.setAuthor("kim");
        	
        	em.persist(book);
        	
        	em.createQuery("select i from Item i where type(i) = Book ", Item.class)
        	.getResultList();
        	
        	em.persist(book);
        	
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
