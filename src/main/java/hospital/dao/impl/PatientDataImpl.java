package hospital.dao.impl;

import hospital.entity.PatientData;
import hospital.utils.ConnectionPool;
import hospital.utils.queries.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDataImpl {
    private static final Logger log = Logger.getLogger(PatientDataImpl.class);

    public  PatientData findByPatientId(int id) {
        PatientData patientData = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.FIND_PATIENT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patientData = new PatientData(resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getInt("nurse_id"),
                        resultSet.getInt("diagnosis_id"),
                        resultSet.getInt("patient_status")
                        );
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return patientData;
    }
    public  List<PatientData> findDoctorPatients(int id) {
        List<PatientData> result = new ArrayList<PatientData>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.FIND_DOCTOR_PATIENTS);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                int nurseId = resultSet.getInt("nurse_id");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int patientStatus = resultSet.getInt("patient_status");
                PatientData user = new PatientData(patientId,doctorId, nurseId, diagnosisId, patientStatus);

                result.add(user);
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return result;
    }

    public  List<PatientData> findNursePatients(int id) {
        List<PatientData> result = new ArrayList<PatientData>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.FIND_NURSE_PATIENTS);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                int nurseId = resultSet.getInt("nurse_id");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int patientStatus = resultSet.getInt("patient_status");
                PatientData user = new PatientData(patientId,doctorId, nurseId, diagnosisId, patientStatus);

                result.add(user);
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return result;
    }

    public  void insert(int id) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.execute();
        }
        catch (SQLException throwables) {
            log.error("Error",throwables);
        }

    }

    public  List<PatientData> findAll() {
        List<PatientData> result = new ArrayList<PatientData>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PatientDataQueries.FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                int nurseId = resultSet.getInt("nurse_id");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int patientStatus = resultSet.getInt("patient_status");
                PatientData user = new PatientData(patientId,doctorId, nurseId, diagnosisId, patientStatus);

                result.add(user);
            }
        } catch (Exception e) {
            log.error("Error",e);
        }

        return result;
    }

    public  List<PatientData> findAllWhereNoDoc() {
        List<PatientData> result = new ArrayList<PatientData>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PatientDataQueries.FIND_WITHOUT_DOC);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                int nurseId = resultSet.getInt("nurse_id");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int patientStatus = resultSet.getInt("patient_status");
                PatientData user = new PatientData(patientId,doctorId, nurseId, diagnosisId, patientStatus);

                result.add(user);
            }
        } catch (Exception e) {
            log.error("Error",e);
        }

        return result;
    }

    public  List<PatientData> findAllWhereNoNurse() {
        List<PatientData> result = new ArrayList<PatientData>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PatientDataQueries.FIND_WITHOUT_NURSE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                int nurseId = resultSet.getInt("nurse_id");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int patientStatus = resultSet.getInt("patient_status");
                PatientData user = new PatientData(patientId,doctorId, nurseId, diagnosisId, patientStatus);

                result.add(user);
            }
        } catch (Exception e) {
            log.error("Error",e);
        }

        return result;
    }

    public  void updateDoctor(int doctorId, int patientId) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.ADD_DOCTOR);
            statement.setInt(1, doctorId);
            statement.setInt(2,patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
             log.error("Error",throwables);
        }
    }

    public  void updateNurse(int nurseId, int patientId) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.ADD_NURSE);
            statement.setInt(1, nurseId);
            statement.setInt(2,patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
    }

    public  void updateDiagnosis(int diagnosisId, int patientId) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.ADD_DIAGNOSIS);
            statement.setInt(1, diagnosisId);
            statement.setInt(2,patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
    }


    public  void updatePatientStatus(int id) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PatientDataQueries.UPDATE_PATIENT_STATUS);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
    }
}
