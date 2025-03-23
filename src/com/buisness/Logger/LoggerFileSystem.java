package com.buisness.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class LoggerFileSystem implements LoggerStrategy {

	private FileWriter fileWriter=null; 
	public LoggerFileSystem(String path) {
		try {
		this.setPath(path);
		} catch(IOException e) {
			  System.out.println("file not found" + path);
		}
	}
	
	private void setPath(String path) throws IOException {
			this.fileWriter= new FileWriter(path,true);
	}
	@Override
	public void log(Logger logger){
		try {
			this.fileWriter.write(logger.toString()+"\n");
			this.fileWriter.flush(); 
		} catch(IOException e) {
			System.out.println("mistake at LoggerStrategyFileSystem/logfunction");
		}
		
	}

}
