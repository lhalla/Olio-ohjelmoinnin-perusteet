package demo2bonus;

/**
 * Tuote on mm. Puhelin- ja Kirja-luokkien ylluokka. Koska kaikilla verkkokaupan tuotteilla on tuotekoodi, attribuutti kannattaa siis list Tuote-luokkaan, jotta se peritn.
 * Tuote on mys abstrakti, koska kaupassahan ei voi olla vain tuotteita. Tuotteenhan tytyy oikeasti olla jotakin konkreettista: kirja, puhelin, karkkia, kodinkone yms... Kaikki nm oikeat tuotteet siis perivt tmn luokan.  
 */
public abstract class Tuote {

	//protected, jotta aliluokat voivat kytt attribuuttia suoraan
	//final, koska tuotteen tuotekoodi ei voi muuttua
	protected final int tuotekoodi;
	protected final String nimi;

	public Tuote(int koodi, String nimi){
		tuotekoodi = koodi;
		this.nimi = nimi;
	}

	public int haeTuotekoodi(){
		return tuotekoodi;
	}

	/**
	 * Kaikilla tuotteilla on jokin nimi
	 * @return tuotteen nimi
	 */
	public String haeNimi(){
		return nimi;
	}

	//Tm sisluokka on protected, koska vain tuotteilla (siis tll luokalla ja tmn aliluokilla; Puhelin ja Kirja) tulisi olla psy thn sisluokkaan ja sen vakioihin.
	protected class Tuotekoodi{
		public static final int KIRJA = 1;
		public static final int PUHELIN = 3;
	}
}
