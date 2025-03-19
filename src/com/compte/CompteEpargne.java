package com.compte;

import java.time.LocalDate;
import java.time.Month;

import com.Observer.TimeObserver;

/*
 * 0.005% de taux interet mensuelle
 * 
 * 
 * */
public class CompteEpargne extends AbstractCompte implements TimeObserver {
	
	private final double tauxInteret=0.005;
	protected Month month;
	
	//class to create
	public CompteEpargne(String numero, Double solde){
		super(numero, solde);
		this.month=LocalDate.now().getMonth();
	}
	
	//deserialize class constructor
	protected CompteEpargne(String numero, Double solde,Month month) {
		super(numero, solde);
		this.month = month;
	}
	
	protected void setMonth(Month month) {
		this.month=month;
	}
	
	
	public void calculerInteret(){
		this.solde+=solde*tauxInteret;
	}
	
	
	//TimeObserver methods
	@Override
	public void update(Month currentMonth) {
		int month_passed=currentMonth.compareTo(this.month);
		this.solde= solde +(1 * this.tauxInteret)*month_passed;
	}

	@Override
	public Boolean getState() {
		return this.month;
	}
}
