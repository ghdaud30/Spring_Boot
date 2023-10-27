package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;
	@Test
	@Order(1)
	
	public void testAddQuery() {
		Random rd = new Random();
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("Title" + i);
			board.setWriter("writer" + i);
			board.setContent("content" + i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(0,100));

			boardRepo.save(board);
		}
	}

	@Test
	@Order(2)
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "Title10";

		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		}

		Pageable paging = PageRequest.of(0, 5);

		Page<Board> boardList = boardRepo.findAll(builder, paging);

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}

	}
	
	@Test
	@Order(3)
	public void testDynamicQuery2() {
		String searchCondition = "CNT";
		Long searchKeyword = 50L;

		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (searchCondition.equals("CNT")) {
			builder.and(qboard.cnt.gt(searchKeyword));
		} else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.cnt.gt(searchKeyword));
		}

		Pageable paging = PageRequest.of(0, 5);

		Page<Board> boardList = boardRepo.findAll(builder, paging);

		System.out.println("검색 결과2");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
}
