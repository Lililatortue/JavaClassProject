package com.DAL.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.Bus.Model.Compte.*;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class CompteRepository implements IRepository<Compte> {
    private Connection conn;

    public CompteRepository() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle non trouvé", e);
        }

        // conneceter a la BD
        this.conn = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE", "TON_UTILISATEUR", "TON_MOT_DE_PASSE"
        );
    }

    @Override
    public void create(Compte compte) throws KeyConstraintException {
        String checkQuery = "SELECT COUNT(*) FROM T_COMPTE WHERE CPT_NUMERO = ? AND CPT_TYPE = ?";
        String insertQuery = "INSERT INTO T_COMPTE (CPT_NUMERO, CPT_TYPE, CPT_SOLDE, CPT_DATE_OUV, CPT_DEVISE, CPT_LIMITE, CPT_TAUX, CLI_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery)
        ) {
            String numero = generateNumero(compte);
            checkStmt.setString(1, numero);
            checkStmt.setString(2, compte.getType().toString());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                throw new KeyConstraintException("Ce compte existe déjà.");
            }

            insertStmt.setString(1, numero);
            insertStmt.setString(2, compte.getType().toString());
            insertStmt.setDouble(3, compte.getSolde());
            insertStmt.setDate(4, java.sql.Date.valueOf(compte.getDateOuverture()));

            // Valeurs spécifiques selon le type
            switch (compte.getType()) {
                case CHCK:
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setNull(6, Types.DOUBLE);
                    insertStmt.setNull(7, Types.DOUBLE);
                    break;
                case DEV:
                    insertStmt.setString(5, ((CompteDevise) compte).getDevise().name());
                    insertStmt.setNull(6, Types.DOUBLE);
                    insertStmt.setNull(7, Types.DOUBLE);
                    break;
                case CRED:
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setDouble(6, ((CompteCredit) compte).getLimite());
                    insertStmt.setDouble(7, ((CompteInteret) compte).getTauxInteretAnnuel());
                    break;
                case EPRGN:
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setDouble(6, ((CompteEpargne) compte).getLimite());
                    insertStmt.setDouble(7, ((CompteInteret) compte).getTauxInteretAnnuel());
                    break;
                case LGNCRED:
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setNull(6, Types.DOUBLE);
                    insertStmt.setDouble(7, ((CompteInteret) compte).getTauxInteretAnnuel());
                    break;
                default:
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setNull(6, Types.DOUBLE);
                    insertStmt.setNull(7, Types.DOUBLE);
            }

            insertStmt.setInt(8, compte.getClientId());

            insertStmt.executeUpdate();

        } catch (SQLException e) {
            throw new KeyConstraintException("Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Compte> read() {
        ArrayList<Compte> comptes = new ArrayList<>();
        String query = "SELECT * FROM T_COMPTE";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                CompteType type = CompteType.valueOf(rs.getString("CPT_TYPE"));
                int clientId = rs.getInt("CLI_ID");
                double solde = rs.getDouble("CPT_SOLDE");
                LocalDate dateOuverture = rs.getDate("CPT_DATE_OUV").toLocalDate();

                Compte compte = null;

                switch (type) {
                    case CHCK:
                        compte = new CompteCheque(clientId, solde, 0.50); // frais par défaut
                        break;
                    case CRED:
                        compte = new CompteCredit(clientId, rs.getDouble("CPT_TAUX"), rs.getDouble("CPT_LIMITE"));
                        break;
                    case EPRGN:
                        compte = new CompteEpargne(clientId, rs.getDouble("CPT_TAUX"), rs.getDouble("CPT_LIMITE"));
                        break;
                    case DEV:
                        Devise devise = Devise.valueOf(rs.getString("CPT_DEVISE"));
                        compte = new CompteDevise(clientId, solde, devise);
                        break;
                    case LGNCRED:
                        compte = new LigneDeCredit(clientId, rs.getDouble("CPT_TAUX"));
                        break;
                    default:
                        System.out.println("Type de compte inconnu : " + type);
                }

                if (compte != null) {
                	  comptes.add(compte);
                }

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comptes;
    }

    @Override
    public void update(Compte compte) {
        String query = "UPDATE T_COMPTE SET CPT_SOLDE = ? WHERE CPT_NUMERO = ? AND CPT_TYPE = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, compte.getSolde());
            stmt.setString(2, generateNumero(compte));
            stmt.setString(3, compte.getType().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Compte compte) throws InvariantException {
        String query = "DELETE FROM T_COMPTE WHERE CPT_NUMERO = ? AND CPT_TYPE = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, generateNumero(compte));
            stmt.setString(2, compte.getType().toString());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new InvariantException("Ce compte n'existe pas.");
            }
        } catch (SQLException e) {
            throw new InvariantException("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    private String generateNumero(Compte compte) {
        // Génère un identifiant de compte factice basé sur le type et clientId
        return compte.getType().name().substring(0, 3).toUpperCase() + "-" + compte.getClientId();
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
