package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class queryAnnotationTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("Title20");
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	
	@Test
	@Order(2)
	public void testQueryAnnotationTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("Title30");
		
		System.out.println("검색 결과2");
		for(Object[] row : boardList) {
			System.out.println("---> " + Arrays.toString(row));
		}
	}
	
	@Test
	@Order(3)
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("Title30");
		
		System.out.println("검색 결과3");
		for(Object[] row : boardList) {
			System.out.println("---> " + Arrays.toString(row));
		}
	}
	
	@Test
	@Order(4)
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC,"seq");
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);
		
		System.out.println("검색 결과4");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
}
