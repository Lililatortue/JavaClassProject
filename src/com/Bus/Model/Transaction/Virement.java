package com.Bus.Model.Transaction;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;

//est un proxy il contientune source, un destinataire, un mot de passe et la transaction
//
public class Virement extends Transaction{

	private static final long serialVersionUID = 1L;
	private Transaction destinataire;
	//mot de passe pour acceder au virement
	private String psw;
	
	
	public Virement(Compte source, Transaction t, String psw) {
		//retrait du compte source
		super(source.getClientId(), source.getType(), t.getMontant(), TransactionType.retrait);
		
		//creation de la transaction destinataire
		//set les prerequis
		t.setTransactionType(TransactionType.depot);
		t.setType(CompteType.VRMNT);
		destinataire =t;
		
		this.setPassword(psw);
	}

	//setters
	public void setPassword(String psw){
		this.psw = psw;
	}
	
	//getters
	public int getTransactionId(){
		return this.destinataire.getClientId();
	} 
	
	public Transaction getTransaction(String psw,CompteType type) throws Exception {
		
		if(this.psw.equals(psw)) {
			destinataire.setType(type);
			return this.destinataire;
		}
		else {
			throw new Exception("Invalid credential ");
		}
	}
	
	public int GetDestinataireId() {
		return this.destinataire.getClientId();
	}
}
