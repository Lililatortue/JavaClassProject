package com.DAL.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteCheque;
import com.Bus.Model.Compte.CompteCredit;
import com.Bus.Model.Compte.CompteDevise;
import com.Bus.Model.Compte.CompteEpargne;
import com.Bus.Model.Compte.CompteInteret;
import com.Bus.Model.Compte.LigneDeCredit;
import com.DAL.Repository.Connection.DbConnection;
import com.DAL.Repository.Exception.*;

public class CompteRepository implements IRepository<Compte> {
	//private RecordStrategy<Compte> _strategy;
	
	public ArrayList<Compte> read() {
		ArrayList<Compte> compte = new ArrayList<>();
		String query = "SELECT * FROM View_COMPTES_PAR_CLIENT ORDER BY USR_ID ASC";
		try (Connection conn = DbConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				if(rs.getString("CPT_TYPE").equals("CREDIT")) {	
					compte.add(new CompteCredit(rs));
				} 
				else if(rs.getString("CPT_TYPE").equals("CHEQUE"))
				{
					compte.add(new CompteCheque(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("DEVISE"))
				{
					compte.add(new CompteDevise(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("EPARGNE"))
				{
					compte.add(new CompteEpargne(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("EPARGNE"))
				{
					compte.add(new LigneDeCredit(rs));
				}
			}

		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}

		return compte;
	}
	
	public ArrayList<Compte> findOne(int id) {
		ArrayList<Compte> compte = new ArrayList<>();
		String query = "SELECT * FROM T_COMPTE WHERE CLI_ID = ?";
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if(rs.getString("CPT_TYPE").equals("CREDIT")) {	
					compte.add(new CompteCredit(rs));
				} 
				else if(rs.getString("CPT_TYPE").equals("CHEQUE"))
				{
					compte.add(new CompteCheque(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("DEVISE"))
				{
					compte.add(new CompteDevise(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("EPARGNE"))
				{
					compte.add(new CompteEpargne(rs));
				}
				else if(rs.getString("CPT_TYPE").equals("EPARGNE"))
				{
					compte.add(new LigneDeCredit(rs));
				}
			}

		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}

		return compte;
	}
	
	@Override
	public boolean create(Compte compte) {
		String procedure = "{ call insert_compte(?, ?, ?, ?, ?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			
			stmt.setString(1, compte.getType().toString());
			stmt.setDouble(2, compte.getSolde());
			stmt.setInt(3, compte.getClientId());
			
			String devise = (compte instanceof CompteDevise) ?  ((CompteDevise) compte).getDevise().toString(): null;
			stmt.setString(4,devise);
			
			if(compte instanceof CompteInteret) {
				stmt.setDouble(5,  ((CompteInteret)compte).getTauxInteretAnnuel());				
				stmt.setDouble(6, ((CompteInteret)compte).getLimite());
			}
			
			
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}		
	}




	@Override
	public boolean delete(Compte compte) throws InvariantException {
		String procedure = "{ call delete_compte(?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			
			stmt.setInt(1, compte.getCompteId());
	        stmt.execute();
		    return true;
		        	        	
		}catch (SQLException e) {
			System.out.println("Delete error: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Compte item) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	
	
}
