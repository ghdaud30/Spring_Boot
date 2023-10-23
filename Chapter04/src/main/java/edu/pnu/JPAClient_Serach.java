package edu.pnu;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient_Serach {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("--->" + searchBoard.toString());
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
			emf.close();
		}


		
	}

}
