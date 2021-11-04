package com.esucri.projetox.adapters.exceptions;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.domain.utils.text.TextoUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Objects;

@ControllerAdvice
@RestController
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(value = ErrorWarningMessageException.class)
  public ResponseEntity<GenericResponseDTO> handleNoSuchElementException(
      ErrorWarningMessageException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(GenericResponseDTO.builder().error(e.getErrorWarningMessage()).build());
  }

  @ExceptionHandler(value = UnprocessableJsonException.class)
  public ResponseEntity<GenericResponseDTO> handleUnprocessableJsonException(
      UnprocessableJsonException e) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(GenericResponseDTO.builder().error(e.getErrorWarningMessage()).build());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var errors = new ArrayList<ErrorWarningField>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              var rejectedValue = error.getRejectedValue();
              var errorValue = Objects.isNull(rejectedValue) ? "null" : rejectedValue.toString();

              errors.add(
                  new ErrorWarningField(
                      TextoUtils.camelCaseToSnakeCase(error.getField()),
                      messageSource.getMessage(error, LocaleContextHolder.getLocale()),
                      errorValue));
            });

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(
            GenericResponseDTO.builder()
                .error(
                    new ErrorWarningMessage(
                        ErrorMessage.E000.getCode(), ErrorMessage.E000.getMessage(), errors))
                .build());
  }
}
