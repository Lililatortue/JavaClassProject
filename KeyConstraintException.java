package com.DAL.Repository.Exception;

/*
 * Exception levée lorsqu'une contrainte d'unicité est violée,
 * par exemple lorsqu'une tentative de création d'un enregistrement
 * entre en conflit avec une clé primaire ou un identifiant déjà existant.
 *
 * Cette exception hérite de InvariantException et représente
 * un cas spécifique d'invariant lié à l'unicité des données.
 */
public class KeyConstraintException extends InvariantException{
	
	/**
     * Crée une nouvelle exception de contrainte de clé avec un message descriptif
     *
     * @param message - Le message expliquant la contrainte violée
     */
	public KeyConstraintException(String message) {
		super(message);
	}
}
