package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws IllegalChart {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		if (!NumberUtils.isDigits(choix)) {
			throw new IllegalChart("Le code du département dois être des nombres.");
		}
		// Obtenez la liste des villes
		List<Ville> villes = rec.getVilles();

		// Créez une variable pour suivre si un département valide a été trouvé
		boolean departementValide = false;

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		// Condition qui verifie si la valeur contient bien un nombre
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new IllegalChart("Le min doit être un nombre.");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		// Condition qui verifie si la valeur contient bien un nombre
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new IllegalChart("Le max doit être un nombre.");
		}
		
		// convertie les valeur string en int
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		// condition si les valeur ne son pas en dessous de 0 et si le min est pas
		// superieur au max
		if (min < 0 || max < 0 || min > max) {
			throw new IllegalChart("Les bornes de population sont invalides.");
		}

		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
					departementValide = true;
				}
			}
		}
		System.out.println("\n");
		if (!departementValide) {
			throw new IllegalChart("Code département inconnu.");
		}
	}

}
