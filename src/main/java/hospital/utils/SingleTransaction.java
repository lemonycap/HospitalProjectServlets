package hospital.utils;

import hospital.entity.prescription.Prescription;
import hospital.utils.queries.PrescriptionQueries;
import org.apache.log4j.Logger;

import java.sql.*;

public class SingleTransaction {
    private static final Logger log = Logger.getLogger(SingleTransaction.class);

    private static Connection connectionTo;
    private static Connection connectionFrom;

    private static SingleTransaction instance = null;

    public synchronized static SingleTransaction getInstance() {
        if (instance == null) {
            instance = new SingleTransaction();
            instance.getConnectionTo();
            instance.getConnectionFrom();
        }
        return instance;
    }

    private static Connection getConnectionFrom() {
        try {
            connectionFrom = ConnectionPool.getConnection();
            connectionFrom.setAutoCommit(false);
        } catch (SQLException e) {
           log.error("SQLException: " + e.getMessage()
                    + "SQLState: " + e.getSQLState());
        }
        return connectionFrom;
    }

    private static Connection getConnectionTo() {
        try {
            connectionTo = ConnectionPool.getConnection();
            connectionTo.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("SQLException: " + e.getMessage()
                    + "SQLState: " + e.getSQLState());
        }
        return connectionTo;
    }

    public  void transfer(int id,int patientId) throws SQLException {
        connectionFrom = getConnectionFrom();
        connectionTo = getConnectionTo();
        Statement stFrom = null;
        PreparedStatement stTo = null;
        try {
            stFrom = connectionFrom.createStatement();
            stTo = connectionTo.prepareStatement(PrescriptionQueries.FIND_MED_LIST);

            ResultSet rsFrom = stFrom.executeQuery("select prescription_name from prescription_table where id_prescription = " + id);
            String name = null;
            while (rsFrom.next()) {
                name = rsFrom.getString("prescription_name");
            }
            Prescription aimPrescription = null;
            stTo.setString(1,name);
            ResultSet rsTo = stTo.executeQuery();
            while (rsTo.next()) {
                aimPrescription = new Prescription(rsTo.getString("medicine_name"),
                        rsTo.getInt("medicine_amount"));
            }
            stFrom.executeUpdate("delete from new_prescriptions where prescription_id = " + id + " and patient_id =  " + patientId);
            stTo = connectionTo.prepareStatement(PrescriptionQueries.UPDATE_MED_LIST);
            stTo.setInt(1,(aimPrescription.getAmountOfMedicine() - 1));
            stTo.setString(2,aimPrescription.getName());
            stTo.executeUpdate();

            connectionFrom.commit();
            connectionTo.commit();

        } catch (SQLException e) {
            log.error("SQLException: " + e.getMessage()
                    + "SQLState: " + e.getSQLState());
            connectionFrom.rollback();
            connectionTo.rollback();
        } finally {
            if (stFrom != null) {
                try {
                    stFrom.close();
                } catch (SQLException ex) {
                    log.error("Error", ex);
                }
            }
            if (stTo != null) {
                try {
                    stTo.close();
                } catch (SQLException ex) {
                    log.error("Error", ex);
                }
            }

        }
    }
}
