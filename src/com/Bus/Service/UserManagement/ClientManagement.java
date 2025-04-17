package com.Bus.Service.UserManagement;

import com.Bus.Model.Client.*;
import java.util.ArrayList;
import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteCheque;
import com.DAL.Repository.*;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

/*
 * Service de gestion des clients pour le système bancaire FortisBank.
 *
 * Cette classe centralise les opérations CRUD liées aux clients et à leurs comptes.
 * Elle communique avec les repositories utilisateurs et comptes pour persister les données
 * dans des fichiers sérialisés.
 * 
 * Elle permet notamment d'ajouter un client avec un compte chèque par défaut,
 * d'ajouter des comptes supplémentaires, et de supprimer un client et ses comptes associés.
 */
public class ClientManagement {
	private UserRepository _userRepo;
	private CompteRepository _compteRepo;
	private ArrayList<Client> _client = new ArrayList<Client>();;
	
	// Initialise les repositories et charge les clients existants depuis le fichier sérialisé
	public ClientManagement() {
		_userRepo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		_compteRepo= new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\CompteList.ser"));
		for(var user : _userRepo.read()) {
			if(user instanceof Client) {
				_client.add((Client)user);
			}
		}
	}
	
	/**
 	 * Ajoute un nouveau client au système avec un compte chèque initial
 	 * 
 	 * @param u - Le client à ajouter
 	 * @throws UserValidationException - Si les données du client sont invalides
 	 * @throws KeyConstraintException - Si un utilisateur avec le même ID existe déjà
 	 */
	public void  ADDClient(Client u) throws UserValidationException, KeyConstraintException {
		UserValidation.generalValidation(u);
		_userRepo.create(u);
		_compteRepo.create(new CompteCheque(u.getId(),0,5));
		_client.add(u);
	}
	
	/**
 	 * Ajoute un compte à un client existant
 	 * 
 	 * @param compte - Le compte à ajouter
 	 * @throws UserValidationException - Si le client lié n'existe pas
 	 * @throws InvariantException - Si une contrainte métier est violée lors de l'ajout
 	 */
	public void  ADDAccount(Compte compte) throws UserValidationException, InvariantException {
		if(_userRepo.findFirst((u)->u.getId()==compte.getClientId()) != null)
			_compteRepo.create(compte);	
	}
	
	/**
 	 * Supprime un client du système ainsi que tous ses comptes associés
 	 * 
 	 * @param u - Le client à supprimer
 	 * @throws UserValidationException - Si les données du client sont invalides
 	 * @throws InvariantException - Si une contrainte empêche la suppression
 	 */
	public void  DeleteClient(Client u) throws UserValidationException, InvariantException {
		UserValidation.generalValidation(u);
		_userRepo.delete(u);
		// Récupère les comptes à supprimer
		  ArrayList<Compte> accountsToDelete = new ArrayList<>();
		    for (Compte item : _compteRepo.read()) {
		        if (item.getClientId() == u.getId()) {
		            accountsToDelete.add(item);
		        }
		    }
		 // Supprime les comptes
		    for (Compte compte : accountsToDelete) {
		        _compteRepo.delete(compte);
		    }
		_userRepo.commit();
		_compteRepo.commit();
	}
	
	/**
 	 * 
 	 * @return une liste des clients chargés en mémoire
 	 */
	public ArrayList<Client>read() {
		return _client;
	}
	
	/**
 	 * Retourne la liste des comptes associés à un client donné
 	 * 
 	 * @param id - L'identifiant du client
 	 * @return une liste des comptes du client
 	 */
	public ArrayList<Compte> getClientCompte(int id){
		ArrayList<Compte> temp = new ArrayList<>();
		for(var item: _compteRepo.read()) {
			if(item.getClientId()==id) {
				temp.add(item);
			}
		}
		return temp;
	}
}
