package edu.pnu.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		for (int i = 1 ; i < 5; i ++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setPass("pass" + i);
			m.setName("Name" + i);
			m.setRegidate(new Date());
			list.add(m);
		}
	}
	
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		
		return list;
	}
	
	@GetMapping("/member/{id}")
	// 아이디가 {Id} 인 member를 찾아서 브라우저에 출력
	public MemberVO getMember (@PathVariable Integer id) {
		for (int i = 0; i < list.size(); i++) {
			MemberVO m = list.get(i);
			if(m.getId() == id) {
				System.out.println("id를 찾았습니다");
				return m;
			}
		}
		System.out.println("찾는 id가 없습니다");
		return null;
	}
	
	@PostMapping("/member")
	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO memberVO) {
		if(memberVO == null) {
			System.out.println("받은 memberVO 객체가 없습니다.");
			return null;
		}
		else {
			list.add(memberVO);
		}
		
		return memberVO;
	}

}
