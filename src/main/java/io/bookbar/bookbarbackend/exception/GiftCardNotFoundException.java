package io.bookbar.bookbarbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GiftCardNotFoundException extends RuntimeException{
    public GiftCardNotFoundException(String msg){
        super(msg);
    }
}
