package com.shopping.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UtilisateurException extends RuntimeException{
    public UtilisateurException(String s) {
        super(s);
    }
}
