package kyj.area.teamprofile.domain.member.service;

import kyj.area.teamprofile.common.exception.ErrorEnum;
import kyj.area.teamprofile.common.exception.ServiceErrorException;
import kyj.area.teamprofile.domain.member.dto.*;
import kyj.area.teamprofile.domain.member.entity.Image;
import kyj.area.teamprofile.domain.member.entity.Mbti;
import kyj.area.teamprofile.domain.member.entity.Member;
import kyj.area.teamprofile.domain.member.repository.ImageRepository;
import kyj.area.teamprofile.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final ImageS3Service imageS3Service;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = Member.register(request.name(), request.age(), request.mbti());
        Member savedMember = memberRepository.save(member);

        return new CreateMemberResponse(
                savedMember.getId()
                , savedMember.getName()
                , savedMember.getAge()
                , savedMember.getMbti().name()
        );
    }

    @Transactional(readOnly = true)
    public SearchMemberResponse searchMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MEMBER));

        return new SearchMemberResponse(
                member.getName()
                , member.getAge()
                , member.getMbti().name()
        );
    }

    @Transactional
    public CreateImageResponse createImage(Long memberId, String key) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MEMBER));

        if (imageRepository.countByMember(member) >= 1) {
            throw new ServiceErrorException(ErrorEnum.ERR_MORE_ONE_IMAGE);
        }

        Image image = Image.register(member, key);
        Image savedImage = imageRepository.save(image);

        return new CreateImageResponse(
                savedImage.getId()
                , savedImage.getMember().getId()
                , savedImage.getImageKey()
        );
    }

    @Transactional(readOnly = true)
    public SearchImageResponse searchImage(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MEMBER));
        Image image = imageRepository.findByMember(member).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_IMAGE));
        URL downloadUrl = imageS3Service.getDownloadUrl(image.getImageKey());

        return new SearchImageResponse(
                downloadUrl
        );
    }
}
