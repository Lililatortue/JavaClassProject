package com.Bus.Model.Transaction;

import javax.security.auth.login.CredentialException;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;

//est un proxy il contientune source, un destinataire, un mot de passe et la transaction
//
public class Virement extends Transaction{	
	private static final long serialVersionUID = 1L;
	//transaction envoyer a la personne
	private Transaction destinataire;
	//psw pour acceder a la transaction envoyer
	private String psw;
	
	
	public Virement(Compte source, Transaction transaction, String psw) {
		//retrait du compte source
		super(source.getClientId(), source.getType(), transaction.getMontant(), TransactionType.RETRAIT);	
		//creation de la transaction destinataire
		//set les prerequis
		transaction.setTransactionType(TransactionType.DEPOT);
		transaction.setCompteType(CompteType.VRMNT);
		destinataire =transaction;
		this.setPassword(psw);
	}

	//setters
	public void setPassword(String psw){
		this.psw = psw;
	}
	//getters
	public int getTransactionId(){
		return this.destinataire.getCompteId();
	} 
	public Transaction getTransaction(String psw,CompteType Comptetype) throws CredentialException {
		//verifi si le mot de passe concorde
		if(this.psw.equals(psw)) {
			destinataire.setCompteType(Comptetype);
			return this.destinataire;
		}
		else {
			throw new CredentialException("Invalid credential ");
		}
	}

}
