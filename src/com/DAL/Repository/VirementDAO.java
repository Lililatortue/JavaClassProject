package com.DAL.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.Virement;
import com.DAL.Repository.Connection.DbConnection;
//TODO: make it work
public class VirementDAO {
	
	public ArrayList<Virement> read() {
		ArrayList<Virement> virement = new ArrayList<>();
		String query = "SELECT * FROM QUEUE_VIREMENT ORDER BY VIR_DES ASC";
		try (Connection conn = DbConnection.getConnection();	
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			
			while (rs.next()) {
				virement.add(new Virement(rs));
			}
			return virement;
		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}
		return null;
	}
	
	
	
	public ArrayList<Virement> findOne(int numero) {
		ArrayList<Virement> virement = new ArrayList<>();
		String query = "SELECT * FROM QUEUE_VIREMENT WHERE VIR_DES = ?";
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setInt(1, numero);
			ResultSet rs = stmt.executeQuery();
				
			while (rs.next()) {
				virement.add(new Virement(rs));
			}
			return virement;
		} catch (SQLException e) {
			System.out.println("Error findone compte: " + e.getMessage());
		}

		return virement;
	}

	
	
	public boolean create(Virement virement) {
		String procedure = "{ call pkg_transactionManager.proc_createVirement(?, ?, ?, ?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(	1, 		virement.getSource());
			stmt.setInt(	2, 		virement.getDestinataire());
			stmt.setString(	3, 		virement.getPassword());
			stmt.setDouble(	4, 		virement.getMontant());
			stmt.setInt(	5, 		virement.getCompteId());
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
		
	}
	public boolean accept(Transaction source, Transaction destinataire, int id) {
		String procedure = "{ call pkg_transactionManager.proc_acceptVirement(?, ?, ?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(	1, 		id);
			stmt.setInt(	2, 		source.getCompteId());
			stmt.setInt(	3, 		destinataire.getCompteId());
			stmt.setDouble(	4, 		source.getMontant());

	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
		
	}
	
	
}
