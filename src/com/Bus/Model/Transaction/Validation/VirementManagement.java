package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.Virement;
import com.DAL.Repository.VirementDAO;

public class VirementManagement {
	private VirementDAO _virement;

	private Compte _compte;
	//default constructor
	public VirementManagement(Compte compte) {
		_virement = new VirementDAO();
		_compte = compte;
	}
	
	//rajoute le retrait dans transaction
	public void creerVirement(Virement v) throws Exception {
		_compte.retirer(v.getMontant());
		_virement.create(v);
	}
	
	//rajoute le montant du retrait dans le compte choisi
	public void accepterVirement(Virement v,String psw,Compte compte) throws Exception {
		Transaction dest = 	v.getDestinataireTransaction(psw, compte.getCompteId());
		Transaction source = v.getSourceTransaction();
			compte.deposer(dest.getMontant());
			_virement.accept(source,dest,v.getVirementId());
		
	}

	//method pour recevoir toute les virement envoyer a l'utilisateur
	public ArrayList<Virement> getVirement(int id) {

		return _virement.findOne(id);
	}
}
