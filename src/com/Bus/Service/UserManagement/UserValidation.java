package com.Bus.Service.UserManagement;

import com.Bus.Model.Client.Utilisateur;

/*
 * Classe utilitaire pour valider les attributs d'un utilisateur Utilisateur.
 * 
 * Les méthodes statiques de cette classe permettent de vérifier que tous les champs essentiels
 * (nom, prénom, adresse, NIP, type, email) sont correctement remplis avant l'enregistrement ou la modification
 * d'un utilisateur dans le système.
 */
public class UserValidation {
	
	/**
 	 * Effectue la validation générale des attributs d'un utilisateur
 	 * 
 	 * @param u - L'utilisateur à valider
 	 * @throws UserValidationException - Si un ou plusieurs champs sont vides ou invalides
 	 */
	public static void generalValidation(Utilisateur u) throws UserValidationException {
		checkNom(u.getNom());
		checkPrenom(u.getPrenom());
		checkAdresse(u.getAdresse());
		checkNip(u.getNip());
		checkType(u.getType());
		checkEmail(u.getEmail());
	}
	
	/**
 	 * Vérifie que le nom n'est pas vide
 	 * 
 	 * @param nom - Le nom à valider
 	 * @throws UserValidationException - Si le nom est vide
 	 */
	private static void checkNom(String nom) throws UserValidationException {
		if (! isEmpty(nom)) {
			return;
		} else {
			throw new UserValidationException("Empty name not allowed");
		}
	}
	
	/**
 	 * Vérifie que le prénom n'est pas vide
 	 * 
 	 * @param prenom - Le prénom à valider
 	 * @throws UserValidationException - Si le prénom est vide
 	 */
	private static void checkPrenom(String prenom) throws UserValidationException {
		if (! isEmpty(prenom)) {
			return;
		} else {
			throw new UserValidationException("Empty prenom not allowed");
		}
	}

	/**
     * Vérifie que l'adresse n'est pas vide
     *
     * @param adresse - L'adresse à valider
     * @throws UserValidationException - Si l'adresse est vide
     */
	private static void checkAdresse(String adresse) throws UserValidationException {
		if (! isEmpty(adresse)) {
			return;	
		} else {
			throw new UserValidationException("Empty adresse not allowed");
		}
	}

	/**
     * Vérifie que le NIP n'est pas vide
     *
     * @param nip - Le NIP à valider
     * @throws UserValidationException - Si le NIP est vide
     */
	private static void checkNip(String nip) throws UserValidationException {
		if (! isEmpty(nip)) {
			return;
		} else {
			throw new UserValidationException("Empty nip not allowed");
		}
	}

	/**
     * Vérifie que le type d'utilisateur n'est pas vide
     *
     * @param type - Le type à valider
     * @throws UserValidationException - Si le type est vide
     */
	private static void checkType(String type) throws UserValidationException {
		if (! isEmpty(type)) {
			return;
		} else {
		throw new UserValidationException("Empty type not allowed");
		}
	}

	/**
     * Vérifie que l'adresse email n'est pas vide
     *
     * @param email - L'email à valider
     * @throws UserValidationException - Si l'email est vide
     */
	private static void checkEmail(String email) throws UserValidationException {
		if (! isEmpty(email)) {
			return;
		} else {
			throw new UserValidationException("Empty email not allowed");
		}
	}
	
	/**
     * Vérifie si une chaîne est vide (longueur ≤ 0)
     *
     * @param str - La chaîne à vérifier
     * @return true si la chaîne est vide, false sinon
     */
	private static boolean isEmpty(String nom) {
		return nom.length()<=0;
	}

}
