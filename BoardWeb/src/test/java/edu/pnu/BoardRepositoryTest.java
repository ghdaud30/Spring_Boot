package edu.pnu;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardRepositoryTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	@Order(1)
	@DisplayName("데이터 삽입")
	public void testInsert() {
		Member member1 = new Member();
		member1.setId("member");
		member1.setPassword(encoder.encode("member123"));
		member1.setName("둘리");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("admin");
		member2.setPassword(encoder.encode("admin123"));
		member2.setName("도우너");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);
		
		for(int i = 1; i <= 20 ; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 게시글" + i);
			board.setContent(member1.getName() + "가 등록한 게시글" + i);
			boardRepo.save(board);
		}
		
		for(int i = 1; i <= 20 ; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 게시글" + i);
			board.setContent(member2.getName() + "가 등록한 게시글" + i);
			boardRepo.save(board);
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("데이터 상세 조회")
	public void TestGetBoard() {
		Optional<Board> option = boardRepo.findById(1L);
		if(!option.isPresent()) {
			System.out.println("No Data");
			return;
		}
		Board board = option.get();
		System.out.println("[" + board.getSeq() + "번 게시 글 상세 정보 ]");
		System.out.println("제목 \t : " + board.getTitle());
		System.out.println("작성자 \t : " + board.getMember().getName());
		System.out.println("내용 \t : " + board.getContent());
		System.out.println("등록일 \t : " + board.getCreateDate());
		System.out.println("조회수 \t : " + board.getCnt());
	}
	
	@Test
	@Order(3)
	@DisplayName("목록 검색 테스트")
	public void testGetBoardList() {
		Optional<Member> option = memberRepo.findById("member");
		if(!option.isPresent()) {
			System.out.println("No Member");
			return;
		}
		else {
			Member member = option.get();
			System.out.println("[ " + member.getName() +"가 등록한 게시글 ]");
			for (Board board : member.getBoardList()) {
				System.out.println("---> " + board.toString());
			}
		}
	}
	
}
