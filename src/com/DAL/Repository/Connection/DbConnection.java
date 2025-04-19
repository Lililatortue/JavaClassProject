package com.DAL.Repository.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "C##William";
    private static final String PASS = "SomewhereInTheSea";

    /**
     * takes credential and connects to dataBase
     * 
     * @return connection
     * @throws SQLException if error occurs in attempt of connection
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

		}
    	return DriverManager.getConnection(URL, USER, PASS);

    }
}
