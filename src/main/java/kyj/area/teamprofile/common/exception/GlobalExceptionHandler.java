package kyj.area.teamprofile.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import kyj.area.teamprofile.common.dto.MainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kyj.area.teamprofile.common.exception.Message.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private void printBasicInfo(String message, HttpServletRequest request, Exception e) {
        log.error("[API - LOG] Request URL: {}, {}", request.getMethod(), request.getRequestURL().toString());
        log.error("[API - LOG] Error: {}", message);
        log.error("[API - LOG] Error Info: {}", e.toString());
    }

    @ExceptionHandler(value = ServiceErrorException.class)
    public ResponseEntity<MainResponse<Void>> handleException(ServiceErrorException e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request, e);
        return ResponseEntity.status(e.getStatus()).body(MainResponse.fail(e.getStatus().name(), e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<MainResponse<Void>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        printBasicInfo(e.getAllErrors().get(0).getDefaultMessage(), request, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MainResponse.fail(HttpStatus.BAD_REQUEST.name(), e.getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<MainResponse<Void>> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MainResponse.fail(HttpStatus.BAD_REQUEST.name(), MSG_DATA_INTEGRITY_VIOLENCE));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<MainResponse<Void>> HttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request, e);

        // Enum Json 역직렬화 시 에러 발생 처리 위함 (HttpMessageNotReadableException 이 래핑)
        if(e.getCause().getCause() instanceof ServiceErrorException serviceErrorException) {
            printBasicInfo(serviceErrorException.getMessage(), request, e);
            return ResponseEntity.status(serviceErrorException.getStatus()).body(MainResponse.fail(serviceErrorException.getStatus().name(), serviceErrorException.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MainResponse.fail(HttpStatus.BAD_REQUEST.name(), MSG_NOT_READABLE_VALUE));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MainResponse<Void>> exceptionHandler(Exception e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MainResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.name(), MSG_SERVER_ERROR));
    }
}
