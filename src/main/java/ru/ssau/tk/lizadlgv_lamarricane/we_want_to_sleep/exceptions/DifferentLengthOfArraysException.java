package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 437106622661620988L;

    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
