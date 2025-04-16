package com.Bus.Service.UserManagement;

import java.util.ArrayList;

import com.Bus.Model.Client.Gestionnaire;
import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

/*
 * Service de gestion des gestionnaires dans le système FortisBank.
 *
 * Cette classe permet d'ajouter, de supprimer et de récupérer
 * les utilisateurs de type Gestionnaire depuis un fichier sérialisé.
 * 
 * Elle utilise un UserRepository pour accéder aux données persistées.
 */
public class GestionnaireManagement {
	
	// Repository central des utilisateurs
	private UserRepository _repo;
	
	// Liste locale des gestionnaires présents dans le système
	private ArrayList<Gestionnaire> _gestionnaire = new ArrayList<Gestionnaire>();;
	
	// Initialise le repository et charge tous les gestionnaires existants depuis la source de données
	public GestionnaireManagement() {
		_repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		for(var user : _repo.read()) {
			if(user instanceof Gestionnaire) {
				_gestionnaire.add((Gestionnaire)user);
			}
		}
	}
	
	/**
     * Ajoute un gestionnaire au système après validation
     *
     * @param u - Le gestionnaire à ajouter
     * @throws UserValidationException - Si les données du gestionnaire sont invalides
     * @throws KeyConstraintException - Si un utilisateur avec le même ID existe déjà
     */
	public void  ADDGestionnaire(Gestionnaire u) throws UserValidationException, KeyConstraintException {
		UserValidation.generalValidation(u);
		_repo.create(u);
	}
	
	/**
     * Supprime un gestionnaire du système après validation
     *
     * @param u - Le gestionnaire à supprimer
     * @throws UserValidationException - Si les données du gestionnaire sont invalides
     * @throws InvariantException - Si une contrainte empêche la suppression
     */
	public void  DeleteGestionnaire(Gestionnaire u) throws UserValidationException, InvariantException {
		UserValidation.generalValidation(u);
		_repo.delete(u);
	}
	
	/**
	 * 
	 * @return une liste des gestionnaires présents dans le système
	 */
	public ArrayList<Gestionnaire>read() {
		return _gestionnaire;
	}
	
}
