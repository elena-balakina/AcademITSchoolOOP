package ru.academits.balakina.view;

import ru.academits.balakina.controller.TemperatureConverter;
import ru.academits.balakina.model.TemperatureScale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TemperatureConverterView {
    private final TemperatureScale[] scales;

    public TemperatureConverterView(TemperatureScale[] scales) {
        this.scales = scales;
    }

    public void startView() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Not possible to set system type of interface");
            }

            Image icon = Toolkit.getDefaultToolkit().getImage("Temperature/src/ru/academits/balakina/icon.png");
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            // настройки панели
            GridBagConstraints constraints = new GridBagConstraints();

            // 1 строка
            // 1 ячейка
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 0.5; // на всю ширину панели
            constraints.weighty = 0.5;  // установить отступ
            constraints.insets = new Insets(0, 5, 0, 0);
            constraints.ipady = 15;   // высота строки

            constraints.gridy = 0;  // нулевая ячейка таблицы по вертикали
            JLabel labelInput = new JLabel("Введите температуру: ");
            constraints.gridx = 0;      // нулевая ячейка таблицы по горизонтали
            panel.add(labelInput, constraints);

            // 2 ячейка
            JTextField inputValue = new JTextField();
            constraints.gridx = 1;  // вторая ячейка таблицы по вертикали
            panel.add(inputValue, constraints);

            // 2 строка
            // 1 ячейка
            JLabel labelFomTemperature = new JLabel("Перевести из: ");
            constraints.gridy = 1;
            constraints.gridx = 0;
            panel.add(labelFomTemperature, constraints);

            // 2 ячейка
            JComboBox<TemperatureScale> menu1 = new JComboBox<>(scales);
            constraints.gridx = 1;
            panel.add(menu1, constraints);

            // 3 строка
            // 1 ячейка
            JLabel labelToTemperature = new JLabel("Перевести в: ");
            constraints.gridy = 2;
            constraints.gridx = 0;
            panel.add(labelToTemperature, constraints);

            // 2 ячейка
            JComboBox<TemperatureScale> menu2 = new JComboBox<>(scales);
            constraints.gridx = 1;
            panel.add(menu2, constraints);

            // 4 строка - кнопка Конвертировать
            JButton buttonConvert = new JButton("Конвертировать");
            buttonConvert.setSize(50, 20);
            constraints.gridy = 3;
            constraints.anchor = GridBagConstraints.CENTER;
            panel.add(buttonConvert, constraints);

            // 5 строка
            // 1 ячейка
            JLabel labelResult = new JLabel("Результат вычислений: ");
            constraints.gridy = 4;
            constraints.gridx = 0;
            panel.add(labelResult, constraints);

            // 2 ячейка
            JTextField resultValue = new JTextField();
            resultValue.setEditable(false);
            constraints.gridx = 1;
            panel.add(resultValue, constraints);

            // 6 строка - кнопка Очистить поля
            JButton buttonClean = new JButton("Очистить поля");
            buttonConvert.setSize(50, 20);
            constraints.gridy = 5;
            panel.add(buttonClean, constraints);

            JFrame frame = new JFrame("Temperature converter");

            // настройки размеров фрейма
            frame.setSize(400, 270);
            frame.setMinimumSize(new Dimension(400, 270));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setResizable(false);
            frame.setIconImage(icon);

            // добавление элементов на фрейм
            frame.add(panel, BorderLayout.PAGE_START);

            frame.setVisible(true);

            // Listeners
            buttonConvert.addActionListener(e -> {
                try {
                    TemperatureScale scaleFrom = (TemperatureScale) menu1.getSelectedItem();
                    TemperatureScale scaleTo = (TemperatureScale) menu2.getSelectedItem();
                    String inputString = inputValue.getText();
                    double inputTemperature = Double.parseDouble(inputString);

                    TemperatureConverter converter = new TemperatureConverter();
                    double outputTemperature = converter.convert(scaleFrom, scaleTo, inputTemperature);

                    resultValue.setText(String.valueOf(outputTemperature));
                    inputValue.requestFocusInWindow();
                } catch (NumberFormatException exception) {
                    resultValue.setText("ВВЕДИТЕ ЧИСЛО");
                    inputValue.requestFocusInWindow();
                }
            });

            inputValue.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            buttonConvert.doClick();
                        } catch (NumberFormatException exception) {
                            resultValue.setText("ВВЕДИТЕ ЧИСЛО");
                            inputValue.requestFocusInWindow();
                        }
                    }
                }
            });

            buttonClean.addActionListener(e -> {
                inputValue.setText("");
                resultValue.setText("");
                inputValue.requestFocusInWindow();
            });
        });
    }
}
