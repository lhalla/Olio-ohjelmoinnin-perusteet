package demo2bonus;

/**
 * UPDATE:
 * Puhelin perii nyt Tuote-luokan, jolloin Verkkokaupan tuotteet-lista (ArrayList<Tuote>) voi sil viittauksia niin Puhelin-olioihin kuin Kirja-olioihinkin. (Molemmilla on sama ylluokka: Tuote.)
 */
public class Puhelin extends Tuote {
	private String malli;
	private String valmistaja;

	public Puhelin(String malli, String valmistaja){
		//Kaikkien puhelimien tuotekoodi on 3, joka on tallennettu luokkavakiona Tuote-luokan sisluokkaan Tuotekoodi.  
		super(Tuotekoodi.PUHELIN, valmistaja+" "+malli);
		this.malli = malli;
		this.valmistaja = valmistaja;
	}

	public String haeMalli() {
		return malli;
	}

	public String haeValmistaja() {
		return valmistaja;
	}


}
