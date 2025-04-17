package com.DAL.Repository.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Implémentation de l'interface RecordStrategy utilisant la sérialisation Java pour
 * lire et écrire des objets dans un fichier.
 * 
 * @param <c> - Le type des objets sérialisés
 */
public  class SerializeRecord<c> implements RecordStrategy<c>{
	
	String connectionString;

	/**
     * Constructeur de la stratégie de sérialisation
     *
     * @param connectionString - Le chemin vers le fichier de stockage des objets
     */
	public SerializeRecord(String connectionString) {
		 this.connectionString = connectionString;	
	}
	
	/**
     * Sérialise et sauvegarde la liste d'objets dans le fichier spécifié par connectionString
     *
     * @param c - La liste d'objets à sauvegarder
     */
	@Override
	public void set(ArrayList<c> c)  {
		try {
			FileOutputStream fos = new FileOutputStream(this.connectionString);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
     * Désérialise les objets à partir du fichier et les filtre selon un prédicat
     *
     * @param predicate - Le prédicat de filtrage appliqué aux objets lus
     * @return une liste d'objets correspondant au filtre donné
     */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<c> get(Predicate<c> predicate) {
		ArrayList<c> temp = new ArrayList<c>();
		File file = new File(this.connectionString);
		if(file.length() ==0){
			return temp; // Fichier vide, retourne une liste vide
		}
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			while(fis.available() > 0) {
				// Lecture des objets
				ArrayList<c> items = (ArrayList<c>) ois.readObject();
				for(var user : items) {
					if( predicate.test(user)) {
						temp.add(user);
					}	
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
