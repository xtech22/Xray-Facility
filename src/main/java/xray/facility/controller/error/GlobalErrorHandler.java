package xray.facility.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	 														 
		 
	   
		
		
		@ExceptionHandler(NoSuchElementException.class)				//Returns a NoSuchElementException message.
		@ResponseStatus(code = HttpStatus.NOT_FOUND)				//The NoSuchElementException parameter
		public Map<String, String> handleNoSuchElementExeption(		// converted to a string value.
		NoSuchElementException e) {
		log.error("Exception:{}", e.toString());
		return Map.of("message", e.toString());
		}
	}
			

