package pl.js.efecteback.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoteNotFound(NoteNotFoundException ex, HttpServletRequest httpRequest) {
		ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(), httpRequest, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	public static ErrorResponse buildErrorResponse(String exceptionMessage, HttpServletRequest httpRequest, HttpStatus httpStatus) {
		int status = httpStatus.value();
		String error = httpStatus.getReasonPhrase();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return new ErrorResponse(timestamp, status, error, exceptionMessage, httpRequest.getRequestURI());
	}
}
