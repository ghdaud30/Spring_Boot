package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController 
public class MemberController {
	
	List<MemberVO> list = new ArrayList<>();
	int idx = 1;
	
	public MemberController() {
		for (int i = 1; i <= 10; i++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setName("name" + i);
			m.setPass("pass" + i);
			m.setRegidate(new Date());
			
			//모든 파라미터를 필요로 하는 생성자를 이용한 객체 생성
			MemberVO m1 = new MemberVO(idx, "pass" + idx, "name" + idx, new Date());
			
			//빌더 패턴 생성
//			list.add(MemberVO.builder()
//			.id(idx)
//			.name("name"+idx)
//			.pass("pass"+idx)
//			.regidate(new Date())
//			.build());
			
			list.add(m);
			idx++;
		}
		System.out.println("리스트가 생성 되었습니다");
		
	}
	
	//http://localhost:8080/member : method=“get”
	//모든 멤버 정보를 JSON 형태로 브라우저에 출력
	@GetMapping("/member")
	public List<MemberVO> getMembers(){
		System.out.println("모든 멤버 정보가 출력되었습니다");
		return list;
	}
	
	public MemberVO findMember(Integer id) {
		for (MemberVO m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	//http://localhost:8080/member/{id} : method=“get”
	//아이디가 {Id} 인 member를 찾아서 브라우저에 출력
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.println("Id를 찾았습니다");
		return findMember(id);
	}
	
	public int getNext() {
		int index = -1;
		for (MemberVO m : list) {
			if(index < m.getId()) index = m.getId();
		}
		return index + 1;
	}
	
	//http://localhost:8080/member : method=“post”
	//추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			System.out.println("이름과 비밀번호가 없습니다");
			return null;
		}
		
		memberVO.setId(getNext());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		System.out.println("추가되었습니다");

		return memberVO;
	}
	
	//http://localhost:8080/member : method=“put”
	//수정 대상 객체 정보를 전달, 수정된 객체를 출력
	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO memberVO) {
		MemberVO m = findMember(memberVO.getId());
		if(memberVO.getName() != null) m.setName(memberVO.getName());
		if(memberVO.getPass() != null) m.setPass(memberVO.getPass());
		System.out.println("수정되었습니다");
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			System.out.println("수정의 실패 하였습니다");
			return null;
		}
		return m;
	}
	
	//http://localhost:8080/member/{id} : method=“delete”
	//아이디가 {Id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		MemberVO m = findMember(id);
		if(m != null) {
			System.out.println("삭제되었습니다");
			list.remove(m);
			return m;
		}
		System.out.println("삭제의 실패하였습니다");
		return null;
	}


}
