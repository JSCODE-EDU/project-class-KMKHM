package jscode.board.service;

import jscode.board.domain.Member;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.exception.member.EmailAlreadyExistsException;
import jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto req) {
        validateSignUpInfo(req.getEmail());
        Member member = memberRepository.save(req.toEntity());
        return MemberResponseDto.toDto(member);
    }

    private void validateSignUpInfo(String email) {
        if (memberRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException();
        }
    }

}
