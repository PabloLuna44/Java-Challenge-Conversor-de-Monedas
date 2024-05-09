package com.coinconvert.main;

import com.coinconvert.models.ApiController;
import com.coinconvert.models.Calculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        // Paneles para organizar los componentes
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String[] currenciesFrom = {"MXN", "USD", "MXN", "CAD", "USD", "CAD"};
        String[] currenciesTo = {"USD", "MXN", "CAD", "MXN", "CAD", "USD"};

        // Componentes
        JLabel currencyLabel = new JLabel("Currency:");
        JComboBox<String> currencyComboBox = new JComboBox<>(new String[]{
                "MXN to USD",
                "USD to MXN",
                "MXN to CAD",
                "CAD to MXN",
                "USD to CAD",
                "CAD to USD"
        });

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountTextField = new JTextField();

        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel();

        // Establecer disposición horizontal
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(currencyLabel)
                .addComponent(amountLabel));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(currencyComboBox)
                .addComponent(amountTextField)
                .addComponent(convertButton)
                .addComponent(resultLabel));
        layout.setHorizontalGroup(hGroup);

        // Establecer disposición vertical
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(currencyLabel)
                .addComponent(currencyComboBox));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(amountLabel)
                .addComponent(amountTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(convertButton));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resultLabel));
        layout.setVerticalGroup(vGroup);

        // Acción del botón de conversión
        DecimalFormat df = new DecimalFormat("#.##");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCurrency = (String) currencyComboBox.getSelectedItem();
                int index = currencyComboBox.getSelectedIndex();
                double amount = Double.parseDouble(amountTextField.getText());
                try {
                    double value = ApiController.consultCurrency(currenciesFrom[index], currenciesTo[index]);
                    double total = Calculation.calculationCurrency(amount, value);
                    resultLabel.setText(df.format(amount) + " " + currenciesFrom[index] + " = " + df.format(total) + " " + currenciesTo[index]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultLabel.setText("Error occurred while converting.");
                }
            }
        });

        // Agregar el panel al frame
        frame.add(panel);

        frame.setVisible(true);
    }
}
