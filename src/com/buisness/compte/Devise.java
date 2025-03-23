package com.buisness.compte;

public enum  Devise{
	 	USD( "$", 0.69),
	    EUR( "€", 0.64),
	    GBP( "£", 0.54);
	
		public String signe;
		public double exchangeRate;
		Devise(String singne, double d) {
			// TODO Auto-generated constructor stub
		}

}
