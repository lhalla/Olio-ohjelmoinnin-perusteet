package demo2bonus;

/**
 * UPDATE:
 * Kirja perii nyt Tuote-luokan, jolloin Verkkokaupan tuotteet-lista (ArrayList<Tuote>) voi sil viittauksia niin Kirja-olioihin kuin Puhelin-olioihinkin. (Molemmilla on sama ylluokka: Tuote.)
 */
public class Kirja extends Tuote{
	private final int julkaisuvuosi;

	public Kirja(String nimi, int julkaisuvuosi){
		super(Tuotekoodi.KIRJA, nimi);
		this.julkaisuvuosi = julkaisuvuosi;
	}

	//UPDATE:
	//haeNimi()-metodi on Tuote-luokassa, joka peritn.

	public int haeJulkaisuvuosi() {
		return julkaisuvuosi;
	}

}
