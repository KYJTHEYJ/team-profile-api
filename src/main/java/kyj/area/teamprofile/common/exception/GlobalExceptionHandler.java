package kyj.area.teamprofile.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import kyj.area.teamprofile.common.dto.MainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kyj.area.teamprofile.common.exception.Message.MSG_SERVER_ERROR;

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MainResponse.fail(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MainResponse<Void>> exceptionHandler(Exception e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MainResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.name(), MSG_SERVER_ERROR));
    }
}
