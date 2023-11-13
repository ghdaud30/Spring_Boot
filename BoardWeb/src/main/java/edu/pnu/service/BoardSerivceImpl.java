package edu.pnu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardSerivceImpl implements BoardService{
	@Autowired
	private BoardRepository boardRepo;
	
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	
	public void updateBoard(Board board) {
		Optional<Board> option = boardRepo.findById(board.getSeq());
		if(!option.isPresent()) {
			System.out.println("No board data");
			return;
		}
		else {
			Board findBoard = option.get();
			findBoard.setTitle(board.getTitle());
			findBoard.setContent(board.getContent());
			boardRepo.save(findBoard);
		}
	}
	
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
	
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}
	
	public Page<Board> getBoardList(Board board){
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"seq");
		return boardRepo.getBoardList(pageable);
	}
}
