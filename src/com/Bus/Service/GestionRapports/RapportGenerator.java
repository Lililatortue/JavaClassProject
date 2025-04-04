package com.Bus.Service.GestionRapports;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class RapportGenerator<c>{
	
	private formatStrategy<c> _formating;
	private PrintWriter _writer;
	
	//constructeur par default
	public RapportGenerator() throws IOException {
		this._formating = new TXTStrategy<c>(null);
		this._writer = new PrintWriter(new FileWriter("./src/Printer/default"+_formating.getExtension(),false));
	}
	
	//constructeur normal
	public RapportGenerator(formatStrategy<c> strategy,String path) throws IOException {
		this.setFormating(strategy);
		this._writer = new PrintWriter(new FileWriter(path,false));
	}
	
	
	public void setFormating(formatStrategy<c> strategy) {
		this._formating = strategy;
	}
	
	
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
	
	
	//fonction overloaded prends 1 seul item
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

