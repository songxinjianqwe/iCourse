package com.sinjinsong.icourse.common.advice;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.domain.RestError;
import com.sinjinsong.icourse.common.exception.security.RestAccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
	
	@ExceptionHandler(BaseRestException.class)
	public ResponseEntity<RestError> handleBaseRestException(BaseRestException e) {
		return new ResponseEntity<>(new RestError(e.getStatus(), e.getCode(), e.getErrors(), e.getMoreInfoURL()), e.getStatus());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestError> handle(RuntimeException e) {
	    e.printStackTrace();
	    log.info("cause: {}",e.getCause());
	    if(e.getCause() instanceof  BaseRestException){
	        return handleBaseRestException((BaseRestException) e.getCause());
        }
        if(e instanceof org.springframework.security.access.AccessDeniedException) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        BaseRestException exception = new RestAccessDeniedException(authentication.getAuthorities().toString());
	        return handleBaseRestException(exception);
        }
        return new ResponseEntity<>(new RestError(HttpStatus.INTERNAL_SERVER_ERROR,500,null,null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
