import inputData.WprowadzenieDanych;
import loggic.UmowaFactory;
import loggic.Umowy;
import outputData.WyswietlanieDanych;

public class KalkulatorPodatkowyApk {
    public static void main(String[] args) {
        WprowadzenieDanych daneKlienta = new WprowadzenieDanych();

        daneKlienta.setDaneOdKlienta(UmowaFactory.getMapaUmow());

        Umowy umowa = UmowaFactory.stworzUmowe(daneKlienta.getTypUmowy(), daneKlienta.getDochod());
        if(umowa == null)
            return;
        umowa.oblicz();
        WyswietlanieDanych.pokazDane(umowa);
    }
}
