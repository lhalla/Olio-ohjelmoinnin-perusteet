package demo2bonus;

import java.util.ArrayList;

public class Verkkokauppa {

	//UPDATE
	//myytvt tuotteet. Final-mre varmistaa, ett tuotteet-muuttujaan tallennettu arvo (viittaus olioon) ei koskaan muutu. ArrayList-olion sislt voi tietysti muuttua.
	private final ArrayList<Tuote> tuotteet;

	//UPDATE: Tss oma geneerinen luokka AlennusLaari, jossa silytetn verkkokaupassa alennuksessa olevat kirjat.
	private final AlennusLaari<Kirja> alennusKirjat;

	private int rahat;

	//Luokkamuuttuja. Jokainen verkkokauppa saa myyd tuotteita vain tll hinnalla.
	public static int tuotteenHinta = 30;

	//Taas final-mre, mutta tll kertaa arvomuuttujaan. Seuraus on se, ett perustamisvuosi-muuttujassa (tai oikeammin vakiossa) on aina konstruktorissa sille annettu arvo. 
	private final int perustamisvuosi;

	//nimi ei ole final-mreell varustettu. Se on kuitenkin private, jolloin muutos onnistuu vain asetusmetodin kautta. Koska nimen asetusmetodi puuttuu, nimikin on kytnnss vakio.
	//Final-mreen voisi list, jos haluaa selvsti ilmoittaa muille ohjelmojille tmn muuttujan olevan vakio.
	private String nimi;

	public Verkkokauppa(String nimi, int perustamisvuosi){
		this.nimi = nimi;
		this.perustamisvuosi = perustamisvuosi;
		tuotteet = new ArrayList<Tuote>();
		rahat = 0;

		//UPDATE: Tehdn geneerinen alennuslaari. Alennuslaarissa voi olla vain yhden tyyppisi asioita kerrallaan. Tll kertaa on kirjapivt.
		alennusKirjat = new AlennusLaari<Kirja>(new Kirja[2]);
	}

	public String haeNimi(){
		return nimi;
	}

	public int haePerustamisvuosi(){
		return perustamisvuosi;
	}


	/**
	 * UPDATE:
	 * Aikaisemmin tarvitsimme erilliset metodit eri tuotteiden lismiseksi. 
	 * Nyt riitt, ett lismme tuote-olion, joka voi siis oikeasti olla luotu joko new Puhelin(...) tai new Kirja(...). (Tst tarkemmin polymorfismi-luennolla.)
	 * Tm on mahdollista, koska Puhelin ja Kirja perivt Tuote-luokan. 
	 * 
	 * Lis annetun tuotteen kaupan 'hyllylle'
	 * @param tuote != null
	 */
	public void lisaaTuote(Tuote tuote){
		tuotteet.add(tuote);
		rahat -= tuotteenHinta;

		if(tuote instanceof Kirja){
			//UPDATE: Asetetaan kaikki kirjaoliot alennukseen ( jos vain alennuslaariin mahtuu).
			alennusKirjat.asetaAlennukseen((Kirja)tuote);
		}
	}

	/**
	 * Palauttaa kaikki verkkokaupassa myytvt tuotteet.  
	 * @return ArrayList, jossa kaikki kaupan tuotteet. 
	 */
	public ArrayList<Tuote> annaTuotekatalogi(){
		return tuotteet;
	}

	/**
	 * UPDATE:
	 * Aikaisemmin tarvitsimme mys kaksi osto-metodia (ja tuotteiden lisntyess olisimme tarvinneet paljon enemmn). Nyt riitt yksi kaikille mahdollisille myytville tuotteille.
	 * 
	 * @param indeksiKatalogissa halutun puhelimen indeksi katalogissa (kts. annaPuhelinKatalogi()). Jos annetaan indeksi listan ulkopuolelta tai negatiivinen indeksi, puhelinta ei myyd.
	 * @param ostaja Asiakas, joka on ostamassa puhelinta
	 * @return ostettu puhelin. Null jos puhelinta ei myyty.
	 */
	public Tuote ostaTuote(int indeksiKatalogissa, Asiakas ostaja){
		//Tarkistetaan, ett ostaja pystyy ostamaan tuotteen.
		if(ostaja.annaVarallisuus() < tuotteenHinta)
			return null;

		//Jos ostaja ei haluakaan puhelinta
		if(indeksiKatalogissa < 0 || indeksiKatalogissa > tuotteet.size())
			return null;

		//remove metodi poistaa alkion listasta ja palauttaa sen
		Tuote t = tuotteet.remove(indeksiKatalogissa);


		//UPDATE:
		//Tss lasketaan asiakkaan maksettava summa: PremiumAsiakas-olioilla on alennusprosentti, tavallisilla asiakkailla se on 0.
		//Tm verkkokauppa-olio (tai edes ohjelmoija) ei siis tied, onko ostaja-viittauksen pss oleva olio oikeasti PremiumAsiakas- vai Asiakas-luokasta luotu. 
		//Tm on kuitenkin tarkoituksenmukaista ja osa olio-ohjelmointia. Tst tarkemmin luennoilla polymorfismin yhteydess.
		//Polymorfismin idea lyhyesti: Koska PremiumAsiakas perii Asiakas-luokan, PremiumAsiakas-olioita voidaan ksitell mys Asiakas-olioina.
		int oikeaHinta = (int) (tuotteenHinta*((100-ostaja.annaAlennusProsentti())/100.0));
		this.rahat += oikeaHinta; 
		ostaja.maksa(oikeaHinta);

		return t;
	}

}
