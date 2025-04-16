package com.Bus.Service.LoginValidation;

/*
 * Interface IConnectionHandler définissant le contrat pour une chaîne de responsabilité 
 * dans la gestion des connexions.
 * 
 * Chaque implémentation de IConnectionHandler est responsable de traiter
 * une requête de connexion ou de la transmettre au gestionnaire suivant dans la chaîne
 */
public interface IConnectionHandler {
	
	/**
	 * Définit le prochain gestionnaire de connexion dans la chaîne de responsabilité
	 * 
	 * @param n - Le gestionnaire suivant à appeler si ce gestionnaire ne traite pas la requête
	 */
	public void setNext(IConnectionHandler n);
	
	/**
	 * Traite une requête de connexion. Si ce gestionnaire ne peut pas la traiter complètement,
     * il peut la transmettre au suivant dans la chaîne.
	 * 
	 * @param request - La requête de connexion à traiter
	 * @throws Exception - Si une erreur survient lors du traitement
	 */
	public void Handle(Request request) throws Exception;
}
