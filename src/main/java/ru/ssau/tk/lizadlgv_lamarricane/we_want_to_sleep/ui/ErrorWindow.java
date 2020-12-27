package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorWindow {
    ErrorWindow(Component parent, Exception e) {
        showErrorWindow(parent, e);
    }

    public void showErrorWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа";
        } else if (e instanceof DifferentLengthOfArraysException) {
            return "Неверное значение количства точек";
        } else if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован";
        } else if (e instanceof IOException) {
            return "Ошибка ввода/вывода";
        } else if (e instanceof InconsistentFunctionsException) {
            return "Разная длина массивов";
        } else if (e instanceof IllegalArgumentException) {
            return "Задана некорректная функция";
        }
        return "Неверные данные!";
    }
}
