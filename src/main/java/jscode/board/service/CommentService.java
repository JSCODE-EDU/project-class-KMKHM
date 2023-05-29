package jscode.board.service;

import jscode.board.domain.Board;
import jscode.board.domain.Comment;
import jscode.board.domain.Member;
import jscode.board.dto.comment.CommentRequestDto;
import jscode.board.dto.comment.CommentResponseDto;
import jscode.board.exception.board.BoardNotFoundException;
import jscode.board.exception.member.NotFoundMemberException;
import jscode.board.repository.BoardRepository;
import jscode.board.repository.CommentRepository;
import jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public CommentResponseDto createComment(CommentRequestDto req, Long id) {
        Member member = memberRepository.findById(jwtAuth()).orElseThrow(NotFoundMemberException::new);
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        Comment comment = commentRepository.save(new Comment(req.getContents(), member, board));
        return CommentResponseDto.toDto(comment);
    }

    public Long jwtAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Long id = Long.parseLong(name);
        return id;
    }
}
