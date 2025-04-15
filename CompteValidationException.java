package com.Bus.Service.CompteManagement;

/*
 * Exception personnalisée utilisée pour signaler les erreurs de validation
 * liées aux comptes bancaires dans le système.
 */

public class CompteValidationException extends Exception {
	
	/**
	 * Construit une nouvelle exception de validation de compte avec un message détaillé
	 * 
	 * @param message - Le message décrivant l'erreur de validation
	 */
	public CompteValidationException(String message) {
		super(message);
	}
}

