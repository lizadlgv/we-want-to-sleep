package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2561581671504521455L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
