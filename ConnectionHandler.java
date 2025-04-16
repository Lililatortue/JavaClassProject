package com.Bus.Service.LoginValidation;

/*
 * Classe ConnectionHandler implémentant le modèle de conception de chaîne de responsabilité.
 * 
 * Elle permet de chaîner plusieurs gestionnaires de connexion et de transmettre
 * une requête au prochain gestionnaire dans la chaîne si elle n'est pas traitée.
 */

public class ConnectionHandler implements IConnectionHandler {
	
	// Référence vers le prochain gestionnaire dans la chaîne
	private IConnectionHandler _next;
	
	/**
     * Définit le prochain gestionnaire de la chaîne de responsabilité
     *
     * @param next - Le gestionnaire suivant à appeler si ce gestionnaire ne traite pas la requête
     */
	public void setNext(IConnectionHandler next) {
		this._next = next;
	}
	
	/**
     * Traite la requête en la transmettant au prochain gestionnaire s'il existe
     *
     * @param request - La requête à traiter
     * @throws Exception - Si un gestionnaire échoue
     */
	public void Handle(Request request) throws Exception {
		if(_next != null) {
			_next.Handle(request);
		}
	}
}
