package br.com.codenation.logstackapi.exception;

import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest r) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error(status.getReasonPhrase())
                .messsage(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> illegalArgument(IllegalArgumentException e, HttpServletRequest r) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error(status.getReasonPhrase())
                .messsage(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorResponseDTO> resourceIsException(ResourceExistsException e, HttpServletRequest r) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .error(status.getReasonPhrase())
                .messsage(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }
}
