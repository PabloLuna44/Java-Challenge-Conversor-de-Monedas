import com.coinconvert.ApiController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String currency="";
        Scanner keyboard=new Scanner(System.in);
        System.out.println("Enter the currency");
        currency=keyboard.nextLine();
        String json=ApiController.consultApi(currency);
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        String[] arr={"MXN","USD","MXN","CAD","USD","CAD"};
        String[] arr2={"USD","MXN","CAD","MXN","CAD","USD"};

        System.out.println("""
                Enter currency that you wish convert
                ------------------------------------
                |   [1]MXN->USD                    |
                |   [2]USD->MXN                    |
                |   [3]MXN->CAD                    |
                |   [4]CAD->MXN                    |
                |   [5]USD->CAD                    |
                |   [6]CAD->USD                    |
                |   [7]Exit                        |
                ------------------------------------
                """);









        








    }
}