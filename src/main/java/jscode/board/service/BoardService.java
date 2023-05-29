package jscode.board.service;

import jscode.board.config.jwt.JwtTokenProvider;
import jscode.board.domain.Board;
import jscode.board.domain.Member;
import jscode.board.dto.board.BoardRequestDto;
import jscode.board.dto.board.BoardResponseDto;
import jscode.board.exception.board.BoardNotFoundException;
import jscode.board.exception.member.NotFoundMemberException;
import jscode.board.repository.BoardRepository;
import jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Long id = Long.parseLong(name);
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
        Board board = boardRepository.save(new Board(req.getHead(), req.getHead(), member));
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
    public BoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardResponseDto.toDto(board);
    }


    @Transactional
    public BoardResponseDto editBoard(Long id, BoardRequestDto req) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.editBoard(req);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
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
