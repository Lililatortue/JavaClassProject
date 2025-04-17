package com.Bus.Service.CompteManagement;
import com.DAL.Repository.CompteRepository;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;


/*
 * Service de gestion des comptes bancaires.
 * 
 * Cette classe permet de créer, supprimer, déposer ou retirer de l'argent sur un compte.
 * Elle agit comme un intermédiaire entre la couche métier et la couche de persistance,
 * en isolant la logique liée aux opérations sur les comptes.
 * 
 * Chaque opération est persistée via le CompteRepository, qui utilise une
 * sérialisation des objets Compte.
 */
public class CompteManagement {
	private CompteRepository _compteRepo;
	
	/*
     * Constructeur par défaut.
     * Initialise le référentiel des comptes avec un fichier de données sérialisées.
     */
	public CompteManagement() {
		_compteRepo = new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\CompteList.ser"));
	
	}
	
	/**
 	 * Crée un nouveau compte bancaire et l'ajoute au référentiel
 	 * 
 	 * @param compte - Le compte à ajouter
 	 * @throws KeyConstraintException - Si un compte avec le même identifiant ou type existe déjà
 	 */
	public void CreateAccount(Compte compte) throws KeyConstraintException {
		_compteRepo.create(compte);
		_compteRepo.commit();
	}
	
	/**
 	 * Supprime un compte bancaire du référentiel
 	 * 
 	 * @param compte - Le compte à supprimer
 	 * @throws InvariantException - Si le compte ne peut pas être supprimé
 	 */
	public void DeleteAccount(Compte compte) throws InvariantException {
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}
	
	/**
 	 * Dépose un montant sur le compte correspondant à la transaction
 	 * 
 	 * @param transaction - La transaction contenant l'ID client, le type de compte et le montant
 	 * @throws Exception - Si le compte n'est pas trouvé
 	 */
	public void deposer(Transaction transaction) throws Exception {
		Compte compte = null;
		// Rechercher le compte concerné
		for(var item : _compteRepo.read()) {
			if(item.getClientId()==transaction.getClientId() 
					&& item.getType() == transaction.getCompteType()) 
			{
				compte=item;
				break;
			}
		}
		if(compte == null) {
			throw new Exception("no account found");
		}else {
			compte.deposer(transaction.getMontant());
		}	
		_compteRepo.update(compte);
		_compteRepo.commit();
	}
	
	/**
 	 * Retire un montant du compte correspondant à la transaction
 	 * 
 	 * @param transaction - La transaction contenant l'ID client, le type de compte et le montant
 	 * @throws Exception - Si le compte n'est pas trouvé ou si le retrait échoue
 	 */
	public void retirer(Transaction transaction) throws Exception {
		Compte compte = null;
		// Rechercher le compte concerné
		for(var item : _compteRepo.read()) {
			if(item.getClientId()==transaction.getClientId() 
					&& item.getType() == transaction.getCompteType()) 
			{
				compte=item;
				break;
			}
		}
		if(compte == null) {
			throw new Exception("aucun compte existe");
		}else {
			compte.retirer(transaction.getMontant());
		}	
		
		_compteRepo.update(compte);
		_compteRepo.commit();
	}
}
