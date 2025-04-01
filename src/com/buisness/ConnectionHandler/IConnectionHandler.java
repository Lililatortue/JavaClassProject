package com.buisness.ConnectionHandler;

/*
 * Interface IConnectionHandler définissant le contrat pour une chaîne de responsabilité 
 * dans la gestion des connexions.
 */

public interface IConnectionHandler {
	
	// Définit le prochain gestionnaire de connexion dans la chaîne de responsabilité
	public void setNext(IConnectionHandler n);
	
	// Traite une requête et, si nécessaire, la transmet au gestionnaire suivant
	public void Handle(Request request);
}
