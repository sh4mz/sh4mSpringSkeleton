package net.sh4m.project.core;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = Logger.getLogger(RestResponseEntityExceptionHandler.class);
	
	public RestResponseEntityExceptionHandler() {
        super();
    }

	//capture below error exception and handling properly
	//can store to file or can put in DB for error monitoring
	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("error",ex);
        
        ResponseEntity<Object> resp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                contentType(MediaType.APPLICATION_JSON).body(ex);
        
        return resp;
    }
}
