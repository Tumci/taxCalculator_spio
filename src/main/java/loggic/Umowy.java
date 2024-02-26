package loggic;

import java.text.DecimalFormat;

public abstract class Umowy {
    private final double PROCENT_SKLADKA_EMERYTALNA = 9.76;
    private final double PROCENT_SKLADKA_RENTOWA = 1.5;
    private final double PROCENT_SKLADKA_CHOROBOWA = 2.45;
    public static final double PROCENT_SKLADKA_ZDROWOTNA = 9.00;
    public static final double PROCENT_SKLADKA_ZDROWOTNA_POMNIEJSZA_PODATEK = 7.75;
    private final double PROCENT_ZALICZKA_NA_PODATEK_DOCHODOWY = 18;

    private final double dochod;
    private double skladkaEmerytalna;
    private double skladkaRentowa;
    private double skladkaChorobowa;

    private double skladkaZdrowotna;
    private double skladkaZdrowotnaPomniejszajacaPodatek;
    private double podstawaOpodatkowania;
    private double podstawaOpodatkowaniaLiczbaCalkowita;

    private double zaliczkaNaPodatekDochodowy;
    private double zaliczkaDoUrzeduSkarbowego;
    private double zaliczkaDoUrzeduSkarbowegoLiczbaCalkowita;
    private double wartoscPodstawyWynagrodzenia;
    private double wynagrodzenie;
    private double podatekPotracony;
    private String wyswietlKosztUzyskaniaPrzychodu;

    public Umowy(double dochod) {
        this.dochod = dochod;
    }
    public double getSkladkaEmerytalna() {
        return skladkaEmerytalna;
    }
    public double getSkladkaRentowa() {
        return skladkaRentowa;
    }
    public double getSkladkaChorobowa() {
        return skladkaChorobowa;
    }
    public double getSkladkaZdrowotna() {
        return skladkaZdrowotna;
    }
    public double getDochod() {
        return dochod;
    }
    public double getSkladkaZdrowotnaPomniejszajacaPodatek() {return skladkaZdrowotnaPomniejszajacaPodatek;}
    public double getPodstawaOpodatkowania(){return podstawaOpodatkowania;}
    public double getPodstawaOpodatkowaniaLiczbaCalkowita(){return podstawaOpodatkowaniaLiczbaCalkowita;}
    public double getZaliczkaNaPodatekDochodowy() {return zaliczkaNaPodatekDochodowy;}
    public double getWartoscPodstawyWynagrodzenia() {return wartoscPodstawyWynagrodzenia;}
    public double getPodatekPotracony() {return podatekPotracony;}
    public double getWynagrodzenie() {
        return wynagrodzenie;
    }
    public String getWyswietlKosztUzyskaniaPrzychodu() {
        return wyswietlKosztUzyskaniaPrzychodu;
    }
    public double getZaliczkaDoUrzeduSkarbowego() {
        return zaliczkaDoUrzeduSkarbowego;
    }
    public double getZaliczkaDoUrzeduSkarbowegoLiczbaCalkowita() {
        return zaliczkaDoUrzeduSkarbowegoLiczbaCalkowita;
    }

    static double format_liczbaCalkowita(double liczba){
        DecimalFormat format_liczbaCalkowita = new DecimalFormat("#");
        return Double.parseDouble(format_liczbaCalkowita.format(liczba));
    }

    public void oblicz() {
        wartoscPodstawyWynagrodzenia = obliczPodstaweWynagrodzenia(dochod);
        obliczUbezpieczenia(wartoscPodstawyWynagrodzenia);

        double kosztyUzyskaniaPrzychodu = this.obliczKosztyUzyskaniaPrzychodu();
        if(this instanceof UmowaOPrace)
            wyswietlKosztUzyskaniaPrzychodu = "Koszty uzyskania przychodu w stałej wysokości " + kosztyUzyskaniaPrzychodu;
        else if (this instanceof UmowaZlecenie)
            wyswietlKosztUzyskaniaPrzychodu = "Koszty uzyskania przychodu (stałe) " + kosztyUzyskaniaPrzychodu;

        podstawaOpodatkowania = this.obliczPodstaweOpodatkowania();
        podstawaOpodatkowaniaLiczbaCalkowita = format_liczbaCalkowita(podstawaOpodatkowania);
        zaliczkaNaPodatekDochodowy = obliczZaliczkeNaPodatekDochodowy(podstawaOpodatkowaniaLiczbaCalkowita);

        podatekPotracony = this.obliczPodatekPotracony();

        zaliczkaDoUrzeduSkarbowego = obliczZaliczkeDoUrzeduSkarbowego();
        zaliczkaDoUrzeduSkarbowegoLiczbaCalkowita = format_liczbaCalkowita(zaliczkaDoUrzeduSkarbowego);

        wynagrodzenie = dochod - ((skladkaEmerytalna + skladkaRentowa + skladkaChorobowa) + skladkaZdrowotna + zaliczkaDoUrzeduSkarbowegoLiczbaCalkowita);
    }

    public double obliczPodstaweWynagrodzenia(double podstawaWymiaruSkladki){
        skladkaEmerytalna = (podstawaWymiaruSkladki * PROCENT_SKLADKA_EMERYTALNA) / 100;
        skladkaRentowa = (podstawaWymiaruSkladki * PROCENT_SKLADKA_RENTOWA) / 100;
        skladkaChorobowa = (podstawaWymiaruSkladki * PROCENT_SKLADKA_CHOROBOWA) / 100;
        return (podstawaWymiaruSkladki - skladkaEmerytalna - skladkaRentowa - skladkaChorobowa);
    }

    public double obliczZaliczkeNaPodatekDochodowy(double podstawaOpodatkowaniaLiczbaCalkowita){
        return (podstawaOpodatkowaniaLiczbaCalkowita * PROCENT_ZALICZKA_NA_PODATEK_DOCHODOWY) / 100;
    }

    public void obliczUbezpieczenia ( double podstawaWymiaruSkladki){
        skladkaZdrowotna = (podstawaWymiaruSkladki * PROCENT_SKLADKA_ZDROWOTNA) / 100;
        skladkaZdrowotnaPomniejszajacaPodatek = (podstawaWymiaruSkladki * PROCENT_SKLADKA_ZDROWOTNA_POMNIEJSZA_PODATEK) / 100;
    }

    abstract double obliczKosztyUzyskaniaPrzychodu();
    abstract double obliczPodstaweOpodatkowania();
    abstract double obliczPodatekPotracony();
    abstract double obliczZaliczkeDoUrzeduSkarbowego();
}
