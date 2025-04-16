package com.DAL.Repository.Connection;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Interface représentant une stratégie de persistance des données.
 * Elle permet de définir les mécanismes de récupération et de sauvegarde
 * des objets en fonction de leur type.
 * 
 * @param <c> - Le type des objets à gérer
 */
public interface RecordStrategy<c> {
	
	/**
     * Récupère une liste d'objets en fonction d'un prédicat.
     * Ce prédicat permet de filtrer les objets selon une condition spécifique.
     *
     * @param predicate - Le prédicat à appliquer pour filtrer les objets
     * @return une liste filtrée d'objets du type spécifié
     */
	public ArrayList<c> get(Predicate<c> predicate);
	
	/**
	 * Sauvegarde la liste d'objets passée en paramètre
     *
     * @param c - La liste des objets à sauvegarder
	 */
	public void set(ArrayList<c> c);

}
