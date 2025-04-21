package com.Bus.Model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.CredentialException;

import com.Bus.Model.Compte.Compte;

//est un proxy il contientune source, un destinataire, un mot de passe et la transaction
//
public class Virement extends Transaction{	

	private int _virementId;
	//id des clients concerner
	private int _source;
	private int _destinataire;
	
	private Transaction _transaction;
	private String _psw;
	
	
	public Virement(Compte source, int destinataireId, Transaction transaction, String psw) {
		//source
		super(source.getCompteId(), transaction.getMontant());
		this.setSource(source.getClientId());
		this.setDestinataire(destinataireId);
		
		_transaction =transaction;
		this.setPassword(psw);
	}
	
	public Virement(ResultSet rs) throws SQLException {
		//source
		super(rs.getInt("CPT_NUMERO"), rs.getInt("TRX_MONTANT"));
		
		_virementId=rs.getInt("VIR_ID");
		this.setSource(rs.getInt("VIR_SRC"));
		this.setDestinataire(rs.getInt("VIR_DES"));
		
		_transaction = new Transaction(rs);
		this.setPassword(rs.getString("VIR_PSW"));
	}
	
	
	//setters
	public void setSource(int source){
		this._source = source;
	}
	
	public void setDestinataire(int destinataire){
		this._destinataire = destinataire;
	}
	
	public void setPassword(String psw){
		this._psw = psw;
	}
	
	
	//getters
	public int getSource(){
		return this._source;
	}
	
	public int getDestinataire(){
		return this._destinataire;
	}
	
	public String getPassword(){
		return this._psw;
	}
	
	public int getVirementId(){
		return this._virementId;
	}
	
	//retourner la source transaction
	public Transaction getSourceTransaction() {
		return (Transaction)this;
	}
	//retourner la transaction destinataire
	public Transaction getDestinataireTransaction(String psw, int id) throws CredentialException {
		//verify si le psw est valide
		if(_psw.equals(psw)) {
			_transaction.setCompteId(id);
			return this._transaction;
		}
		else {
			throw new CredentialException("Invalid credential ");
		}
	}

}
