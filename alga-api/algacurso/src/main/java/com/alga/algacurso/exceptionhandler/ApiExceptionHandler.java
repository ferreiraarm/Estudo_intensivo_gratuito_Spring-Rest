package com.alga.algacurso.exceptionhandler;

import com.alga.algacurso.domain.exception.NegException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("um ou mais campos invalidos");
        problemDetail.setType(URI.create("https://problemas.com/erros/campos-invalidos"));

        var fields = ex.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(error -> ((FieldError) error).getField(),
               error->messageSource.getMessage(error, LocaleContextHolder.getLocale())));

                // DefaultMessageSourceResolvable::getDefaultMessage)); mudado para APIEXCEPTIONHANDLER


        problemDetail.setProperty("fieldes", fields);


        return super.handleExceptionInternal(ex,problemDetail, headers, status, request);
    }

//    @ExceptionHandler(NegException.class)
//    public ResponseEntity<String> capturar(NegException e){
//        return ResponseEntity.badRequest()
//                .body(e.getMessage());
//
//    }

    @ExceptionHandler(NegException.class)
    public ProblemDetail handleNegocio(NegException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://problemas.com/erros/regra-de-negocio"));

        return problemDetail;

      //  return ResponseEntity.badRequest().body(e.getMessage());

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handDataIntegrityViolation(DataIntegrityViolationException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://problemas.com/erros/recurso-em-uso"));


    return problemDetail;
    }
}
