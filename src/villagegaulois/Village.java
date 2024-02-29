package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	 
 	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for(int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i] == null || !etals[i].isEtalOccupe()) {
					return i;
				}
			}
			
			return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			
			int nbEtal = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbEtal++;
				}
			}

			Etal[] etalsAvecProduit = new Etal[nbEtal];
			
			int etalsCompteur = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsAvecProduit[etalsCompteur] = etals[i];
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
			
			System.out.println("Il reste " + nbEtalLibre + " étals non utilisés dans le marché");
		}
		
	}

	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etalTrouve = marche.trouverEtals(produit);
		
		
		if(etalTrouve.length <= 0) {
			return "Il n'y a pas de vendeur qui propose des fleurs au marché.";
		}
		
		else if (etalTrouve.length == 1) {
			return "Seul le vendeur " + etalTrouve[0].getVendeur().getNom() + " propose des fleurs au marché.";
		}
		
		else {
			StringBuilder chaine = new StringBuilder();
			chaine.append("Les vendeurs qui proposent des fleurs sont : \n");
			
			for(int i = 0; i < etalTrouve.length; i++) {
				chaine.append("- " + etalTrouve[i].getVendeur().getNom() + "\n");
			}
			
			return chaine.toString();
		}
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return "Le vendeur " + vendeur.getNom() + " quitte son étal, il a vendu ";
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		
		int noEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(noEtal, vendeur, produit, nbProduit);
		
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs à l'étal n°" + (noEtal+1));
		return chaine.toString();	
		

	}
	
	public Village(String nom, int nbVillageoisMaximum, int nbMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbMarche);
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
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}