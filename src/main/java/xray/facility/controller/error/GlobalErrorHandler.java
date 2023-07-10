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
	 //Returns a NoSuchElementException message. 
		//The NoSuchElementException parameter 
	   // converted to a string value.
		
		
		@ExceptionHandler(NoSuchElementException.class)
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		public Map<String, String> handleNoSuchElementExeption(
		NoSuchElementException e) {
		log.error("Exception:{}", e.toString());
		return Map.of("message", e.toString());
		}
	}
			  //log.info("NoSuchElementException occurred: {}", e.toString());
			 //Map<String, String> message = new HashMap<>();
			//message.put("message", e.toString());
		   //return message;
	      //404 not found status. Log the error using the SLF4J logger. 

