package com.DAL.Repository;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.*;

/*
 * Implémentation concrète du dépôt de données pour la gestion des comptes bancaires.
 * 
 * Cette classe implémente l'interface IRepository et fournit les opérations 
 * CRUD sur les objets Compte, en utilisant une stratégie de persistance 
 * définie par RecordStrategy.
 */
public class CompteRepository implements IRepository<Compte> {
	private ArrayList<Compte> _compte;
	private RecordStrategy<Compte> _strategy;
	
	/**
 	 * Constructeur par défaut du repository
 	 * 
 	 * @param strategy - La stratégie de persistance utilisée pour charger et sauvegarder les données
 	 */
	public CompteRepository(RecordStrategy<Compte> strategy) {
		this.setStrategy(strategy);
		_compte = _strategy.get((c)-> c!=null ); // Charger tout ce qui est non null
	}
	
	/**
 	 * Constructeur personnalisé permettant de filtrer les comptes au chargement
 	 * 
 	 * @param strategy - La stratégie de persistance à utiliser
 	 * @param predicate - Le prédicat permettant de filtrer les comptes à charger
 	 */
	public CompteRepository(RecordStrategy<Compte> strategy,Predicate<Compte> predicate) {
		this.setStrategy(strategy);
		_compte = _strategy.get(predicate);
	}
	
	/**
     * Définit la stratégie de persistance utilisée par le dépôt
     *
     * @param strategy - La stratégie de persistance à utiliser
     */
	public void setStrategy(RecordStrategy<Compte> strategy) {
		this._strategy = strategy;	
	}
	
	/**
     * Crée un nouveau compte dans le dépôt après avoir vérifié l’unicité par client et type
     *
     * @param item - Le compte à ajouter
     * @throws KeyConstraintException - Si un compte du même type existe déjà pour ce client
     */
	@Override
	public void create(Compte item) throws KeyConstraintException {
		for(var _item : _compte) {
			if(_item.getClientId()==item.getClientId() && _item.getType()==item.getType()) {
				throw new KeyConstraintException("account already exist");
			}
		}
		_compte.add(item);
		_strategy.set(_compte);	
	}
	
	/**
    *
    * @return la liste actuelle des comptes en mémoire
    */
	@Override
	public ArrayList<Compte> read() {
		return this._compte;
	}
	
	/**
     * Met à jour un compte existant
     *
     * @param compte - Le compte mis à jour
     */
	@Override
	public void update(Compte compte) {
		for(var item : _compte) {
			if(item.getClientId()==compte.getClientId())
				item= compte;
		}
	}
	
	/**
     * Supprime un compte du dépôt
     *
     * @param compte - Le compte à supprimer
     * @throws InvariantException - Si le compte n'existe pas dans le dépôt
     */
	@Override
	public void delete(Compte compte) throws InvariantException {
		if(_compte.contains(compte)) {
			_compte.remove(compte);
		}
		else
			throw new InvariantException("compte does not exist");
	}
	
	// Persiste l'état actuel des comptes en utilisant la stratégie de sauvegarde
	public void commit() {
		_strategy.set(_compte);
	}
	
}
