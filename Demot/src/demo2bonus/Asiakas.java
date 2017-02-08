package demo2bonus;

import java.util.ArrayList;

public class Asiakas {

	private String nimi;
	private int varallisuus;

	/**
	 * UPDATE:
	 * Asiakkaalla on nyt lista hnen omistamistaan asioista, niiden yksittin listaamisen sijaan.
	 * Attribuuttien yksittinen listaus helpottaa tietysti varmistumista jonkin asian omistamisesta; nyt esimerkiksi selvittminen, onko tll oliolla kirjaa on hankalampi:
	 * tytyy looppaa listan lpi ja katsoa, onko listassa yhtn Kirja-tyyppist oliota. Aikaisemmin riitti tarkistus "omaKirja != null".
	 * Toisaalta, listan kyttminen mys mahdollistaa sen, ett asiakas voi omistaa monta puhelinta ja kirjaa, yksittinlistaus ei salli tt. Joskus kuitenkin voi olla jrkev est asiakkaan monen asian omistaminen (vaikkapa alennuskuponki?). 
	 * Molemmilla ratkaisuilla on siis omat hyvt ja huonot puolensa.
	 * 
	 * aikaisemmin siis
	 * private Puhelin omaPuhelin = null;
	 * private Kirja omaKirja = null;
	 * nyt:
	 */

	ArrayList<Tuote> omistamatTuotteet = new ArrayList<>();

	//Tm on vain olion itsens tiedossa; ei haku/muutosmetodeja. Tt tietoa kuitenkin tarvitaan kun asiakas valitsee itselleen mieluisaa puhelinta.
	private String mieluisaPuhelin;
	private String mieluisaKirja;

	public Asiakas(String nimi, int rahat, String mieluisaPuhelin, String mieluisaKirja){
		this.nimi = nimi;
		varallisuus = rahat;
		this.mieluisaPuhelin = mieluisaPuhelin;
		this.mieluisaKirja = mieluisaKirja;
	}

	/*
	 * GETTERIT JA SETTERIT
	 */
	public String annaNimi(){
		return nimi;
	}

	public int annaVarallisuus() {
		return varallisuus;
	}

	public void maksa(int tuotteenHinta) {
		varallisuus -= tuotteenHinta;
	}

	/**
	 * UPDATE:
	 * Premium-asiakkaiden ilmestymisen myt mys tavallisilla asiakkailla on alennusprosentti (aina 0)
	 * @return alennus prosentteina. 0 <= alennus <= 100
	 */
	public int annaAlennusProsentti(){
		return 0;
	}

	/**
	 * UPDATE:
	 * aikaisemmin tarvitsimme erilliset metodit erilaisten tuotteiden asettamiseksi.
	 * Nyt riitt tm metodi, jolla asiakas saa uusia omistamiaan tuotteita.
	 * @param p tuote, joka listn. p != null
	 */
	public void hankiTavara(Tuote p){
		//varmistetaan nyt viel alkuehdon toteutuminen...
		if(p != null){
			omistamatTuotteet.add(p);
		}
	}

	/*
	 * Seuraavat metodit mahdollistavat olion toimimisen mallintamassamme verkkokaupassa
	 */

	public int etsiMieluinenPuhelin(ArrayList<Tuote> katalogi) {
		/*
		 * Koska eri olioilla voi olla eri mieluisa puhelinvalmistaja, palautettu indeksi riippuu 
		 * -siit verkkokauppaoliosta, jonka katalogi annetaan tlle parametrina (eri kaupoissa mahdollisesti eri tavarat, ehk eri jrjestyksess)
		 * -konstruktorissa annetusta mieluisasta puhelinvalmistaja.
		 */

		return haeTuote(katalogi, mieluisaPuhelin);
	}

	public int etsiMieluinenKirja(ArrayList<Tuote> katalogi) {
		return haeTuote(katalogi, mieluisaKirja);
	}

	/**
	 * UPDATE: Nyt kirjan ja puhelimen etsimismetodi suorittaakin haeTuote-metodin, joka hakee tuotteista olion haluaman tuotteen sen nimen perusteella.
	 * Jos siis kaupasta ei lydy tuotetta juuri asiakkaan haluamalla nimell, palautetaan -1.
	 * 
	 */

	private int haeTuote(ArrayList<Tuote> tuotteet, String nimi){
		for(int i=0; i<tuotteet.size(); i++){
			Tuote t = tuotteet.get(i);
			if(t.haeNimi().equals(nimi)){
				return i;
			}     
		}
		//Jos mieluisaa ei lydy, palautetaan negatiivinen arvo. Kaupan ostometodin alkuehdoissa ilmoitetaan, ett negatiivinen indeksi tarkoittaa ei ostotapahtumaa.
		return -1;
	}

	/**
	 * UPDATE:
	 * kaikkien tmn asiakkaan omistamien tuotteiden listaus onnistuu yksinkertaisella for-loopilla.
	 * Koska jokaisella tuotteella on nimi, tulostetaan se.
	 */
	public void tulostaOmistetutTavarat() {
		for(Tuote t: omistamatTuotteet){
			System.out.println(t.haeNimi());
		}
	}

	/**
	 * UPDATE:
	 * toString-metodi on Object-luokassa ja sit kutsutaan aina, kun viittaustyyppinen muuttuja tulostetaan.
	 * Sen tarkoitus on palauttaa merkkijono, joka kuvastaa oliota. Asiakas-oliolle siis luonnollinen merkkijonoesitys on asiakkaan nimi, varallisuus ja hnen omistamansa tavarat
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("Asiakas "+nimi);

		sb.append("\nOmistamat tuotteet:\n");
		for(Tuote t: omistamatTuotteet){
			sb.append("  ");
			sb.append(t.haeNimi());
			sb.append("\n");
		}

		sb.append("Varallisuutta on "+varallisuus);

		return sb.toString();
	}
}
