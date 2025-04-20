package com.DAL.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.DbConnection;
//TODO: make it work
public class VirementDAO {
	
	public ArrayList<Transaction> read() {
		ArrayList<Transaction> transaction = new ArrayList<>();
		String query = "SELECT * FROM T_TRANSACTION ORDER BY USR_ID ASC";
		try (Connection conn = DbConnection.getConnection();	
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			
			while (rs.next()) {
				transaction.add(new Transaction(rs));
			}
			return transaction;
		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}
		return null;
	}
	
	
	
	public ArrayList<Transaction> findOne(Compte compte) {
		ArrayList<Transaction> transaction = new ArrayList<>();
		String query = "SELECT * FROM T_TRANSACTION WHERE CPT_NUMERO = ?";
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setInt(1, compte.getCompteId());
			ResultSet rs = stmt.executeQuery();
				
			while (rs.next()) {
				transaction.add(new Transaction(rs));
			}
			return transaction;
		} catch (SQLException e) {
			System.out.println("Error findone compte: " + e.getMessage());
		}

		return transaction;
	}

	
	
	public boolean create(Transaction item) {
		String procedure = "{ call pkg_transactionManager.proc_buyTransaction(?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(1, item.getCompteId());
			stmt.setDouble(2, item.getMontant());
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
		
	}
	
	
	
	public boolean accept(Transaction item) {
		String procedure = "{ call pkg_transactionManager.proc_buyTransaction(?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(1, item.getCompteId());
			stmt.setDouble(2, item.getMontant());
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
		
	}

	public boolean refuse(Transaction item) {
		String procedure = "{ call pkg_transactionManager.proc_sellTransaction(?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(1, item.getCompteId());
			stmt.setDouble(2, item.getMontant());
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
	}
}
