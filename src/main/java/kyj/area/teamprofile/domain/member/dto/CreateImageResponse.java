package kyj.area.teamprofile.domain.member.dto;

public record CreateImageResponse(
        Long id
        , Long memberId
        , String imageUrl
) {
}
