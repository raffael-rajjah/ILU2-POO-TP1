package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			
			return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			
			int nbEtal = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					nbEtal++;
				}
			}

			Etal[] etalsAvecProduit = new Etal[nbEtal];
			
			int etalsCompteur = 0;
			for(int i = 0; i < etalsAvecProduit.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etals[etalsCompteur] = etals[i];
					etalsCompteur++;
				}
			}
			
			return etalsAvecProduit;
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
				
			}
			
			return null;
		}
		
		void afficherMarche() {
			
			int nbEtalLibre = 0;
			
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					System.out.println(etals[i].afficherEtal());
				}
				
				else {
					nbEtalLibre++;
				}
				
			}
			
			System.out.println("Il reste " + nbEtalLibre + " �tals non utilis�s dans le march�");
		}
		
	}

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}