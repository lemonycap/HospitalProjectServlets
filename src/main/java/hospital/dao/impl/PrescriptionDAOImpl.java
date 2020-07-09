package hospital.dao.impl;

import hospital.entity.prescription.Prescription;
import hospital.utils.ConnectionPool;
import hospital.utils.queries.PrescriptionQueries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class, which represents Prescription DAO
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class PrescriptionDAOImpl {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(PrescriptionDAOImpl.class);
    /**
     * Find all prescriptions
     * @return list of prescriptions
     */
    public  List<Prescription> findAll() {
        List<Prescription> result = new ArrayList<Prescription>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PrescriptionQueries.FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    int id = resultSet.getInt("id_prescription");
                    String name = resultSet.getString("prescription_name");
                    String prescriptionClass = resultSet.getString("prescription_class");
                    Prescription prescription = new Prescription(id,name,prescriptionClass);

                    result.add(prescription);
            }
        } catch (Exception e) {
            log.error("Error",e);
        }
        return result;
    }
    /**
     * Find prescription by id
     * @return corresponding prescription
     */
    public  Prescription findById(int id) {
        Prescription prescription = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                prescription = new Prescription(resultSet.getInt("id_prescription"),
                        resultSet.getString("prescription_name"),
                        resultSet.getString("prescription_class"));
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return prescription;
    }


    /**
     * Find patients prescription history
     * @return prescription history - list of prescriptions id
     */
    public  List<Integer> findByPatientHistory(int id) {
        List<Integer> result = new ArrayList<Integer>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.FIND_ALL_HISTORY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_prescription = resultSet.getInt("prescription_id");
                result.add(id_prescription);
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return result;
    }
    /**
     * Find patients new prescriptions
     * @return new prescriptions - list of prescriptions id
     */
    public  List<Integer> findByActivePrescriptions(int id) {
        List<Integer> result = new ArrayList<Integer>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.FIND_ALL_NEW);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_prescription = resultSet.getInt("prescription_id");
                result.add(id_prescription);
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return result;
    }

    /**
     * Find prescription by prescription class
     * @return list of corresponding prescriptions
     */
    public  List<Prescription> findByClass(String prescriptionClass) {
        List<Prescription> result = new ArrayList<Prescription>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.FIND_BY_CLASS);
            statement.setString(1, prescriptionClass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_prescription");
                String name = resultSet.getString("prescription_name");
                String prescriptClass = resultSet.getString("prescription_class");
                Prescription prescription = new Prescription(id,name,prescriptClass);

                result.add(prescription);
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return result;
    }
    /**
     * Insert patient's history in table
     */
    public  void insertHistory(int prescriptionId, int patientId) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.INSERT_PRESCRIPTION_HISTORY);
            statement.setInt(1,patientId);
            statement.setInt(2,prescriptionId);
            statement.execute();
        }
        catch (SQLException throwables) {
            log.error("Error",throwables);
        }

    }
    /**
     * Insert new prescriptions for patient in table
     */
    public  void insertNewPrescriptions(int prescriptionId, int patientId) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.INSERT_NEW_PRESCRIPTION);
            statement.setInt(1,patientId);
            statement.setInt(2,prescriptionId);
            statement.execute();
        }
        catch (SQLException throwables) {
            log.error("Error",throwables);
        }
    }
    /**
     * Delete prescription for particular patient
     */
    public  void delete(int id,int patientId) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PrescriptionQueries.DELETE_PRESCRIPTION);
            statement.setInt(1,id);
            statement.setInt(2,patientId);
            statement.execute();
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
    }


}
