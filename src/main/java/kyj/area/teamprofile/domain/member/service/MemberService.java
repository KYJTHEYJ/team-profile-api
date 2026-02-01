package kyj.area.teamprofile.domain.member.service;

import jakarta.validation.Valid;
import kyj.area.teamprofile.common.exception.ErrorEnum;
import kyj.area.teamprofile.common.exception.ServiceErrorException;
import kyj.area.teamprofile.domain.member.dto.CreateMemberRequest;
import kyj.area.teamprofile.domain.member.dto.CreateMemberResponse;
import kyj.area.teamprofile.domain.member.dto.SearchMemberResponse;
import kyj.area.teamprofile.domain.member.entity.Member;
import kyj.area.teamprofile.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = Member.register(request.name(), request.age(), request.mbti());
        Member savedMember = memberRepository.save(member);

        return new CreateMemberResponse(
                savedMember.getId()
                , savedMember.getName()
                , savedMember.getAge()
                , savedMember.getMbti()
        );
    }

    @Transactional(readOnly = true)
    public SearchMemberResponse searchMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MEMBER));

        return new SearchMemberResponse(
                member.getName()
                , member.getAge()
                , member.getMbti()
        );
    }
}
