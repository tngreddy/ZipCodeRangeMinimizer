package com.wsi.zipcodes.exception;
/********************************************************************************************
 * Name			: InvalidInputException.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A exception handler class which handles the exceptions thrown in the application
 *******************************************************************************************/
import com.wsi.zipcodes.bean.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * A method which handles the InvalidInputException thrown by the application when the Input provided in Invalid
     * @param exception
     * @return a responseEntity object with the custom Error object set with an error message
     */
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleException(RuntimeException exception)  {
        return new ResponseEntity<>(new Error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
