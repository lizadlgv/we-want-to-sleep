package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6427898332351487264L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
