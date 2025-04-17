package com.DAL.Repository.Exception;

/*
 * Exception levée lorsqu'une opération viole une contrainte d'invariant,
 * c'est-à-dire une condition qui doit toujours être vraie dans le système.
 */
public class InvariantException extends Exception{
	
	/**
     * Crée une nouvelle exception avec un message descriptif
     *
     * @param message - Le message décrivant la nature de l'invariant violé
     */
	public InvariantException(String message) {
		super(message);
	}
}
