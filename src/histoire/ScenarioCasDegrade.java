package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
//		try {
//			System.out.println("Fin du test");
//			
//		} catch (NullPointerException exception) {
//			System.out.println("l'étal est ɗéjà liberé");
//		}
//		Gaulois g = new Gaulois("dummyg", 0);
//		Etal etal = new Etal();
//		etal.libererEtal();
//		etal.acheterProduit(0, g);
		
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		village.ajouterHabitant(abraracourcix);
		village.afficherVillageois();

		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		
		Etal etalFleur = village.rechercherEtal(bonemine);
		System.out.println(etalFleur.acheterProduit(0, obelix));
		
	}
}
