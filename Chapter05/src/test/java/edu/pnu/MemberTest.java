package edu.pnu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@DisplayName("맴버 여러개 입력 테스트")
	@Test
	@Order(1)
	public void BOardInsertTen() {
		for(int i = 1; i <= 100; i++) {
			Member member = new Member();
			member.setId("ID" + i);
			member.setPassword("password" + i);
			member.setName("name" + i);
			member.setRole("Role" + i);
			
			
			memberRepo.save(member);
		}
	}
	
	@DisplayName("상세 조회 테스트")
	@Test
	@Order(2)
	public void testGetMember() {
		Member member = memberRepo.findById("ID1").get();
		System.out.println(member.toString());
		
	}
	
	@DisplayName("수정 기능 테스트")
	@Test
	@Order(3)
	public void testUpdateMember() {
		System.out.println("=== 1번 게시글 조회 ===");
		Member member = memberRepo.findById("ID1").get();
		
		System.out.println("===1번 게시글 제목 수정 ===");
		member.setName("이름을 수정했습니다");
		memberRepo.save(member);
	}
	
	@DisplayName("삭제 기능 테스트")
	@Test
	@Order(4)
	public  void testDeleteMember() {
		memberRepo.deleteById("ID1");
	}
}
