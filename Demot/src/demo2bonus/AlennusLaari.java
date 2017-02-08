package demo2bonus;

//Annetaan geneeriselle tyypille rajoite; Alennuslaariin voi asettaa vain Tuote-luokasta tai sen aliluokista tehtyj olioita.
public class AlennusLaari<T extends Tuote> {

	//viittauksen tyyppi T on mritelty luokan signatuurissa. Muuten geneerisyys toimii kuten muutkin muuttujien tyypit. vrt. "private final int[] alennushinnat";
	//Mik ajonaikainen tyyppi T tulee olemaan, mritelln aina, kun uusi alennuslaari-olio luodaan. Verkkokaupassa alennuslaareja luodaan vain Verkkokaupan konstruktorissa.
	private final T[] alennustuotteet;

	public AlennusLaari(T[] taulukko){
		alennustuotteet = taulukko;
	}

	public boolean onkoAlennuksessa(T tuote){
		for(T tavara : alennustuotteet){
			if(tavara == null)
				continue;

			//T on joko Tuote-tyyppinen tai sen aliluokan tyyppinen, joten metodia haeNimi voidaan kutsua.
			if(tuote.haeNimi().equals(tavara.haeNimi()))
				return true;
		}
		return false;
	}

	public void asetaAlennukseen(T tuote){
		for(int i=0; i< alennustuotteet.length; i++){
			if(alennustuotteet[i] == null){
				alennustuotteet[i] = tuote;
				break;
			} 
		}  
	}

	public void poistaAlennuksesta(T tuote){
		for(int i=0; i< alennustuotteet.length; i++){
			T tavara = alennustuotteet[i];
			if(tuote.haeNimi().equals(tavara.haeNimi())){
				alennustuotteet[i] = null;
				break;
			}
		}
	}
}