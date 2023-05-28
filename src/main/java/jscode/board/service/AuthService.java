package jscode.board.service;

import jscode.board.config.jwt.JwtTokenProvider;
import jscode.board.domain.Member;
import jscode.board.domain.RefreshToken;
import jscode.board.dto.jwt.TokenDto;
import jscode.board.dto.jwt.TokenRequestDto;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.exception.jwt.InvalidRefreshTokenException;
import jscode.board.exception.jwt.InvalidTokenException;
import jscode.board.exception.jwt.LogoutException;
import jscode.board.exception.member.EmailAlreadyExistsException;
import jscode.board.repository.MemberRepository;
import jscode.board.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto req) {
        validateSignUpInfo(req.getEmail());
        Member member = memberRepository.save(req.toEntity(passwordEncoder));
        return MemberResponseDto.toDto(member);
    }

    @Transactional
    public TokenDto login(MemberRequestDto req) {
        UsernamePasswordAuthenticationToken authenticationToken = req.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto req) {

        if (!jwtTokenProvider.validateToken(req.getRefreshToken())) {
            throw new InvalidRefreshTokenException();
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(req.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(LogoutException::new);

        if (!refreshToken.getValue().equals(req.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return tokenDto;
    }

    private void validateSignUpInfo(String email) {
        if (memberRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException();
        }
    }
}

