package edu.pnu.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.board.BoardVO;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	BoardService boardService = new BoardService();
	
	@GetMapping("/board")
	public List<BoardVO> getBoards(){
		return boardService.getBoards();
	}
	
	@GetMapping("/board/{seq}")
	public BoardVO getBoard(@PathVariable Integer seq) {
		return boardService.getBoard(seq);
	}
	@GetMapping("/getboard")
	public BoardVO getBoard1( Integer seq) {
		return boardService.getBoard(seq);
	}
	
	@PostMapping("/board")
	public BoardVO addMember(BoardVO boardVO) {
		return boardService.addMember(boardVO);
	}
	
	@PutMapping("/board")
	public BoardVO updateMembers(BoardVO boardVO) {
		return boardService.updateMembers(boardVO);
	}
	
	@DeleteMapping("/board/{seq}")
	public BoardVO removeMember(@PathVariable Integer seq) {
		return boardService.removeMember(seq);
	}
	@DeleteMapping("/deleteboard")
	public BoardVO removeMember1( Integer seq) {
		return boardService.removeMember(seq);
	}
	
}
