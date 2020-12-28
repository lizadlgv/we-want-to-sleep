package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private final JButton buttonCreateTFunction = new JButton("Создать табулированную функцию из массивов");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton inputButtonMath = new JButton("Создать табулированную функцию с помощью другой функции");
    private final JButton openButton = new JButton("Открыть функцию");
    private final JButton saveButton = new JButton("Сохранить функцию");
    private final ArrayList<Double> xValues = new ArrayList<>();
    private final ArrayList<Double> yValues = new ArrayList<>();
    private final MainWindowTableModel tableModel = new MainWindowTableModel();
    private final JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public MainWindow() {
        super("Основное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 1200, 500);
        getContentPane().add(buttonCreateTFunction);
        getContentPane().add(inputButtonMath);
        getContentPane().add(buttonSettings);
        getContentPane().add(openButton);
        getContentPane().add(saveButton);
        setLocationRelativeTo(null);
        compose();
        addButtonListeners();
        setVisible(true);
    }

    public void wrapTable(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCreateTFunction)
                        .addComponent(inputButtonMath)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonCreateTFunction)
                        .addComponent(inputButtonMath)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        buttonCreateTFunction.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TableWindow.main(factory, tableModel::setFunction);
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorWindow(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> new MathFuncWindow());
        buttonSettings.addActionListener(event -> {
            try {
                SettingsWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(tableModel::setFunction);
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
        saveButton.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
