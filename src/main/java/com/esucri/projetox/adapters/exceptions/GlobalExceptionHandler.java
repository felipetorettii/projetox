package com.esucri.projetox.adapters.exceptions;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value = ErrorWarningMessageException.class)
  public ResponseEntity<GenericResponseDTO> handleNoSuchElementException(
      ErrorWarningMessageException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(GenericResponseDTO.builder().error(e.getErrorWarningMessage()).build());
  }
}
