package com.Bus.Service.UserManagement;


import com.Bus.Model.Client.Utilisateur;

public class UserValidation {
	
	public static void generalValidation(Utilisateur u) throws UserValidationException {
		checkNom(u.getNom());
		checkPrenom(u.getPrenom());
		checkAdresse(u.getAdresse());
		checkNip(u.getNip());
		checkType(u.getType());
		checkEmail(u.getEmail());
	}
	private static void checkNom(String nom) throws UserValidationException {
		if (! isEmpty(nom)) {
			return;
		} else {
			throw new UserValidationException("Empty name not allowed");
		}
	}
	
	private static void checkPrenom(String prenom) throws UserValidationException {
		if (! isEmpty(prenom)) {
			return;
		} else {
			throw new UserValidationException("Empty prenom not allowed");
		}
	}

	
	private static void checkAdresse(String adresse) throws UserValidationException {
		if (! isEmpty(adresse)) {
			return;	
		} else {
			throw new UserValidationException("Empty adresse not allowed");
		}
	}


	private static void checkNip(String nip) throws UserValidationException {
		if (! isEmpty(nip)) {
			return;
		} else {
			throw new UserValidationException("Empty nip not allowed");
		}
	}


	private static void checkType(String type) throws UserValidationException {
		if (! isEmpty(type)) {
			return;
		} else {
		throw new UserValidationException("Empty type not allowed");
		}
	}

	private static void checkEmail(String email) throws UserValidationException {
		if (! isEmpty(email)) {
			return;
		} else {
			throw new UserValidationException("Empty email not allowed");
		}
	}
	
	private static boolean isEmpty(String nom) {
		return nom.length()<=0;
	}

}
