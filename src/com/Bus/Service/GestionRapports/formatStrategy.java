package com.Bus.Service.GestionRapports;

public interface formatStrategy<c> {
	public String getHeader();
	public String write(c item);
	public String getExtension();
	
}
