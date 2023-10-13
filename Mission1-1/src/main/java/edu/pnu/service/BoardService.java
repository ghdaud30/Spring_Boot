package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.board.BoardVO;

@RestController
public class BoardService {
	
		private List<BoardVO> list = new ArrayList();
		
		public BoardService() {
			for (int i = 1; i <= 10; i++) {
				BoardVO b = new BoardVO();
				b.setSeq(i);
				b.setContent("content" + i);
				b.setCreateDate(new Date());
				b.setTitle("title" + i);
				b.setWriter("writer" + i);
				b.setCnt(i);
				list.add(b);
			}
		}
		
		public List<BoardVO> getBoards(){
			System.out.println("보드 리스트를 출력합니다");
			return list;
		}
		
		public BoardVO findMember(@PathVariable Integer seq) {
			for (BoardVO b : list) {
				if(b.getSeq() == seq) {
					System.out.println("보드 객체를 찾았습니다");
					return b;
				}
			}
			System.out.println("보드 객체를 못 찾았습니다");
			return null;
		}
		
		public BoardVO getBoard(@PathVariable Integer seq) {
			BoardVO b = findMember(seq);
			if(b != null) {
				return b;
			}
			else
				return null;
		}
		
		public int getNext() {
			int index = -1;
			for(BoardVO b : list) {
				if(index < b.getSeq()) index = b.getSeq();
			}
			return index + 1;
		}
		
		public BoardVO addMember(BoardVO boardVO) {
			int index = getNext();
			
			if(boardVO.getTitle() == null || boardVO.getContent() == null
					|| boardVO.getWriter() == null) {
				System.out.println("제목이나 글쓴이 내용이 없습니다");
				return null;
			}
			else {
				boardVO.setSeq(index);
				boardVO.setCnt(index);
				boardVO.setCreateDate(new Date());
				list.add(boardVO);
				System.out.println("추가 되었습니다");
				return boardVO;
			}
		}
		
		public BoardVO updateMembers(BoardVO boardVO) {
			
			BoardVO b = findMember(boardVO.getSeq());
			if(b != null) {
				if(boardVO.getTitle() == null || boardVO.getContent() == null) {
					System.out.println("제목이나 내용이 없습니다");
					return null;
				}
				else {
					if(boardVO.getTitle() != null) b.setTitle(boardVO.getTitle());
					if(boardVO.getContent() != null) b.setContent(boardVO.getContent());
					System.out.println("제목과 내용이 수정 되었습니다");
					return b;
				}
			}
			else {
				System.out.println("찾는 멤버가 없습니다");
				return null;
			}
		}
		
		public BoardVO removeMember(@PathVariable Integer seq) {
			BoardVO b = findMember(seq);
			if(b != null) {
				list.remove(b);
				System.out.println("아이디가 삭제 되었습니다");
				return b;
			}
			else {
				System.out.println("삭제 시도를 한 아이디가 없습니다");
				return null;
			}
		}
	}
