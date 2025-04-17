package com.Bus.Service.Logger;

import java.io.FileWriter;
import java.io.IOException;


/*
 * Implémentation de LoggerStrategy pour journaliser les messages dans un fichier.
 * 
 * Cette classe permet d'écrire des logs dans un fichier en utilisant un FileWriter.
 * Le fichier est ouvert en mode append (ajout), ce qui permet de ne pas écraser les logs existants.
 */
public class LoggerFileSystem implements LoggerStrategy {

	// Writer utilisé pour écrire les logs dans le fichier
	private FileWriter fileWriter=null; 
	
	/**
 	 * Constructeur de la classe LoggerFileSystem
 	 * 
 	 * @param path - Le chemin du fichier où les logs doivent être écrits
 	 */
	public LoggerFileSystem(String path) {
		try {
		this.setPath(path);
		} catch(IOException e) {
			  System.out.println("file not found" + path);
		}
	}
	
	/**
	 * Initialise le FileWriter avec le fichier spécifié
	 * 
	 * @param path - Le chemin du fichier de log
	 * @throws IOException - Si une erreur survient à l'ouverture du fichier
	 */
	private void setPath(String path) throws IOException {
			this.fileWriter= new FileWriter(path,true); // Mode append
	}
	
	/**
     * Écrit une entrée de log dans le fichier à l'aide du Logger fourni
     * 
     * @param logger - L'objet Logger contenant les informations à écrire
     */
	@Override
	public void log(Logger logger){
		try {
			this.fileWriter.write(logger.toString()+"\n");
			this.fileWriter.flush(); // S'assure que les données sont écrites immédiatement
		} catch(IOException e) {
			System.out.println("mistake at LoggerStrategyFileSystem/logfunction");
		}
	}
	
	// Ferme proprement le fichier de log
    public void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("mistake while closing LoggerFile");
        }
    }

}
