package kyj.area.teamprofile.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceErrorException extends RuntimeException {
    private final HttpStatus status;

    public ServiceErrorException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.status = errorEnum.getStatus();
    }
}
