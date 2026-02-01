package kyj.area.teamprofile.domain.member.dto;

public record CreateMemberResponse(
        Long id
        , String name
        , Integer age
        , String mbti
) {
}
