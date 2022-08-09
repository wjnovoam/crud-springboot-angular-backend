package com.wjnovoa.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DocumetPersonExistsException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    private String nameOfField;
    private String valueOfCampo;
    public DocumetPersonExistsException(String nameOfField, String valueOfCampo) {
        super(String.format("El %s '%s' ya esta registrado", nameOfField, valueOfCampo));
        this.nameOfField = nameOfField;
        this.valueOfCampo = valueOfCampo;
    }
}