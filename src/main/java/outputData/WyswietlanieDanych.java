package outputData;

import loggic.Umowy;
import loggic.UmowaOPrace;
import java.text.DecimalFormat;

public class WyswietlanieDanych {
    static DecimalFormat format_liczbaSetna = new DecimalFormat("#.00");
    static DecimalFormat format_liczbaCalkowita = new DecimalFormat("#");
    public static void pokazDane(Umowy umowa) {
        System.out.println("Podstawa wymiaru składek " + umowa.getDochod());
        System.out.println("Składka na ubezpieczenie emerytalne "
                + format_liczbaSetna.format(umowa.getSkladkaEmerytalna()));
        System.out.println("Składka na ubezpieczenie rentowe    "
                + format_liczbaSetna.format(umowa.getSkladkaRentowa()));
        System.out.println("Składka na ubezpieczenie chorobowe  "
                + format_liczbaSetna.format(umowa.getSkladkaChorobowa()));

        System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
                + umowa.getWartoscPodstawyWynagrodzenia());

        System.out.println("Składka na ubezpieczenie zdrowotne: "
                + Umowy.PROCENT_SKLADKA_ZDROWOTNA + "% = "
                + format_liczbaSetna.format(umowa.getSkladkaZdrowotna()) + " "
                + Umowy.PROCENT_SKLADKA_ZDROWOTNA_POMNIEJSZA_PODATEK + "% = "
                + format_liczbaSetna.format(umowa.getSkladkaZdrowotnaPomniejszajacaPodatek()));

        System.out.println(umowa.getWyswietlKosztUzyskaniaPrzychodu());

        System.out.println("Podstawa opodatkowania " + umowa.getPodstawaOpodatkowania()
                + " zaokrąglona " + umowa.getPodstawaOpodatkowaniaLiczbaCalkowita());

        System.out.println("Zaliczka na podatek dochodowy 18 % = " + umowa.getZaliczkaNaPodatekDochodowy());

        if(umowa instanceof UmowaOPrace)
            System.out.println("Kwota wolna od podatku = " + UmowaOPrace.KWOTA_ZMNIEJSZAJACA_PODATEK);

        System.out.println("Podatek potrącony = " + format_liczbaSetna.format(umowa.getPodatekPotracony()));

        System.out.println("Zaliczka do urzędu skarbowego = "
                + format_liczbaSetna.format(umowa.getZaliczkaDoUrzeduSkarbowego()) + " po zaokrągleniu = "
            + format_liczbaCalkowita.format(umowa.getZaliczkaDoUrzeduSkarbowegoLiczbaCalkowita()));

        System.out.println();

        System.out.println("Pracownik otrzyma wynagrodzenie netto w wysokości = " + format_liczbaSetna.format(umowa.getWynagrodzenie()));
    }
}
