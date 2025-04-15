package com.Bus.Service.CompteManagement;

import java.util.ArrayList;
import com.Bus.Model.Compte.*;
import com.Bus.Service.UserManagement.ClientManagement;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.CompteRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.*;

/*
 * Service de gestion des demandes de création de comptes bancaires.
 * 
 * Cette classe gère les requêtes de création de comptes soumises par les clients.
 * Elle permet d'approuver ou de refuser une demande, ainsi que d'ajouter une nouvelle
 * demande dans le référentiel temporaire des comptes en attente.
 * 
 * Une demande approuvée entraîne l’ajout du compte au profil du client.
 * Une demande refusée est simplement supprimée de la file d’attente.
 * 
 * Les données sont persistées via sérialisation dans un fichier dédié aux requêtes.
 */

public class CompteRequestManagement {
	private CompteRepository _compteRepo;
	private ClientManagement _client;

	/*
     * Constructeur par défaut.
     * Initialise le référentiel des demandes de compte et le gestionnaire client.
     */
	public CompteRequestManagement() {
		_client = new ClientManagement();
		_compteRepo = new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\AccountRequest.ser"));
	}
	
	/**
	 * Approuve une demande de création de compte pour un client existant
	 * 
	 * @param compte - Le compte à approuver
	 * @throws UserValidationException - Si le client est invalide ou ne peut pas recevoir un nouveau compte
	 * @throws InvariantException - Si la suppression du compte en attente échoue
	 */
	public void  ApprouveRequestCompte(Compte compte) throws UserValidationException, InvariantException  {
		for(var client: _client.read()) {
			if(client.getId()==compte.getClientId()) {
				_client.ADDAccount(compte);
			}
		}	
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}

	/**
	 * Refuse une demande de création de compte
	 * 
	 * @param compte - Le compte à refuser
	 * @throws InvariantException - Si la suppression échoue
	 */
	public void  DenyRequestCompte(Compte compte) throws InvariantException  {
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}
	
	/**
	 * Ajoute une nouvelle demande de création de compte à la file d’attente
	 * 
	 * @param u - Le compte demandé
	 * @throws KeyConstraintException - Si un doublon est détecté dans les requêtes
	 */
	public void  AccountRequest(Compte u) throws KeyConstraintException  {
		_compteRepo.create(u);
	}
	
	/**
	 * 
	 * @return la liste de toutes les demandes de création de compte en attente
	 */
	public ArrayList<Compte> read(){
		return _compteRepo.read();
	}
}
