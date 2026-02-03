package kyj.area.teamprofile.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static kyj.area.teamprofile.common.exception.Message.*;

@Getter
public enum ErrorEnum {
    //region "Member 관련 Error"
    ERR_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_MEMBER)
    , ERR_NOT_FOUND_MBTI(HttpStatus.BAD_REQUEST, MSG_NOT_FOUND_MBTI)
    //endregion

    //region "Image 관련 Error"
    , ERR_NOT_FOUND_IMAGE(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_IMAGE)
    , ERR_MORE_ONE_IMAGE(HttpStatus.BAD_REQUEST, MSG_MORE_ONE_IMAGE)
    , ERR_BIGGER_10MB_IMAGE(HttpStatus.BAD_REQUEST, MSG_BIGGER_10MB_IMAGE)
    , ERR_UNSUPPORTED_IMAGE(HttpStatus.BAD_REQUEST, MSG_UNSUPPORTED_IMAGE)
    , ERR_UPLOAD_IMAGE(HttpStatus.BAD_REQUEST, MSG_UPLOAD_IMAGE_ERROR)
    //endregion

    //region "Server 관련 Error"
    , ERR_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, MSG_SERVER_ERROR);
    //endregion

    private final HttpStatus status;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
