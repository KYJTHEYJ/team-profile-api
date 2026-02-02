package kyj.area.teamprofile.domain.member.controller;

import jakarta.validation.Valid;
import kyj.area.teamprofile.common.dto.MainResponse;
import kyj.area.teamprofile.domain.member.dto.*;
import kyj.area.teamprofile.domain.member.service.ImageS3Service;
import kyj.area.teamprofile.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ImageS3Service imageS3Service;

    @PostMapping
    public ResponseEntity<MainResponse<CreateMemberResponse>> createMember(@Valid @RequestBody CreateMemberRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED).body(MainResponse.success(HttpStatus.CREATED.name(), memberService.createMember(request)));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MainResponse<SearchMemberResponse>> searchMember(@PathVariable Long memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(MainResponse.success(HttpStatus.OK.name(), memberService.searchMember(memberId)));
    }

    @PostMapping("/{memberId}/profile-image")
    public ResponseEntity<MainResponse<CreateImageResponse>> createImage(
            @PathVariable Long memberId
            , @RequestParam("image") MultipartFile image) {
        String key = imageS3Service.getUploadKey(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(MainResponse.success(HttpStatus.CREATED.name(), memberService.createImage(memberId, key)));
    }

    @GetMapping("/{memberId}/profile-image")
    public ResponseEntity<MainResponse<SearchImageResponse>> searchImage(@PathVariable Long memberId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MainResponse.success(HttpStatus.OK.name(), memberService.searchImage(memberId)));
    }
}
