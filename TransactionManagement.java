package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.TransactionType;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.DAL.Repository.TransactionRepository;
import com.DAL.Repository.Connection.*;

/*
 * Classe de gestion des transactions bancaires
 * 
 * Cette classe permet :
 *    De traiter et enregistrer les dépôts et retraits
 *    De mettre à jour le solde des comptes concernés
 *    D'accéder à l’historique des transactions spécifiques à un compte
 *    
 *    Elle centralise les appels entre la couche métier des comptes et le dépôt de données des transactions.
 */

public class TransactionManagement {

	private TransactionRepository _transaction;
	private CompteManagement _compte;
	
	/*
     * Constructeur par défaut.
     * Initialise les gestionnaires de compte et de transaction, avec un fichier de sérialisation prédéfini.
     */
	public TransactionManagement() {
		_transaction = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/transactionList.ser"));
		_compte = new CompteManagement();
	}
	
	/**
	 * Ajoute une transaction (dépôt ou retrait) au système
	 * 
	 * Cette méthode met à jour le solde du compte concerné en fonction du type de transaction
     * (dépôt ou retrait), puis enregistre la transaction dans le dépôt de données.
	 * 
	 * @param t - La transaction à traiter et enregistrer
	 * @throws Exception - Si une erreur survient pendant l’opération
	 */
	public void ADDTransaction(Transaction t) throws Exception {
		if(t.getTransactionType()==TransactionType.depot) {
			_compte.deposer(t);
		} else if(t.getTransactionType()==TransactionType.retrait) {
			_compte.retirer(t);
		}	
		_transaction.create(t);
	}
	
	/**
	 * Retourne la liste des transactions associées à un client et un type de compte donnés
	 * 
	 * @param id - L'identifiant du client
	 * @param type - Le type de compte concerné
	 * @return une liste d'objets Transaction correspondant au filtre
	 */
	public ArrayList<Transaction> getSpecifiqueTransactions(int id, CompteType type) {
		ArrayList<Transaction> temp = new ArrayList<Transaction>();

		for(var item : _transaction.read() ) {
			if(item.getClientId()==id
					&& item.getCompteType() == type)
			{
				temp.add(item);
			}
		}
		return temp;
	}

}
