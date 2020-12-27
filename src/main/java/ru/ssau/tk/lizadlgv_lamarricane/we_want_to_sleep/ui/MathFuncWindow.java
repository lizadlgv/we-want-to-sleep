package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class MathFuncWindow extends JDialog {
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JLabel fromLabel = new JLabel("От:");
    private final JLabel toLabel = new JLabel("До:");
    private final JLabel countLabel = new JLabel("Количество:");
    private final JTextField countField = new JTextField();
    private final JTextField fromField = new JTextField();
    private final JTextField toField = new JTextField();
    private final JButton okButton = new JButton("OK");
    private final Map<String, MathFunction> nameFunctionMap = new HashMap<>();

    TabulatedFunctionFactory factory;
    TabulatedFunction function;

    public MathFuncWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        this.factory = factory;
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners(callback);
        setLocationRelativeTo(null);
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        MathFuncWindow app = new MathFuncWindow(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Единичная функция", new UnitFunction());
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Тождественная функция", new IdentityFunction());
        nameFunctionMap.put("Функция деления на 2", new DivisionFunction());
        nameFunctionMap.put("Функция корня четвертой степени", new FourthRootFunction());
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                MathFunction mathFunction = nameFunctionMap.get(func);
                function = new ArrayTabulatedFunctionFactory().create(mathFunction, from, to, count);
                callback.accept(function);
                this.dispose();
            } catch (Exception e) {
                ErrorWindow errorWindow = new ErrorWindow(this, e);
                errorWindow.showErrorWindow(this, e);
            }
        });
    }
}
