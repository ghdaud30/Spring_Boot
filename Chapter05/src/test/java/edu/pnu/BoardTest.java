package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@DisplayName("보드 한개 입력 테스트")
	@Test
	public void BOardInsertOne() {
		Board board = new Board();
		
//		board.setTitle("Title");
//		board.setWriter("writer");
//		board.setContent("asdsadsa");
//		board.setCreateDate(new Date());
//		board.setCnt(0L);
		
		boardRepo.save(Board.builder()
				.title("adsdasdasd")
				.writer("zxczxczxcczx")
				.content("qwewqewqe")
				.createDate(new Date())
				.cnt(0L)
				.build());
		
	}
	
	@DisplayName("보드 여러개 입력 테스트")
	@Test
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
}
