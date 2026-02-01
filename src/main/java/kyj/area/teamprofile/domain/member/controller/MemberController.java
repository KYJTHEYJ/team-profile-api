package kyj.area.teamprofile.domain.member.controller;

import jakarta.validation.Valid;
import kyj.area.teamprofile.common.dto.MainResponse;
import kyj.area.teamprofile.domain.member.dto.CreateMemberRequest;
import kyj.area.teamprofile.domain.member.dto.CreateMemberResponse;
import kyj.area.teamprofile.domain.member.dto.SearchMemberResponse;
import kyj.area.teamprofile.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MainResponse<CreateMemberResponse>> createMember(@Valid @RequestBody CreateMemberRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED).body(MainResponse.success(HttpStatus.CREATED.name(), memberService.createMember(request)));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MainResponse<SearchMemberResponse>> searchMember(@PathVariable Long memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(MainResponse.success(HttpStatus.OK.name(), memberService.searchMember(memberId)));
    }
}
