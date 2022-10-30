package com.record.management.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.record.management.dto.GlobalResponse;
import com.record.management.exceptions.BadRequestException;
import com.record.management.exceptions.DuplicateRoleException;
import com.record.management.exceptions.DuplicateRolePermissionException;
import com.record.management.exceptions.DuplicateUserException;
import com.record.management.exceptions.DuplicateUserPermissionException;
import com.record.management.exceptions.IllegalFileFormatException;
import com.record.management.exceptions.RoleNotFoundException;
import com.record.management.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<GlobalResponse> handleException(DuplicateUserException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<GlobalResponse> handleException(BadRequestException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<GlobalResponse> handleException(UserNotFoundException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<GlobalResponse> handleException(RoleNotFoundException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateUserPermissionException.class)
	public ResponseEntity<GlobalResponse> handleException(DuplicateUserPermissionException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<GlobalResponse> handleException(DuplicateRoleException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateRolePermissionException.class)
	public ResponseEntity<GlobalResponse> handleException(DuplicateRolePermissionException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalFileFormatException.class)
	public ResponseEntity<GlobalResponse> handleException(IllegalFileFormatException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<GlobalResponse> handleException(IOException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalResponse> handleException(Exception exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse(exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<GlobalResponse> handleException(AccessDeniedException exception) {
		return new ResponseEntity<GlobalResponse>(new GlobalResponse("Access is denind to user.", null),
				HttpStatus.UNAUTHORIZED);
	}

}
