package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2425645922517094756L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
