package demo2bonus;

public class PremiumAsiakas extends Asiakas{

	private int alennus;

	public PremiumAsiakas(String nimi, int rahat, String mieluisaPuhelin, String mieluisaKirja, int alennusProsentti) {
		super(nimi, rahat, mieluisaPuhelin, mieluisaKirja);
		alennus = alennusProsentti;
	}

	public void asetaAlennusProsentti(int uusiProsentti){
		alennus = uusiProsentti;
	}

	/**
	 * UPDATE:
	 * Premium-asiakkailla on alennusprosentti. Tss siis ylikirjoitetaan (uudelleentoteutetaan) Asiakas-luokan alennusprosentin kyseleminen.
	 * Tavallisille asiakkaille aina 0, mutta tst luokasta tehtyjen olioiden alennusprosentti voikin olla jotakin muuta (tallennettu siis alennus-attribuuttiin)
	 */
	@Override
	public int annaAlennusProsentti() {
		return alennus;
	}
}
