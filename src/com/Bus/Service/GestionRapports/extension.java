package com.Bus.Service.GestionRapports;

public enum extension {
	CSV(".csv"),
	TXT(".txt"),
	JSON(".json");
	
	private final String ext;
	
	extension(String extension) {
		 this.ext = extension;
	}
	public String getExtension() {
        return ext;
    }
}
