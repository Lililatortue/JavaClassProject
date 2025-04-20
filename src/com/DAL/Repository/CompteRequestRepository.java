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
import com.DAL.Repository.Exception.InvariantException;

public class CompteRequestRepository implements IRepository<Compte> {
	//private RecordStrategy<Compte> _strategy;
	
	public ArrayList<Compte> read() {
		ArrayList<Compte> compte = new ArrayList<>();
		String query = "SELECT * FROM QUEUE_COMPTE_REQUEST ORDER BY CLI_ID ASC";
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
				else if(rs.getString("CPT_TYPE").equals("LGNECRED"))
				{
					compte.add(new LigneDeCredit(rs));
				}
			}

		} catch (SQLException e) {
			System.out.println("Error getting read compte: " + e.getMessage());
		}

		return compte;
	}
	
	public ArrayList<Compte> findOne(int id) {
		ArrayList<Compte> compte = new ArrayList<>();
		String query = "SELECT * FROM QUEUE_COMPTE_REQUEST WHERE CLI_ID = ?";
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
				else if(rs.getString("CPT_TYPE").equals("LGNECRED"))
				{
					compte.add(new LigneDeCredit(rs));
				}
			}

		} catch (SQLException e) {
			System.out.println("Error getting findone compte: " + e.getMessage());
		}

		return compte;
	}
	
	@Override
	public boolean create(Compte compte) {
		String procedure = "{ call pkg_compteManager.proc_createAccountRequest(?, ?, ?, ?, ?, ?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			
			stmt.setString(1, compte.getType().toString());
			stmt.setInt(2, compte.getClientId());
			
			String devise = (compte instanceof CompteDevise) ?  ((CompteDevise)compte).getDevise().toString() : null;
			stmt.setString(3,devise);	
	
			Double limite = (compte instanceof CompteInteret) ? ((CompteInteret)compte).getLimite() : null;
			Double taux = (compte instanceof CompteInteret) ?((CompteInteret)compte).getTauxInteretAnnuel():null;
			stmt.setObject(4, limite, java.sql.Types.DOUBLE);
			stmt.setObject(5, taux, java.sql.Types.DOUBLE);
			
			Double frais = (compte instanceof CompteCheque) ? ((CompteCheque)compte).getFraisTransaction(): null;
			Integer restante = (compte instanceof CompteCheque) ? ((CompteCheque)compte).getTransactionsRestante(): null;
			stmt.setObject(6, frais, java.sql.Types.DOUBLE);
			stmt.setObject(7, restante, java.sql.Types.INTEGER);
			
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert  error: " + e.getMessage());
			return false;
		}		
	}




	@Override
	public boolean delete(Compte compte) throws InvariantException {
		String procedure = "{ call pkg_compteManager.proc_denyAccountRequest(?,?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			
			stmt.setInt(1, compte.getClientId());
			stmt.setString(2, compte.getType().toString());
	        stmt.execute();
		    return true;
		        	        	
		}catch (SQLException e) {
			System.out.println("Delete error: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Compte compte) {
		String procedure = "{ call pkg_compteManager.proc_acceptAccountRequest(?,?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setInt(1, compte.getClientId());
			stmt.setString(2, compte.getType().toString());
	        stmt.execute();
		    return true;
		        	        	
		}catch (SQLException e) {
			System.out.println("Delete error: " + e.getMessage());
			return false;
		}
	}

}
