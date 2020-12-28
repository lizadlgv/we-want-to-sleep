package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.ui;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.lang.*;

public class MainWindow extends JFrame {
    private final JButton buttonCreateTFunction = new JButton("Создать табулированную функцию");
    private JButton openButton = new JButton("Открыть");
    private JButton saveButton = new JButton("Сохранить");
    private final JButton inputButtonMath = new JButton("Создать математическую функцию");
    private final JButton buttonSettings = new JButton("Настройки");
    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();
    private MainWindowTableModel tableModel = new MainWindowTableModel();
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public MainWindow() {
        super("Главное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 1000, 500);
        getContentPane().add(buttonCreateTFunction);
        getContentPane().add(buttonSettings);
        getContentPane().add(openButton);
        getContentPane().add(inputButtonMath);
        getContentPane().add(saveButton);
        setLocationRelativeTo(null);
        compose();
        addButtonListeners();
        setVisible(true);
        setResizable(false);
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
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonCreateTFunction)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        inputButtonMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathFuncWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new Error (this, e);
            }
        });
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
                FileReader.main(data -> tableModel.setFunction(data));
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
        window.setResizable(false);
    }
}
