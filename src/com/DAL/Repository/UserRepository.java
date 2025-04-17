package com.DAL.Repository;

import java.util.ArrayList;
import java.util.function.Predicate;
import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.*;

/*
 * Implémentation du dépôt pour les objets Utilisateur, utilisant une stratégie de persistance personnalisable.
 * Permet de gérer la création, lecture, mise à jour, suppression et recherche des utilisateurs.
 */
public class UserRepository implements IRepository<Utilisateur>{
	private ArrayList<Utilisateur> _user;
	private RecordStrategy<Utilisateur> _strategy;
	
	/**
 	 * Constructeur par défaut : charge tous les utilisateurs avec un identifiant strictement supérieur à 0
 	 * 
 	 * @param strategy - La stratégie de persistance à utiliser
 	 */
	public UserRepository(RecordStrategy<Utilisateur> strategy) {
		this.setStrategy(strategy);
		_user = _strategy.get((u)-> u.getId() >0 ); // Charger tout ce qui est non null
	}
	
	/**
     * Définit la stratégie de persistance à utiliser pour les opérations futures
     * 
     * @param strategy - La stratégie de persistance
     */
	public void setStrategy(RecordStrategy<Utilisateur> strategy) {
		this._strategy = strategy;
	}
	
	/**
     * Retourne le premier utilisateur correspondant à un prédicat donné
     * 
     * @param predicate - La condition de recherche à appliquer
     * @return le premier utilisateur correspondant au prédicat
     * @throws InvariantException - Si aucun utilisateur ne correspond à la condition
     */
	public Utilisateur findFirst(Predicate<Utilisateur> predicate) throws InvariantException {
		for(var user : _user) {
			if(predicate.test(user)) {
				return user;
			}
		}
		throw new InvariantException("User non existant");
	}
	
	/**
     * Crée un nouvel utilisateur après vérification d'intégrité (unicité de l'ID et de l'email)
     * 
     * @param user - L'utilisateur à ajouter
     * @throws KeyConstraintException - Si un utilisateur avec le même ID ou email existe déjà
     */
	@Override
	public void create(Utilisateur user) throws KeyConstraintException {
		for(var item : _user) {
			if((item.getId()==user.getId())||(item.getEmail().equals(user.getEmail()))) {
				throw new KeyConstraintException("User id or email already exist");	
			}
		}
		
		_user.add(user);
		_strategy.set(_user);
	}

	/**
     * 
     * @return une liste d'utilisateurs actuellement chargés
     */
	@Override
	public ArrayList<Utilisateur> read() {	
		return _user;
	}
	
	/**
     * Met à jour un utilisateur existant dans la liste (par ID)
     * Remplace l'ancien objet par le nouveau, puis persiste les modifications
     * 
     * @param user - L'utilisateur mis à jour
     */
	@Override
	public void  update(Utilisateur user) {
		for(var  users : _user) {
	        if(user.getId()==users.getId()) {
	        	users=user;
	        }    	
	    }
	        _strategy.set(_user);
	}
	
	/**
     * Supprime un utilisateur de la liste si celui-ci existe
     * 
     * @param user - L'utilisateur à supprimer
     * @throws InvariantException - Si l'utilisateur n'existe pas dans le dépôt
     */
	@Override
	public void delete(Utilisateur user) throws InvariantException {
		if (_user.contains(user)) {
	        _user.remove(user);
	       
	    } else {
	    	throw new InvariantException("User non existant");
	    }
	}

	// Force la sauvegarde des données en mémoire via la stratégie définie
	public void commit() {
		 _strategy.set(_user);
	}
}
