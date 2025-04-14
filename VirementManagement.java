package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.Virement;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.DAL.Repository.TransactionRepository;
import com.DAL.Repository.Connection.SerializeRecord;

/*
 * Classe de gestion des virements bancaires.
 * 
 * Cette classe permet d'encapsuler la logique liée à l'envoi et la réception de virements,
 * ainsi que leur persistance dans les fichiers sérialisés.
 */

public class VirementManagement {
	private TransactionRepository _virement;
	private TransactionRepository _transaction;
	private CompteManagement _compte;
	
	/*
     * Constructeur par défaut. Initialise les référentiels de données sérialisées
     * pour les virements et les transactions générales.
     */
	public VirementManagement() {
		_virement = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/Virement.ser") );
		_transaction = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/transactionList.ser") );
		_compte = new CompteManagement();
		
	}
	
	/**
	 * Exécute un virement en retirant le montant du compte source et en enregistrant
     * la transaction comme virement en attente.
	 * 
	 * @param t - Le virement à effectuer (incluant source, destinataire, mot de passe)
	 * @throws Exception - Si le retrait échoue
	 */
	public void faireVirement(Virement t) throws Exception {
		_compte.retirer(t);
		_virement.create(t);
		_transaction.create(t);
	}
	
	/**
	 * Permet au destinataire de recevoir un virement s'il entre le bon mot de passe.
	 * 
	 * Le montant est alors déposé sur le compte destinataire et le virement est
     * supprimé du fichier de virements en attente.
	 * 
	 * @param v - Le virement à recevoir
	 * @param psw - Le mot de passe de sécurité fourni par le destinataire
	 * @param compte - Le type de compte sur lequel le dépôt doit être fait
	 * @throws Exception - Si le mot de passe est incorrect ou si le dépôt échoue
	 */
	public void recevoirVirement(Virement v,String psw,CompteType compte) throws Exception {
		Transaction temp=v.getTransaction(psw, compte);
		_compte.deposer(temp);
		_virement.delete(v);
		_virement.commit();
		_transaction.create(temp);
	}
	
	/**
	 * Récupère tous les virements en attente liés à un client donné.
	 * 
	 * @param id - L'identifiant du client
	 * @return une liste de virements dont le client est la source
	 */
	public ArrayList<Virement> getVirement(int id) {
		ArrayList<Virement> temp = new ArrayList<Virement>();
		for(var item: _virement.read()) {
			if(item instanceof Virement && item.getClientId()==id) {
				temp.add((Virement) item);
			}
		}
		
		return temp;
	}
}
