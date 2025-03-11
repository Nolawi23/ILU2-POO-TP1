package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
//		Etal etal = new Etal();
//		etal.libererEtal();

//		Etal etal = new Etal();
//		Gaulois gaulois = new Gaulois("nom", 5);
//		etal.occuperEtal(gaulois, "fleurs", 5);
//		etal.acheterProduit(1, null);

//		Etal etal = new Etal();
//		Gaulois gaulois = new Gaulois("nom", 5);
//		etal.occuperEtal(gaulois, "fleurs", 5);
//		try {
//			etal.acheterProduit(0, gaulois);
//		} catch (IllegalArgumentException e) {
//			System.out.println("La quantite doit etre strictement positive");
//		}
		

		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("nom", 5);
		try {
			etal.acheterProduit(5, gaulois);
		}catch(IllegalStateException e) {
			System.out.println("L'etal n'est pas occupé");
		}

		System.out.println("Fin du test");
	}
}
