package jscode.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jscode.board.dto.member.MemberRequestDto;
import jscode.board.dto.member.MemberResponseDto;
import jscode.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Member Api")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@RequestHeader("Authorization") String token) {
        MemberResponseDto result = memberService.getMemberInfo(token.substring(7));
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
