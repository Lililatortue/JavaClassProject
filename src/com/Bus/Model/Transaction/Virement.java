package com.Bus.Model.Transaction;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;

/*
 * Représente une transaction de type virement entre deux comptes bancaires.
 * 
 * Un virement est composé de deux sous-transactions :
 *   Un retrait effectué depuis le compte source
 *   Un dépôt vers un compte destinataire, protégé par un mot de passe
 * 
 * Cette classe agit comme un proxy permettant de contrôler l’accès
 * à la transaction de dépôt via authentification.
 */
public class Virement extends Transaction{

	private static final long serialVersionUID = 1L;
	
	// Transaction représentant le dépôt vers le compte destinataire
	private Transaction destinataire;
	// Mot de passe requis pour débloquer et effectuer le virement vers le destinataire
	private String psw;
	
	/**
 	 * Constructeur de la classe Virement
 	 * 
 	 * @param source
 	 * @param t
 	 * @param psw
 	 */
	public Virement(Compte source, Transaction t, String psw) {
		super(source.getClientId(), source.getType(), t.getMontant(), TransactionType.retrait);
		// Configure la transaction destinataire
		t.setTransactionType(TransactionType.depot);
		t.setType(CompteType.VRMNT);
		destinataire =t;
		this.setPassword(psw);
	}

	/**
 	 * Définit le mot de passe requis pour autoriser le virement
 	 * 
 	 * @param psw - Le mot de passe à associer à cette transaction de virement
 	 */
	public void setPassword(String psw){
		this.psw = psw;
	}
	
	/**
 	 * 
 	 * @return l'identifiant du client destinataire du virement
 	 */
	public int getTransactionId(){
		return this.destinataire.getClientId();
	} 
	
	/**
 	 * Récupère la transaction de dépôt vers le destinataire si le mot de passe fourni est correct
 	 * 
 	 * @param psw - Le mot de passe pour valider le virement
 	 * @param type - Le type de compte du destinataire
 	 * @return l'objet Transaction représentant le dépôt
 	 * @throws Exception - Si le mot de passe est incorrect
 	 */
	public Transaction getTransaction(String psw,CompteType type) throws Exception {
		
		if(this.psw.equals(psw)) {
			destinataire.setType(type);
			return this.destinataire;
		}
		else {
			throw new Exception("Invalid credential ");
		}
	}
	
	/**
 	 * 
 	 * @return l'identifiant du client destinataire du virement
 	 */
	public int GetDestinataireId() {
		return this.destinataire.getClientId();
	}
}
