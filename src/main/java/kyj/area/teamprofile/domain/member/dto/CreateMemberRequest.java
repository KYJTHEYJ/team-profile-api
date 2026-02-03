package kyj.area.teamprofile.domain.member.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kyj.area.teamprofile.domain.member.entity.Mbti;

public record CreateMemberRequest(
        @NotBlank(message = "이름은 필수 입니다")
        @Size(max = 15, message = "이름은 15자리 까지 입력 가능합니다")
        String name
        , @NotNull(message = "나이는 필수 입니다")
        @Max(value = 999, message = "나이는 3자리수 까지 입력 가능합니다")
        Integer age
        , @NotNull(message = "MBTI 필수 입니다")
        Mbti mbti
) {
}
