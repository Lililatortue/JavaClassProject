package com.Bus.Service.GestionRapports;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe responsable de la génération de rapports à partir d’une liste ou d’un objet unique
 * en utilisant une stratégie de formatage spécifiée
 * 
 * La stratégie est définie via l’interface formatStrategy. Les rapports sont
 * générés dans des fichiers avec l’extension appropriée et peuvent être utilisés pour l’exportation
 * de données utilisateur, comptes, transactions, etc.
 * 
 * @param <c> - Le type d’objet à formatter et à écrire dans le rapport
 */
public class RapportGenerator<c>{
	
	private formatStrategy<c> _formating;
	private PrintWriter _writer;
	
	/**
 	 * Constructeur par défaut
 	 * 
 	 * @throws IOException - Si une erreur survient lors de la création du fichier
 	 */
	public RapportGenerator() throws IOException {
		this._formating = new TXTStrategy<c>(null);
		this._writer = new PrintWriter(new FileWriter("./src/Printer/default"+_formating.getExtension(),false));
	}
	
	/**
 	 * Constructeur permettant de spécifier une stratégie de formatage et un chemin personnalisé
 	 * 
 	 * @param strategy - La stratégie de formatage à utiliser
 	 * @param path - Le chemin du fichier de sortie
 	 * @throws IOException - Si une erreur survient lors de la création du fichier
 	 */
	public RapportGenerator(formatStrategy<c> strategy,String path) throws IOException {
		this.setFormating(strategy);
		this._writer = new PrintWriter(new FileWriter(path,false));
	}
	
	/**
 	 * Définit la stratégie de formatage à utiliser
 	 * 
 	 * @param strategy - La stratégie de formatage
 	 */
	public void setFormating(formatStrategy<c> strategy) {
		this._formating = strategy;
	}
	
	/**
 	 * Crée un document contenant une liste d’objets formatés
 	 * 
 	 * @param itemlist - La liste des objets à exporter
 	 * @throws Exception - Si la liste est vide ou si une erreur d’écriture survient
 	 */
	public void createDocument(ArrayList<c> itemlist) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(this._formating.getHeader().isEmpty() ? this._formating.getHeader() : "");
		
		for(var item : itemlist){
			builder.append(this._formating.write(item));
			builder.append("\n");
		}
		
		String bigStr = builder.toString();

		if(bigStr.length()>0) {
			this._writer.write(bigStr);
			this._writer.flush();
			this._writer.close();
		}
			
		else
			throw new Exception("Create document, Null data");
	}
	
	
	/**
 	 * Crée un document contenant un seul objet formaté
 	 * 
 	 * @param item - L’objet à exporter
 	 * @throws Exception - Si l’objet est nul ou vide, ou si une erreur d’écriture survient
 	 */
	public void createDocument(c item) throws Exception {
		String itemData = (this._formating.getHeader().isEmpty()) ? " ":this._formating.getHeader()+"\n";
	    itemData += this._formating.write(item);
	    if (itemData != null && !itemData.isEmpty()) {
	    	
	    	this._writer.write(itemData);
	    	this._writer.flush();
	    	this._writer.close();
	    }
	        
	    else
	        throw new Exception("Null or empty item data");
	}

}

