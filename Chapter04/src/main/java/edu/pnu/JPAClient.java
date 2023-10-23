package edu.pnu;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Random rd = new Random();
		
		try {
			tx.begin();

			for(int i = 1; i <= 5; i++) {
				
				Board board = new Board();
				board.setTitle("JPA title" + i);
				board.setWriter("관리자" + i);
				board.setContent("asdasdadas" + i);
				board.setCreateDate(new Date());
				board.setCnt(rd.nextLong(50));
				
				em.persist(board);
				
			}
			
			
			tx.commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();
		}
		
	}

}
