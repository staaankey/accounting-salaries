package staaankey.group.accountingsalaries.minio.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.minio.exception.ResourceNotFoundException;
import staaankey.group.accountingsalaries.minio.exception.ResourceWriteException;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(ResourceNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ResourceWriteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String writeOperationHandler(ResourceWriteException exception) {
        return exception.getMessage();
    }

}


