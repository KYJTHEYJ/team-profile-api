package kyj.area.teamprofile.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static kyj.area.teamprofile.common.exception.Message.NOT_FOUND_MEMBER;

@Getter
public enum ErrorEnum {
    // region "Member 관련 Error"
    ERR_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, NOT_FOUND_MEMBER);
    // endregion

    private final HttpStatus status;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
