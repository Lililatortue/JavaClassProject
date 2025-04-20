package com.DAL.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Predicate;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteCheque;
import com.Bus.Model.Compte.CompteCredit;
import com.Bus.Model.Compte.CompteDevise;
import com.Bus.Model.Compte.CompteEpargne;
import com.Bus.Model.Compte.CompteInteret;
import com.Bus.Model.Compte.LigneDeCredit;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.DbConnection;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class TransactionRepository implements IRepository<Transaction>{
	
	public ArrayList<Transaction> read() {
		ArrayList<Transaction> transaction = new ArrayList<>();
		String query = "SELECT * FROM View_COMPTES_PAR_CLIENT ORDER BY USR_ID ASC";
		try (Connection conn = DbConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {


		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}
		return null;

	}
	
	public ArrayList<Transaction> findOne(int id) {
		ArrayList<Compte> compte = new ArrayList<>();
		String query = "SELECT * FROM T_COMPTE WHERE CLI_ID = ?";
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
		

		} catch (SQLException e) {
			System.out.println("Error getting compte: " + e.getMessage());
		}

		return compte;
	}
	
	@Override
	public boolean create(Transaction compte) {
		String procedure = "{ call insert_compte(?, ?, ?, ?, ?, ?)}";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {

			

			
			
	        stmt.execute();
	        return true;
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}	
		
	}

	@Override
	public boolean update(Transaction item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Transaction item) throws InvariantException {
		// TODO Auto-generated method stub
		return false;
	}

}
