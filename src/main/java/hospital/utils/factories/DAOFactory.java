package hospital.utils.factories;

import hospital.dao.impl.*;
import hospital.utils.SingleTransaction;
/**
 * Class, which represents DAO factory.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class DAOFactory {
    /**
     * Returns new user DAO.
     * @return new instance of userDAOImpl
     */
    public UserDAOImpl createUserDao() {
        return new UserDAOImpl();
    }
    /**
     * Returns new patient data DAO.
     * @return new instance of patientDataImpl
     */
    public PatientDataImpl createPatientDataDao() {
        return new PatientDataImpl();
    }
    /**
     * Returns new prescription DAO.
     * @return new instance of prescriptionDAOImpl
     */
    public PrescriptionDAOImpl createPrescriptionDao() {
        return  new PrescriptionDAOImpl();
    }
    /**
     * Returns new diagnosis DAO.
     * @return new instance of diagnosisDAOImpl
     */
    public DiagnosisDAOImpl createDiagnosisDao() {
        return new DiagnosisDAOImpl();
    }
    /**
     * Returns new role DAO.
     * @return new instance of roleDAOImpl
     */
    public RoleDAOImpl createRoleDao() {
        return new RoleDAOImpl();
    }
    /**
     * Returns new single transaction for medical prescriptions
     * @return new instance of SingleTransaction
     */
    public SingleTransaction createSingleTransaction() {
        return new SingleTransaction();
    }
}
