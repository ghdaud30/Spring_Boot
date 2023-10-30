package edu.pnu;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	@Order(1)
	@DisplayName("데이터 10개 삽입하기")
	public void testInsert1() {
		for(int i = 1; i <= 10; i++) {
			 memberRepo.save(Member.builder()
						.name("user" + i)
						.pass("1234")
						.regeidate(new Date())
						.build());
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("데이터 출력하기")
	public void getMembers(){
		List<Member> list = (List<Member>) memberRepo.findAll();
		for (Member member : list) {
			System.out.println(member);
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("선택한 데이터 출력하기")
		public void getMember() {
		
			Optional<Member> opt = memberRepo.findById(10);
			System.out.println(opt.get().toString());
		}
	
	
}
	

