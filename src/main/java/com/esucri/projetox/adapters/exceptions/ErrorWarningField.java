package com.esucri.projetox.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorWarningField implements Serializable {

    private String field;
    private String message;
    private String value;

    public ErrorWarningField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
