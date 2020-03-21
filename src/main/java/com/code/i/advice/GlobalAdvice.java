package com.code.i.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalAdvice extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String responseMessage = "malformed JSON request";
        log.error("HTTP400 thrown to client ", ex);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, responseMessage, ex));
    }


    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        String responseMessage = "something went wrong";
        log.error("HTTP500 thrown to client ", ex);
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, responseMessage, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String responseMessage = "not found";
        logger.info("HTTP404 " + ex.getHttpMethod() + " " + ex.getRequestURL());
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, responseMessage, ex));
    }


    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        String responseMessage = ex.getConstraintViolations().stream().map(violation -> violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage()).reduce(" ", (a, b) -> a + ":" + b);
        logger.info("HTTP400 " + responseMessage);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, responseMessage, ex));
    }

//    @ExceptionHandler(value = { AccessDeniedException.class })
//    protected ResponseEntity<Object> handle(AccessDeniedException exception, WebRequest request) {
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            UserDetails user = (UserDetails) principal;
//            String username = user.getUsername();
//            String roles = user.getAuthorities().stream()
//                    .map(GrantedAuthority::toString)
//                    .reduce("", (a, b) -> a + " " + b).trim();
//
//            HttpServletRequest req = ((ServletWebRequest) request).getRequest();
//            String url = req.getMethod() + " " + req.getRequestURI();
//            log.info("HTTP-403 user '{}' with role '{}' attempted to access '{}'", username, roles, url);
//        } else {
//            log.warn("HTTP-403 principal: {}", principal.toString());
//        }
//
//        return handleExceptionInternal(exception, "unauthorized", new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
}
