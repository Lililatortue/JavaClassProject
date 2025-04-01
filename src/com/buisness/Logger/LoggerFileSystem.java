package com.buisness.Logger;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Implémentation de LoggerStrategy pour journaliser les messages dans un fichier.
 * 
 * Cette classe permet d'écrire des logs dans un fichier en utilisant un FileWriter.
 * Le fichier est ouvert en mode append (ajout), ce qui permet de ne pas écraser les logs existants.
 */

public class LoggerFileSystem implements LoggerStrategy {

	private FileWriter fileWriter=null; 
	
	/*
     * Constructeur de LoggerFileSystem.
     */
	public LoggerFileSystem(String path) {
		try {
		this.setPath(path);
		} catch(IOException e) {
			  System.out.println("file not found" + path);
		}
	}
	
	/*
     * Initialise le fichier de log.
     * Ouvre le fichier en mode append pour ne pas écraser les logs existants.
     */
	private void setPath(String path) throws IOException {
			this.fileWriter= new FileWriter(path,true); // Mode append
	}
	
	/*
     * Écrit un message de log dans le fichier.
     */
	@Override
	public void log(Logger logger){
		try {
			this.fileWriter.write(logger.toString()+"\n");
			this.fileWriter.flush(); // S'assure que les données sont bien écrites
		} catch(IOException e) {
			System.out.println("mistake at LoggerStrategyFileSystem/logfunction");
		}
	}
	
	/*
     * Ferme le fichier de log proprement.
     */
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
