package com.Bus.Service.GestionRapports;

public class TXTStrategy<c> implements formatStrategy<c>{
	
	private String header;
	
	public TXTStrategy(String item){
		if(item==null)
			this.header = "";
		else
			this.header = item;
	}
	@Override
	public String write(c item) {
		return item.toString();
	}

	@Override
	public String getHeader() {
		return header;
	}
	@Override
	public String getExtension() {
		
		return ".txt";
	}

}
