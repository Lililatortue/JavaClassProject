package com.DAL.Repository;

import java.util.ArrayList;

import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

/**
 * Interface générique définissant les opérations de base du patron de dépôt (Repository).
 * 
 * Cette interface abstrait les opérations CRUD (Create, Read, Update, Delete) 
 * pour tout type d'entité.
 * 
 * @param <c> - Le type d'objet géré par le dépôt (repository)
 */
public interface IRepository<c> {
	
	/**
	 * Crée un nouvel élément dans le dépôt
	 * 
	 * @param item - L'élément à ajouter
	 * @throws KeyConstraintException - Si une contrainte d’unicité est violée
	 */
	public void create(c item) throws KeyConstraintException;
	
	/**
	 * 
	 * @return une liste de tous les éléments actuellement présents dans le dépôt
	 */
	public ArrayList<c> read();
	
	/**
	 * Met à jour un élément existant dans le dépôt
	 * 
	 * @param item - L'élément mis à jour
	 */
	public void update(c item);
	
	/**
	 * Supprime un élément du dépôt
	 * 
	 * @param item - L'élément à supprimer
	 * @throws InvariantException - Si l'élément n'existe pas ou ne peut pas être supprimé
	 */
	public void delete(c item) throws InvariantException;
	
}
