package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner)  throws IllegalChart{

		System.out.println("Quel est le nom de la ville recherchée ? ");
		String choix = scanner.nextLine();
		List<Ville> villes = rec.getVilles();
		// Créez une variable pour suivre si un département valide a été trouvé
		boolean villeValide = false;
		
		for (Ville ville : villes) {
			if (ville.getNom().equalsIgnoreCase(choix)
					|| ville.getNom().toLowerCase().startsWith(choix.toLowerCase())) {
				System.out.println(ville);
				villeValide = true;
			}
			if (!villeValide) {
				throw new IllegalChart("Ville inconnu.");
			}
		}
	}

}
