package edu.pnu;

import java.sql.SQLException;
import java.util.List;

import org.h2.result.UpdatableRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest
public class MemberDaoTest {
	
	@DisplayName("MemberDao Insert Test")
//	@Test
	public void testInsert() throws SQLException {
		MemberDao dao = new MemberDao();
		int ret = dao.addMember(MemberVO.builder()
				.pass("sadsadsa")
				.name("kim")
				.build());
		System.out.println("ret : " + ret);
		System.out.println("추가 완료");
	}
	
	@DisplayName("MemberDao Select All Test")
	@Test
	public void testSelectAll() throws SQLException {
		MemberDao dao = new MemberDao();
		
		List<MemberVO> list = dao.getMembers();
		for (MemberVO m : list) {
			System.out.println("MemberVO " + m);
		}
	}
	
	@DisplayName("MemberDao select Test")
	@Test
	public void testSelect() throws SQLException {
		MemberDao dao = new MemberDao();
		
		MemberVO m = dao.getMember(1);
		if(m != null) {
			System.out.println("m 찾았습니다 " + m);
		}
		else {
			System.out.println("오류 입니다.");
		}
	}
	
	@DisplayName("MemberDao update Test")
	@Test
	public void testUpdate() throws SQLException {
		MemberDao dao = new MemberDao();
		MemberVO m = dao.getMember(2);
		m.setPass("32czxz");
		m.setName("ㅋㅌㅊdasdas");
		int index = dao.updateMember(m);
		
		if(index >= 1) {
			System.out.println("m을 수정 했습니다 " + m);
		}
		else {
			System.out.println("업데이트 오류 입니다. ");
		}
	}
	
	@DisplayName("MemberDao delete Test")
	@Test
	public void testDelete() throws SQLException {
		MemberDao dao = new MemberDao();
		int num = 5;
		int index = dao.deleteMember(num);
		
		if(index >= 1) {
			System.out.println(num + " 번을 삭제 했습니다 " + num);
		}
		else {
			System.out.println("삭제 오류 입니다. ");
		}
	}
}
