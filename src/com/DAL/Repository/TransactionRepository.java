package com.DAL.Repository;
import java.util.ArrayList;
import java.util.function.Predicate;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.InvariantException;

/*
 * Classe responsable de la gestion des transactions à l'aide d'une stratégie de persistance.
 * Implémente le patron Repository pour encapsuler la logique d'accès aux données de transactions.
 */
public class TransactionRepository implements IRepository<Transaction>{
	private ArrayList<Transaction> _transactions;
	private RecordStrategy<Transaction> _strategy;
	
	/**
     * Constructeur par défaut qui charge toutes les transactions valides (non nulles) avec une stratégie donnée
     * 
     * @param strategy - La stratégie de persistance à utiliser
     */
    public TransactionRepository(RecordStrategy<Transaction> strategy) {
        this.setStrategy(strategy);
        _transactions = _strategy.get((c) -> c != null); // Charger tout ce qui est non null
      
    }
	
    /**
     * Constructeur personnalisé qui charge les transactions selon un prédicat spécifique
     * 
     * @param strategy - La stratégie de persistance à utiliser
     * @param predicate - Le filtre à appliquer sur les transactions chargées
     */
    public TransactionRepository(RecordStrategy<Transaction> strategy, Predicate<Transaction> predicate) {
        this.setStrategy(strategy);
        _transactions = _strategy.get(predicate);
    }
	
    /**
     * Définit la stratégie de persistance à utiliser
     * 
     * @param strategy - La stratégie à assigner
     */
	public void setStrategy(RecordStrategy<Transaction> strategy) {
		this._strategy = strategy;
	}
	
	/**
     * Crée une nouvelle transaction dans le dépôt et persiste immédiatement les données
     * 
     * @param item - La transaction à ajouter
     */
	@Override
	public void create(Transaction item) {
		_transactions.add(item);
        _strategy.set(_transactions);
	}

	/**
     * 
     * @return une liste de transactions actuellement chargées
     */
	@Override
	public ArrayList<Transaction> read() {
		return this._transactions;
	}
	
	/**
     * Méthode non implémentée pour la mise à jour d'une transaction
     * 
     * @param item - La transaction mise à jour
     */
	@Override
	public void update(Transaction item) {
		
	}

	/**
     * Supprime une transaction du dépôt si elle existe
     * 
     * @param item - La transaction à supprimer
     * @throws InvariantException - Si la transaction n'existe pas
     */
	@Override
	public void delete(Transaction item) throws InvariantException {
		if(_transactions.contains(item)) {
			_transactions.remove(item);
		}
		else
			throw new InvariantException("transaction does not exist");
	}
	
	// Force la persistance manuelle des transactions actuellement en mémoire
	public void commit() {
		_strategy.set(_transactions);
	}
		
}
