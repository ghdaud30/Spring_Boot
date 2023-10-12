package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberService {
	
	private List<MemberVO> list;
	
	public MemberService(){
		list = new ArrayList();
		for (int i = 1; i <= 10; i++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setName("name" + i);
			m.setPass("pass" + i);
			m.setRegidate(new Date());
			list.add(m);
		}
		
	}
	
	public List<MemberVO> getmembers(){
		System.out.println("리스트를 출력합니다");
		return list;
	}
	
	public MemberVO findMember(Integer id) {
		for (MemberVO m : list) {
			if(m.getId() == id) {
				System.out.println("아이디를 찾았습니다");
				return m;
			}
		}
		System.out.println("아이디가 없습니다");
		return null;
	}
	
	public MemberVO getMember(@PathVariable Integer id) {
		MemberVO m = findMember(id);
		if(m == null) {
			return null;
		}
		else {
			System.out.println("아이디를 출력합니다");
			return m;
		}
	}
	
	public int getNext() {
		int index = -1;
		
		for (MemberVO m : list) {
			if(index < m.getId()) index = m.getId();
		}
		return index + 1;
	}
	
	public MemberVO addMember(MemberVO memberVO) {

		int idx = getNext();
		
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			System.out.println("이름과 비밀번호가 없습니다");
			return null;
		}
		else {
			memberVO.setId(idx);
			memberVO.setRegidate(new Date());
			list.add(memberVO);
			System.out.println("아이디를 추가 하였습니다");
			return memberVO;
		}
	}

	public MemberVO updateMembers(MemberVO memberVO) {
		
		MemberVO m = findMember(memberVO.getId());
		if(m == null) {
			System.out.println("아이디가 없습니다");
			return null;
		}
		if(memberVO.getName() != null) m.setName(memberVO.getName());
		if(memberVO.getPass() != null) m.setPass(memberVO.getPass());
		System.out.println("수정되었습니다");
		
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			System.out.println("수정의 실패 하였습니다");
			return null;
		
	}
	return m;
	}
	
	public MemberVO removeMember(@PathVariable Integer id) {
		MemberVO m = findMember(id);
		if(m != null) {
			list.remove(m);
			System.out.println("삭제 되였습니다");
			return m;
		}
		else {
			System.out.println("삭제에 실패 하였습니다");
			return null;
		}
	}
}
