package demo2bonus;

public class Ostotapahtuma {

	/*
	 * Tss luokassa on suoritettava main-metodi. Luokka suorittaa kahden asiakkaan ostotapahtuman.
	 * Pelkstn suoritaPuhelimenOstotapahtuma-metodin toteutusta muuttamalla, kuinka monta tapaa puhelimen varastamiselle keksit? 
	 * Eli 'osto'tapahtuman jlkeen varkaan varallisuus-muuttujan arvo on joko pysynyt samana tai jopa kasvanut.
	 */

	public static void main(String[] args){
		//Luodaan aluksi kauppa, jossa asiakkaat voivat kyd ja tytetn se tavaralla
		Verkkokauppa kauppa = new Verkkokauppa("ParasDiili", 2010);

		kauppa.lisaaTuote(new Kirja("Ohraa ruispellossa", 1951));
		kauppa.lisaaTuote(new Kirja("Anna Anastaasia", 1877));
		kauppa.lisaaTuote(new Kirja("Veden viem", 1936));

		kauppa.lisaaTuote(new Puhelin("Universum S7", "Mamsum"));
		kauppa.lisaaTuote(new Puhelin("pPuhelin7", "Tomato"));
		kauppa.lisaaTuote(new Puhelin("Jit 950", "MacroHard"));

		/*
		 * Luodaan muutama asiakas, jotka kyvt kaupassa.
		 * Sara yritt ostaa samaa kirjaa kuin Otso.
		 * Kaupassa on kuitenkin vain yksi Ohraa ruispellossa -kirja, joten ohjelman loputtua Sara j kirjatta.
		 */
		Asiakas asiakas = new Asiakas("Otso Ostaja", 200, "Mamsum Universum S7", "Ohraa ruispellossa");
		PremiumAsiakas toinenAsiakas = new PremiumAsiakas("Sara Shoppari", 200, "Tomato pPuhelin7", "Ohraa ruispellossa", 30);


		System.out.println("Ennen kaupankynti:");
		asiakas.tulostaOmistetutTavarat();
		toinenAsiakas.tulostaOmistetutTavarat();

		//Asiakkaat kyvt ostamassa itselleen mieluisan puhelimen
		suoritaPuhelimenOstotapahtuma(kauppa, asiakas);
		suoritaPuhelimenOstotapahtuma(kauppa, toinenAsiakas);

		System.out.println("Puhelimen oston jlkeen:");
		asiakas.tulostaOmistetutTavarat();
		toinenAsiakas.tulostaOmistetutTavarat();

		//Asiakkaat kyvt ostamassa itselleen mieluisan kirjan
		suoritaKirjanOstotapahtuma(kauppa, asiakas);
		suoritaKirjanOstotapahtuma(kauppa, toinenAsiakas);

		System.out.println("Kirjan oston jlkeen:");
		asiakas.tulostaOmistetutTavarat();
		toinenAsiakas.tulostaOmistetutTavarat();

		//nm printit siis kutsuvat toString-metodia.
		//toString-metodiahan kutsutaan aina, kun viittaustyyppinen muuttuja tulee muuttaa tekstimuotoiseksi (esim. tulostetaan tai katenoidaan toisen merkkijonon kanssa)
		System.out.println("Tulostetaan viel koko asiakas");
		System.out.println(asiakas);
		System.out.println();
		System.out.println(toinenAsiakas);
	}

	private static void suoritaPuhelimenOstotapahtuma(Verkkokauppa kauppa, Asiakas asiakas) {

//		Verkkokauppa.tuotteenHinta = -100;
		asiakas.maksa(-1 * Verkkokauppa.tuotteenHinta);
		
		int puhelimenIndeksi = asiakas.etsiMieluinenPuhelin(kauppa.annaTuotekatalogi());
		Puhelin p = (Puhelin) kauppa.ostaTuote(puhelimenIndeksi, asiakas);
		asiakas.hankiTavara(p);

	}

	private static void suoritaKirjanOstotapahtuma(Verkkokauppa kauppa, Asiakas asiakas) {

		int kirjanIndeksi = asiakas.etsiMieluinenKirja(kauppa.annaTuotekatalogi());
		Tuote k = kauppa.ostaTuote(kirjanIndeksi, asiakas);
		asiakas.hankiTavara(k);

	}
}
