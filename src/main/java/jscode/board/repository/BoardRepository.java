package jscode.board.repository;

import jscode.board.domain.Board;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByHeadContaining(String head, Pageable pageable);
}
