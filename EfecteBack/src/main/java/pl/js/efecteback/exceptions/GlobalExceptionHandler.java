package pl.js.efecteback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<String> handleBadOrder(NoteNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
