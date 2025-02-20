package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				Etal etalI = new Etal();
				etals[i] = etalI;
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			int indiceEtal = -1;
			for (int i = 0; i < etals.length && indiceEtal == -1; i++) {
				if (!etals[i].isEtalOccupe()) {
					indiceEtal = i;
				}
			}
			return indiceEtal;
		}

		private Etal[] trouverEtals(String produit) {
			int max = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					max++;
				}
			}
			Etal[] etalProduits = new Etal[max];
			for (int i = 0, j = 0; i < etalProduits.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalProduits[j] = etals[i];
					j++;
				}
			}
			return etalProduits;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				Gaulois vendeur = etals[i].getVendeur();
				if (vendeur.getNom().equals(gaulois.getNom())) {
					return etals[i];
				}
			}
			return null;
		}

		private void afficherMarcher() {
			int nbEtalVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				} else {
					nbEtalVide++;
				}
			}
			System.out.println("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
		}

	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] etalProduit = marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		if (etalProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " aux marché.");
		}
		if (etalProduit.length == 1) {
			chaine.append("Seul le vendeur " + etalProduit[0].getVendeur().getNom() + " propose des " + produit
					+ " aux marché.");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			System.out.println(etalProduit.length);
			for (int i = 0; i < etalProduit.length; i++) {
				chaine.append("- " + etalProduit[i].getVendeur().getNom());
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		int indiceEtal = marche.trouverEtalLibre();
		if (indiceEtal != -1) {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			chaine.append(
					"Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (indiceEtal + 1) + ".");
		}
		return chaine.toString();
	}
}