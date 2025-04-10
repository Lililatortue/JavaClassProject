package com.Bus.Model.Compte;

public enum CompteType{
	CHCK("CHECK"),
	CRED("CREDIT"),
	LGNCRED("LIGNE DE CREDIT"),
	DEV("DEVISE"),
	EPRGN("EPARGNE"),
	VRMNT("VIREMENT");
	private final String displayName;

    // Constructor to set the display name
    CompteType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
