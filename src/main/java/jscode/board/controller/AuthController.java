package jscode.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jscode.board.dto.jwt.TokenDto;
import jscode.board.dto.jwt.TokenRequestDto;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Auth Api")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "회원가입 기능", notes = "이메일과 비밀번호를 입력받아 회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody MemberRequestDto req){
        MemberResponseDto result = authService.signup(req);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "로그인 기능", notes = "이메일과 비밀번호를 입력받아 로그인")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto req) {
        TokenDto result = authService.login(req);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "토큰 재발급 기능", notes = "access token, refresh token 으로 토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto request){
        TokenDto tokenDto = authService.reissue(request);
        return ResponseEntity.ok(tokenDto);
    }
}
