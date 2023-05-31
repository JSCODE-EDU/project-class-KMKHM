package jscode.board.repository;


import jscode.board.domain.LikeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeBoard, Long> {
    Optional<LikeBoard> findByBoardIdAndMemberId(Long memberId, Long boardId);

}
