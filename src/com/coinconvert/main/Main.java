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
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        String[] currenciesFrom = {"MXN", "USD", "MXN", "CAD", "USD", "CAD"};
        String[] currenciesTo = {"USD", "MXN", "CAD", "MXN", "CAD", "USD"};

        JComboBox<String> currencyComboBox = new JComboBox<>(new String[]{
                "MXN to USD",
                "USD to MXN",
                "MXN to CAD",
                "CAD to MXN",
                "USD to CAD",
                "CAD to USD"
        });
        frame.add(currencyComboBox);

        JLabel amountLabel = new JLabel("Amount:");
        frame.add(amountLabel);

        JTextField amountTextField = new JTextField();
        frame.add(amountTextField);

        JButton convertButton = new JButton("Convert");
        frame.add(convertButton);

        JLabel resultLabel = new JLabel();
        frame.add(resultLabel);

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

        frame.setVisible(true);
    }
}
