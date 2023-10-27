
package edu.pnu;

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
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;
	
	
//	@Test
//	@Order(2)
//	public void testFindByTitle() {
//		List<Board> boardList = boardRepo.findByTitle("Title10");
//		
//		System.out.println("검색 결과1");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	@Order(1)
//	public void testfindByTitleContaining() {
//		Pageable paging = PageRequest.of(0, 5);
//		List<Board> boardList = boardRepo.findByTitleContaining("Title5",paging);
//		
//		System.out.println("검색 결과1");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
	@Test
	@Order(1)
	public void testfindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC,"seq");
		
		Page<Board> pageInfo = boardRepo.findByTitleContaining("Title", paging);
		
		System.out.println("PAGE SIZE :" + pageInfo.getSize());
		System.out.println("TOTAL PAGES :" + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT :" + pageInfo.getTotalElements());
		System.out.println("NEXT :" + pageInfo.getPageable());
		
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("검색 결과1");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
		
	}
//	
//	@Test
//	@Order(3)
//	public void testFindByContentContaining() {
//		List<Board> boardList = boardRepo.findByContentContaining("15");
//		System.out.println("검색 결과2");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	@Order(4)
//	public void testfindByTitleContainingOrderBySeqDesc() {
//		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("5");
//		System.out.println("검색 결과3");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	@Order(1)
//	@DisplayName("랜덤으로 입력 테스트")
//	public void BOardInsertTen() {
//		for(int i = 1; i <= 100; i++) {
//			Random r = new Random();
//			int num = r.nextInt(0,100);
//			Board board = new Board();
//			board.setTitle("Title" + num);
//			board.setWriter("writer" + num);
//			board.setContent("asdsadsa" + num);
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			
//			boardRepo.save(board);
//		}
//	}
	
//	@Test
//	@Order(1)
//	@DisplayName("랜덤으로 입력 테스트")
//	public void BOardInsertTen() {
//		for(int i = 1; i <= 100; i++) {
//			Random r = new Random();
//			int num = r.nextInt(0,100);
//			Board board = new Board();
//			board.setTitle("Title" + num);
//			board.setWriter("writer" + num);
//			board.setContent("asdsadsa" + num);
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			
//			boardRepo.save(board);
//		}
//	}
	
//	@Test
//	@Order(2)
//	@DisplayName("1이 포함되는 데이터 출력")
//	public void testFindByContentContaining() {
//		List<Board> boardList = boardRepo.findByContentContaining("1");
//		System.out.println("검색 결과2");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
}
