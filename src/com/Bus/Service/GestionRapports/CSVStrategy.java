package com.Bus.Service.GestionRapports;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CSVStrategy<c> implements formatStrategy<c>{
	
	private StringBuilder headerString = new StringBuilder();
	ArrayList<Field> primitifFields; 
	ArrayList<Field>objectFields; 
	
	public CSVStrategy(c Header) {
		//initialisation
		primitifFields = new ArrayList<Field>();
		objectFields = new ArrayList<Field>(); 
		
        for (Class<?> c = Header.getClass(); c != null; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                
                //regarde si les types sont primitif ou non
                Class<?> type = field.getType();
                if (type.isPrimitive()) {
            	   primitifFields.add(field);
               } else {
            	   objectFields.add(field);
               }
            }
        }
        setHeader(Header);
	}
	
	
	@Override
	public String write(c item) {	
		//reflection
		StringBuilder contentString = new StringBuilder();
		Class<?> clazz = item.getClass();
		boolean first = true;
		for(Field field : primitifFields) {
			 contentString.append((first == true) ?  "": ",");
			 first=false;
			try {
				var itemValue=field.get(item);
				contentString.append(itemValue == null ? "" : itemValue);
			
			} catch(IllegalArgumentException | IllegalAccessException e) {
				System.out.print(e.getMessage());
			}
		}
		
		for(Field field : objectFields) {
			 contentString.append((first == true) ?  "": ",");
			 first=false;
			try {
				Object itemValue=field.get(item);
				contentString.append(itemValue == null ? "" : itemValue);
			
			} catch(IllegalArgumentException | IllegalAccessException e) {
				System.out.print(e.getMessage());
			}
		}
		return contentString.toString();	
	}
	//setters
	private void setHeader(c item) {
		boolean first = true;
		for(Field field : primitifFields) {
			headerString.append((first == true) ? "" : ",");	
			first=false;
			headerString.append(field.getName());
		}
		
		for(Field field : objectFields) {
			headerString.append((first == true) ? "" : ",");	
			first=false;
			headerString.append(field.getName());
		}
	}
	
	//getters
	@Override
	public String getHeader() {
		return headerString.toString();
	};

   


	@Override
	public String getExtension() {	
		return ".csv";
	}

}
