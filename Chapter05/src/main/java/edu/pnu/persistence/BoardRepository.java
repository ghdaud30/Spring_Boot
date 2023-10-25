package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String searchkeyword);
	List<Board> findByContentContaining(String searchkeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable Paging);
	
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyWord% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(@Param("searchKeyWord") String searchKeyword);
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyWord") String searchKeyword);
}
