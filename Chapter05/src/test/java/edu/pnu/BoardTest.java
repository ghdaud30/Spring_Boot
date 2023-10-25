package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
//	@DisplayName("보드 한개 입력 테스트")
//	@Test
//	public void BOardInsertOne() {
//		Board board = new Board();
//		
////		board.setTitle("Title");
////		board.setWriter("writer");
////		board.setContent("asdsadsa");
////		board.setCreateDate(new Date());
////		board.setCnt(0L);
//		
//		boardRepo.save(Board.builder()
//				.title("adsdasdasd")
//				.writer("zxczxczxcczx")
//				.content("qwewqewqe")
//				.createDate(new Date())
//				.cnt(0L)
//				.build());
//		
//	}
	
	@DisplayName("보드 여러개 입력 테스트")
	@Test
	@Order(1)
	public void BOardInsertTen() {
		for(int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("Title" + i);
			board.setWriter("writer" + i);
			board.setContent("asdsadsa" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardRepo.save(board);
		}
	}
	
	@DisplayName("상세 조회 테스트")
	@Test
	@Order(2)
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board.toString());
		
	}
	
	@DisplayName("수정 기능 테스트")
	@Test
	@Order(3)
	public void testUpdateBoard() {
		System.out.println("=== 1번 게시글 조회 ===");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("===1번 게시글 제목 수정 ===");
		board.setTitle("제목을 수정했습니다");
		boardRepo.save(board);
	}
	
	@DisplayName("삭제 기능 테스트")
	@Test
	@Order(4)
	public  void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}

}
