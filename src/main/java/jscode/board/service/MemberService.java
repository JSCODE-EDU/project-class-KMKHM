package jscode.board.service;

import jscode.board.config.jwt.JwtTokenProvider;
import jscode.board.domain.Member;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.exception.member.EmailAlreadyExistsException;
import jscode.board.exception.member.NotFoundMemberException;
import jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public MemberResponseDto getMemberInfo(String token){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        Long id = Long.valueOf(authentication.getName());
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
        return MemberResponseDto.toDto(member);
    }

}
