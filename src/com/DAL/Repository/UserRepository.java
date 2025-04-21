package com.DAL.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Bus.Model.Client.Client;
import com.Bus.Model.Client.Gestionnaire;
import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.Connection.DbConnection;
import com.DAL.Repository.Exception.*;


public class UserRepository implements IRepository<Utilisateur>{

	public ArrayList<Utilisateur> read() {
		ArrayList<Utilisateur> utilisateur = new ArrayList<>();
		String query = "SELECT * FROM view_utilisateur ORDER BY USR_ID ASC";
		try (Connection conn = DbConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				if(rs.getString("USR_ROLE").equals("CLIENT")) {	
					utilisateur.add(new Client(rs));
				} else {
					utilisateur.add(new Gestionnaire(rs));
				}
					
			}
		} catch (SQLException e) {
			System.out.println("Error getting departments: " + e.getMessage());
		}

		return utilisateur;
	}
	
	
	public Utilisateur findOne(String email) {
		String query = "SELECT * FROM view_utilisateur WHERE USR_EMAIL = ?";
		Utilisateur utilisateur;
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				if(rs.getString("USR_ROLE").equals("Client")) {	
					 utilisateur= new Client(new Client(rs));
					 return utilisateur;
				} else {
					 utilisateur = new Gestionnaire(new Gestionnaire(rs));
					 return utilisateur;
				}
					
			}
		} catch (SQLException e) {
			System.out.println("Error getting user: " + e.getMessage());
		}
		return null;

	}
	
	@Override
	public boolean create(Utilisateur user) {
		String procedure = "{ call pkg_userManager.proc_insertUser(?, ?, ?, ?, ?, ?, ?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {

			stmt.setString(1, user.getNom());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3, user.getAdresse());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getNip());
			stmt.setString(6, user.getRole());
			
			String tel = (user instanceof Client) ? ((Client) user).getTelephone() : "";
			stmt.setString(7, tel);
	        stmt.execute();
	        return true;
	        
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
			return false;
		}
		
	}
	
	@Override
	public boolean update(Utilisateur user) {
		String procedure = "{ call pkg_userManager.proc_updateUser(?, ?, ?, ?, ?, ?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
				stmt.setString(1, user.getNom());
				stmt.setString(2, user.getPrenom());
				stmt.setString(3, user.getAdresse());
				stmt.setString(4, user.getEmail());
				stmt.setString(5, "CLIENT");

				String tel = (user instanceof Client) ? ((Client) user).getTelephone() : "";
				
				
				stmt.setString(6, tel);  
				stmt.execute();
				return true;
			
		}catch (SQLException e) {
			System.out.println("Update error: " + e.getMessage());
			return false;
		}	
	}

	@Override
	public boolean delete(Utilisateur user) throws InvariantException {
		String procedure = "{ call pkg_userManager.proc_deleteUser(?) }";
		try (Connection conn = DbConnection.getConnection(); 
				CallableStatement stmt = conn.prepareCall(procedure)) {
			stmt.setString(1, user.getEmail());
			stmt.execute();
		    return true;
			
		}catch (SQLException e) {
			System.out.println("Delete error: " + e.getMessage());
			return false;
		}
	}

}
