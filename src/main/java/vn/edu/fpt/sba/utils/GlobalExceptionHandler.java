package vn.edu.fpt.sba.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.fpt.sba.exceptions.AlbumNotFoundException;
import vn.edu.fpt.sba.exceptions.ArtistNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArtistNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse artistNotFoundException(ArtistNotFoundException ex) {
        return new APIResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse albumNotFoundException(AlbumNotFoundException ex) {
        return new APIResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
