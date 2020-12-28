package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TableWindow extends JDialog {
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Введите количество точек:");
    private final JTextField countField = new JTextField();
    private final JButton inputButton = new JButton("Ввести");
    private final JButton createButton = new JButton("Создать");
    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public TableWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        super();
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new FlowLayout());
        this.setBounds(300, 300, 500, 500);
        this.factory = factory;
        addButtonListeners(callback);
        compose();
        setLocationRelativeTo(null);
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        TableWindow app = new TableWindow(factory, callback);
        app.setVisible(true);
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
    }

    private void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        inputButton.addActionListener(event -> {
            try {
                createButton.setEnabled(true);
                int count = Integer.parseInt(countField.getText());
                clearTable(tableModel.getRowCount());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
                if (tableModel.getRowCount() > 1) {
                    createButton.setEnabled(true);
                }
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
        createButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                x[0] = xValues.get(0);
                y[0] = yValues.get(0);
                for (int i = 1; i < xValues.size(); i++) {
                    if (xValues.get(i - 1) > xValues.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                function = new ArrayTabulatedFunctionFactory().create(x, y);
                callback.accept(function);
                this.dispose();
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
    }

//    public void addListenerForInputButton() {
//        inputButton.addActionListener(event -> {
//            try {
//                createButton.setEnabled(false);
//                int count = Integer.parseInt(countField.getText());
//                clearTable(tableModel.getRowCount());
//                for (int i = 0; i < count; i++) {
//                    xValues.add(0.);
//                    yValues.add(0.);
//                    tableModel.fireTableDataChanged();
//                }
//                if (tableModel.getRowCount() > 0) {
//                    createButton.setEnabled(true);
//                } else {
//                    new ErrorWindow(this, new DifferentLengthOfArraysException());
//                }
//            } catch (Exception e) {
//                new ErrorWindow(this, e);
//            }
//        });
//    }
//
//    public void addListenerForCreateButton() {
//        createButton.addActionListener(event -> {
//            try {
//                double[] x = new double[xValues.size()];
//                double[] y = new double[xValues.size()];
//                x[0] = xValues.get(0);
//                y[0] = yValues.get(0);
//                for (int i = 1; i < xValues.size(); i++) {
//                    if (xValues.get(i - 1) > xValues.get(i)) {
//                        throw new ArrayIsNotSortedException();
//                    }
//                    x[i] = xValues.get(i);
//                    y[i] = yValues.get(i);
//                }
//                function = new ArrayTabulatedFunctionFactory().create(x, y);
//                System.out.println(function.toString());
//                setVisible(true);
//                dispose();
//            } catch (Exception e) {
//                new ErrorWindow(this, e);
//            }
//        });
//    }
//
//    public void addListenerForCountButton() {
//        countField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                onChanged();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                onChanged();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                onChanged();
//            }
//
//            private void onChanged() {
//                inputButton.setEnabled(!countField.getText().isEmpty());
//            }
//        });
//    }

    public void clearTable(int n) {
        for (int i = 0; i < n; i++) {
            xValues.remove(n - i - 1);
            yValues.remove(n - i - 1);
            tableModel.fireTableDataChanged();
        }
    }
}
