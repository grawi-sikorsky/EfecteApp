/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@SpringBootTest
class GlobalExceptionHandlerTest {
	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	void handleNoteNotFound() {
		String requestURI = "/notes/999";
		String exceptionMsg = "NOT FOUND";
		NoteNotFoundException exception = new NoteNotFoundException(exceptionMsg);
		MockHttpServletRequest httpRequest = new MockHttpServletRequest(HttpMethod.GET.name(), requestURI);

		ResponseEntity<ErrorResponse> actualResponseEntity = globalExceptionHandler.handleNoteNotFound(exception, httpRequest);

		String expectedPath = httpRequest.getRequestURI();
		ErrorResponse errorResponse = actualResponseEntity.getBody();
		assertNotNull(errorResponse.getTimestamp());
		assertEquals(expectedPath, errorResponse.getPath());
		assertEquals(exception.getMessage(), errorResponse.getMessage());
		assertEquals(NOT_FOUND.value(), errorResponse.getStatus());
		assertEquals(NOT_FOUND, actualResponseEntity.getStatusCode());
		assertEquals(NOT_FOUND.getReasonPhrase(), errorResponse.getError());
	}

	@Test
	void buildErrorResponse() {
	}
}