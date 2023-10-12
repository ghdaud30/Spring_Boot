package edu.pnu.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;


// 웹브라우저를 통한 요청을 받아들이고 응답하는 컨트롤러 객체

// C insert Post
// R select Get
// U update put
// D delete Delete


@RestController 
// return 할때 json 형태로 반환함
public class MemberController {

	List<MemberVO> list;
	
	public MemberController() {
		list = new ArrayList<>();
		System.out.println("===> MemberController 생성");
		for (int i = 1 ; i <= 5; i ++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setPass("pass" + i);
			m.setName("Name" + i);
			m.setRegidate(new Date());
			list.add(m);
		}
	}
	
	
	@GetMapping("/member") //http://localhost:8080/member 를 출력하면 호출
	public List<MemberVO> getMembers() {
		
		return list;
	}
	
	private MemberVO findMember(Integer id) {
		for (MemberVO m : list) {
			if(m.getId() == id) 
				return m;
		}
		return null;
	}
	
	@GetMapping("/member/{id}")
	// 아이디가 {Id} 인 member를 찾아서 브라우저에 출력
	//http://localhost:8080/member/5
	public MemberVO getMember (@PathVariable Integer id) {
		return findMember(id);
	}
	
	// 현재 입력되어 있는 객체들의 id를 조사해서 최대값에 1을 더해서
	// 다음 추가되는 데이터의 id를 만들어서 넘겨준다.
	private int getNextid() {
		int mid = -1;
		for (MemberVO m : list) {
			if(mid < m.getId()) mid = m.getId();
		}
		return mid + 1;
	}
	
	
	@PostMapping("/member")
	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO memberVO) {
		
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			System.out.println("이름이나 패스워드가 없습니다");
			return null;
		}
		
		memberVO.setId(getNextid());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
//public ResponseEntity<?> addMember1	(MemberVO memberVO) {
//		
//		if(memberVO.getName() == null || memberVO.getPass() == null) {
//			System.out.println("이름이나 패스워드가 없습니다");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberVO);
//		}
//		
//		memberVO.setId(getNextid());
//		memberVO.setRegidate(new Date());
//		list.add(memberVO);
//		return ResponseEntity.ok(memberVO);
//	}
	
	@PutMapping("/member")
	//수정 대상 객체 정보를 전달, 수정된 객체를 출력
	public MemberVO updateMembers(MemberVO memberVO) {
		MemberVO fm = findMember(memberVO.getId());
		if(memberVO.getName() != null) fm.setName(memberVO.getName());
		if(memberVO.getPass() != null) fm.setPass(memberVO.getPass());
		
		return fm;
	}
	
	@DeleteMapping("/member/{id}")
	//아이디가 {Id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	//http://localhost:8080/member/5
	public MemberVO removeMember(@PathVariable Integer id) {
		for(int i = 1; i <= list.size(); i++) {
			MemberVO m = list.get(i);
			if(m.getId() == id) {
				list.remove(i);
				return m;
			}
		}
		return null;
	}
	
//	@DeleteMapping("/member")
//	//http://localhost:8080/member?id=5
//	public MemberVO removeMember1(Integer id) {
//		for(int i = 1; i <= list.size(); i++) {
//			MemberVO m = list.get(i);
//			if(m.getId() == id) {
//				list.remove(i);
//				return m;
//			}
//		}
//		return null;
//	}

}
