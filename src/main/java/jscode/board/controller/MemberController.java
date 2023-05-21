package jscode.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Member Api")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입 기능", notes = "이메일과 비밀번호를 입력받아 게시글 생성")
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody MemberRequestDto req) {
        MemberResponseDto member = memberService.signup(req);
        return new ResponseEntity(member, HttpStatus.CREATED);
    }
}
