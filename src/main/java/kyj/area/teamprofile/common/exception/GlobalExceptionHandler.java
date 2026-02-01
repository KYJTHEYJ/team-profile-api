package kyj.area.teamprofile.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import kyj.area.teamprofile.common.dto.MainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private void printBasicInfo(String message, HttpServletRequest request) {
        log.error("[API - LOG] Request URL: {}, {}", request.getMethod(), request.getRequestURL().toString());
        log.error("[API - LOG] Error: {}", message);
    }

    @ExceptionHandler(value = ServiceErrorException.class)
    public ResponseEntity<MainResponse<Void>> handleException(ServiceErrorException e, HttpServletRequest request) {
        printBasicInfo(e.getMessage(), request);
        return ResponseEntity.status(e.getStatus()).body(MainResponse.fail(e.getStatus().name(), e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<MainResponse<Void>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        printBasicInfo(e.getAllErrors().get(0).getDefaultMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MainResponse.fail(HttpStatus.BAD_REQUEST.name(), e.getAllErrors().get(0).getDefaultMessage()));
    }
}
