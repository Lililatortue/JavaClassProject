package com.DAL.Repository.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Predicate;

public  class SerializeRecord<c> implements RecordStrategy<c>{
	String connectionString;

	public SerializeRecord(String connectionString) {
		 this.connectionString = connectionString;	
	}
	
	@Override
	public void commit(ArrayList<c> c)  {
		try {
			FileOutputStream fos = new FileOutputStream(this.connectionString);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<c> read() {
		ArrayList<c> temp = new ArrayList<c>();
		
		//check if file is empty
		File file = new File(this.connectionString);
		if(file.length() ==0){
			return temp;
		}
		//send data
		try (FileInputStream fis = new FileInputStream(file);ObjectInputStream ois = new ObjectInputStream(fis)){
		
			while(fis.available() > 0) {
				temp = (ArrayList<c>) ois.readObject();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void create(Predicate<c> predicate) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(c item) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(c item) {
		// TODO Auto-generated method stub	
	}
}
