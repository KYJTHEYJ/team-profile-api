package kyj.area.teamprofile.domain.member.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateMemberRequest(
        @NotBlank(message = "이름은 필수 입니다")
        @Size(max = 15, message = "이름은 15자리 까지 입력 가능합니다")
        String name
        , @NotNull(message = "나이는 필수 입니다")
        @Max(value = 999, message = "나이는 3자리수 까지 입력 가능합니다")
        Integer age
        , @NotBlank(message = "MBTI 필수 입니다")
        @Size(min = 4, max = 4, message = "MBIT는 4글자 입니다")
        String mbti
) {
}
