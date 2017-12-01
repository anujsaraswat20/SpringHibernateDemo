package com.hcl.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is responsible for returning error responses to caller.
 * It's the Spring way of handling exceptions from the project.
 * All the exceptions from controller layer terminates here, error response 
 * is created and sent. 
 */
@ControllerAdvice
public class SpringControllerAdvice {

    /**
     * Method to handle exception of type CustomException thrown from Spring controller
     * @param request HTTP request
     * @param customException exception with necessary reason
     * @return Error response with valid error code and message  
     */
    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, 
        CustomException customException) {
        int statusCode = customException.getStatusCode();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(statusCode);
        errorResponse.setMessage(customException.getStatusMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, new HttpHeaders(), 
            HttpStatus.valueOf(statusCode));
    }
}
