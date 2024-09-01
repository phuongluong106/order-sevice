package vn.order.infrastructure.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.order.application.enums.OnlineResponseCode;
import vn.order.application.response.OnlineResponse;
import vn.order.shared.utils.LogHelper;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> validationList = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(x->{
            String fieldName = ((FieldError) x).getField();
            validationList.put(fieldName, x.getDefaultMessage());
        });
        OnlineResponse response = OnlineResponse.builder()
                .responseCode(OnlineResponseCode.UnprocessableEntity.getCode())
                .responseMessage("Invalid Request Body!")
                .errors(validationList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        
        logException(ex);
        OnlineResponse response = OnlineResponse.builder()
                .responseCode(OnlineResponseCode.SystemError.getCode())
                .responseMessage("SYSTEM_ERROR")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private void logException(Exception ex) {
        
        LogHelper.logException(getClass(), ex, null);
        LogHelper.error(getClass(), "[EXCEPTION] type={}, message={}", ex.getClass().getName(), ex.getMessage(), ex);
    }
}
