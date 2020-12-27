package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.*;

import javax.swing.*;
import java.util.*;


public class SettingsWindow extends JDialog {
    private final JLabel label = new JLabel("Выберите тип фабрики функции:");
    private final JButton okButton = new JButton("OK");
    private final Map<String, TabulatedFunctionFactory> nameFunctionMap = new HashMap<>();
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    TabulatedFunctionFactory factory;

    public SettingsWindow(TabulatedFunctionFactory factory) {
        setModal(true);
        this.factory = factory;
        setTitle("Выберите тип фабрики");
        setSize(300, 100);
        fillMap();
        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
    }

    public static void main(TabulatedFunctionFactory factory) {
        SettingsWindow frame = new SettingsWindow(factory);
        frame.setVisible(true);
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(okButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
    }

    public void fillMap() {
        nameFunctionMap.put("Массив", new ArrayTabulatedFunctionFactory());
        nameFunctionMap.put("Связный список", new LinkedListTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void addButtonListeners() {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFunctionMap.get(func);
                this.dispose();
            } catch (Exception e) {
                ErrorWindow errorWindow = new ErrorWindow(this, e);
                errorWindow.showErrorWindow(this, e);
            }
        });
    }
}
