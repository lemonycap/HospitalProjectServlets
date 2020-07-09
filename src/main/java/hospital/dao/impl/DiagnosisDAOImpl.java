package hospital.dao.impl;

import hospital.entity.diagnosis.Diagnosis;
import hospital.utils.ConnectionPool;
import hospital.utils.queries.DiagnosisQueries;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class, which represents Diagnosis DAO
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public class DiagnosisDAOImpl {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(DiagnosisDAOImpl.class);

    /**
     * Find all diagnoses
     * @return list of diagnoses
     */
    public  List<Diagnosis> findAll() {
        List<Diagnosis> result = new ArrayList<Diagnosis>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DiagnosisQueries.FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_diagnosis");
                String name = resultSet.getString("diagnosis_name");
                String difficulty = resultSet.getString("diagnosis_difficulty");
                Diagnosis diagnosis = new Diagnosis(id,name,difficulty);

                result.add(diagnosis);
            }
        } catch (Exception e) {
           log.error("Error",e);
        }

        return result;
    }
    /**
     * Find diagnosis by id
     * @return corresponding diagnosis
     */
    public  Diagnosis findById(int id) {
        Diagnosis diagnosis = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DiagnosisQueries.FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                diagnosis = new Diagnosis(resultSet.getInt("id_diagnosis"),
                        resultSet.getString("diagnosis_name"),
                        resultSet.getString("diagnosis_difficulty"));
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return diagnosis;
    }
}
