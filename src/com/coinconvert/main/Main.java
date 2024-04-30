package com.coinconvert.main;

import com.coinconvert.models.ApiController;
import com.coinconvert.models.Calculation;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner keyboard=new Scanner(System.in);
        String[] arr={"MXN","USD","MXN","CAD","USD","CAD"};
        String[] arr2={"USD","MXN","CAD","MXN","CAD","USD"};
        DecimalFormat df = new DecimalFormat("#.##");
        int opc=0;
        while (opc != 6) {
            System.out.println("""
                    Enter currency that you wish convert
                    ------------------------------------
                    |   [0]MXN->USD                    |
                    |   [1]USD->MXN                    |
                    |   [2]MXN->CAD                    |
                    |   [3]CAD->MXN                    |
                    |   [4]USD->CAD                    |
                    |   [5]CAD->USD                    |
                    |   [6]Exit                        |
                    ------------------------------------
                    """);
                    opc = keyboard.nextInt();
            if(opc!=6) {
                System.out.println("Enter the value that you wish convert");
                double amount=keyboard.nextDouble();
                double value=ApiController.consultCurrency(arr[opc], arr2[opc]);
                double total= Calculation.calculationCurrency(amount,value);

                System.out.println(arr[opc] + ":$"+df.format(amount)+" -> " + arr2[opc] + ":$" + df.format(total));
            }

        }










        








    }
}