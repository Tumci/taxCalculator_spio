package loggic;

import java.util.Map;
import java.util.TreeMap;

public class UmowaFactory {
    private static Map<Integer, String> mapaUmow;

    public static Map<Integer, String> getMapaUmow(){
        mapaUmow = new TreeMap<>();
        mapaUmow.put(1, UmowaOPrace.PROMPT);
        mapaUmow.put(2, UmowaZlecenie.PROMPT);
        return mapaUmow;
    }
    public static Umowy stworzUmowe(char typUmowy, double dochod){
        if (UmowaOPrace.SKROT == typUmowy) {
            System.out.println("UMOWA O PRACĘ");
            return new UmowaOPrace(dochod);
        } else if (UmowaZlecenie.SKROT == typUmowy) {
            System.out.println("UMOWA-ZLECENIE");
            return new UmowaZlecenie(dochod);
        } else {
            System.out.println("Nieznany typ umowy!");

            //TODO ten return jest do zmiany na zgłoszenie błędu
            return null;
        }
    }
}
