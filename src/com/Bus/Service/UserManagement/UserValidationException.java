package com.Bus.Service.UserManagement;

/*
 * Exception personnalisée utilisée pour signaler des erreurs de validation liées aux utilisateurs.
 */
public class UserValidationException extends Exception {

	/**
 	 * Construit une nouvelle exception avec le message d'erreur spécifié
 	 * 
 	 * @param message - Le message décrivant la cause de l'exception
 	 */
	public UserValidationException(String message) {
        super(message);
    }
}
