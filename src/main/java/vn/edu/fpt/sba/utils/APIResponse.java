package vn.edu.fpt.sba.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class APIResponse {
    private int status;
    private LocalDateTime timestamp;
    private String message = null;
    private Object data = null;

    public APIResponse(int status) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public APIResponse(int status, String message) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public APIResponse(int status, Object data) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public APIResponse(int status, String message, Object data) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }
}
