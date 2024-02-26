package inputData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class WprowadzenieDanych {
    private double dochod;
    private char typUmowy;

    public double getDochod() {
        return this.dochod;
    }

    public char getTypUmowy() {
        return this.typUmowy;
    }

    public void setDaneOdKlienta(Map<Integer, String> mapaUmow) {
        try (InputStreamReader input = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(input);){

            System.out.print("Podaj kwotę dochodu: ");
            this.dochod = Double.parseDouble(reader.readLine());

            System.out.print("Typ umowy: ");
            for(Map.Entry<Integer, String> entry : mapaUmow.entrySet()) {
                System.out.print(entry.getValue());
                if (entry.getKey() == mapaUmow.size())
                    break;
                System.out.print(", ");
            }
            System.out.print(": ");
            this.typUmowy = reader.readLine().toLowerCase().charAt(0);
        } catch (Exception ex) {
            System.out.println("Błędne dane Klienta");
            System.err.println(ex);
        }
    }
}