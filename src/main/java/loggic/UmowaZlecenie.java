package loggic;
public class UmowaZlecenie extends Umowy{
    public static String PROMPT = "(Z)lecenie";
    public static char SKROT = 'z';
    public static final double KWOTA_ZMNIEJSZAJACA_PODATEK = 0;

    public UmowaZlecenie(double przychodBrutto) {
        super(przychodBrutto);
    }
    @Override
    double obliczKosztyUzyskaniaPrzychodu() {
        return (((this.getWartoscPodstawyWynagrodzenia() * 20) / 100));
    }
    @Override
    double obliczPodstaweOpodatkowania() {
        return (this.getWartoscPodstawyWynagrodzenia() - obliczKosztyUzyskaniaPrzychodu());
    }
    @Override
    double obliczPodatekPotracony(){
        return this.getZaliczkaNaPodatekDochodowy();
    }
    public double obliczZaliczkeDoUrzeduSkarbowego() {
        return (this.getZaliczkaNaPodatekDochodowy() - this.getSkladkaZdrowotnaPomniejszajacaPodatek() - KWOTA_ZMNIEJSZAJACA_PODATEK);
    }
}
