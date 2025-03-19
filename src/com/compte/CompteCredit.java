package com.compte;

import java.time.LocalDate;
import java.time.Month;

import com.Observer.TimeObserver;

public class CompteCredit extends AbstractCompte implements TimeObserver/*, AutoCloseable*/ {
	
	private final double tauxInteret=0.05;
	private int year;
	
	private double interetMensuel;
	private double interetDu;
	
	private LocalDate DuDate;
	
	public CompteCredit(String numero, Double montantDu, LocalDate DuDate) throws Exception {
		super(numero, montantDu);
		this.setDuDate(DuDate);
		this.setYear();
		this.interetMensuel=this.calculInteretMensuel();
	}
	
	protected CompteCredit(String numero, Double solde, Double plafond) throws Exception {
		super(numero, solde);
	}
	
	
	public void setDuDate( LocalDate DuDate) {
		if(DuDate.getYear()>=LocalDate.now().getYear()) {
			this.DuDate = DuDate;
		}	
	}
	
	public void setYear() {
		this.year=this.DuDate.getYear()-LocalDate.now().getYear();	
	}
	
	private final Double calculInteretMensuel() {
		double interetMensuel=tauxInteret/12 * this.year;
		return interetMensuel;		
	}
	
	
	public void payerMontantDu(Double montant) {
		if(this.interetDu<=montant) {
			montant-=this.interetDu;
			this.interetDu=0;
			this.solde-=montant;
		} else {
			this.interetDu-=montant;
		}
	}
	
	//system pour fermer le compte
	//public void close{
	//	super();
	//}
		//TimeObserver methods
		@Override
		public void update(Month currentMonth) {
			this.interetDu+=this.interetMensuel;
		}
		@Override
		public Boolean getState() {
			return this.interetDu>0;
		}

		
		
}
