package loggic;

public class UmowaOPrace extends Umowy {
    public static String PROMPT = "(P)raca";
    public static char SKROT = 'p';
    private final double KOSZTY_UZYSKANIA_PRZYCHODU = 111.25;
    public static final double KWOTA_ZMNIEJSZAJACA_PODATEK = 46.33;

    public UmowaOPrace(double przychodBrutto) {
        super(przychodBrutto);
    }
    @Override
    double obliczKosztyUzyskaniaPrzychodu() {
        return KOSZTY_UZYSKANIA_PRZYCHODU;
    }
    @Override
    double obliczPodstaweOpodatkowania() {
        return this.getWartoscPodstawyWynagrodzenia() - obliczKosztyUzyskaniaPrzychodu();
    }
    @Override
    double obliczPodatekPotracony(){
        return this.getZaliczkaNaPodatekDochodowy() - KWOTA_ZMNIEJSZAJACA_PODATEK;
    }
    public double obliczZaliczkeDoUrzeduSkarbowego() {
        return this.getZaliczkaNaPodatekDochodowy() - this.getSkladkaZdrowotnaPomniejszajacaPodatek() - KWOTA_ZMNIEJSZAJACA_PODATEK;
    }
}
