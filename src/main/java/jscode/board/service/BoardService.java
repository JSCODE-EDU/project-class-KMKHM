package jscode.board.service;

import jscode.board.domain.Board;
import jscode.board.dto.BoardRequestDto;
import jscode.board.dto.BoardResponseDto;
import jscode.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class    BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto req) {
        Board board = boardRepository.save(req.toEntity());
        BoardResponseDto boardResponseDto = BoardResponseDto.toDto(board);
        return boardResponseDto;
    }

    @Transactional(readOnly = true)
    public List<Board> findAllBoards() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "createDate"));
        List<Board> boards = boardRepository.findAll(pageable).getContent();
        boards.forEach(e -> BoardResponseDto.toDto(e));
        return boards;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findBoard(Long id) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(Exception::new);
        return BoardResponseDto.toDto(board);
    }


    @Transactional
    public BoardResponseDto editBoard(Long id, BoardRequestDto req) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(Exception::new);
        board.editBoard(req);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void deleteBoard(Long id) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(Exception::new);
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public List<Board> findBoardByHead(String head) {
        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "createDate"));
        List<Board> results = boardRepository.findByHeadContaining(head, pageable);
        results.forEach(e -> BoardResponseDto.toDto(e));
        return results;
    }

}
