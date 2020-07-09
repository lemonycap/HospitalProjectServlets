package hospital.utils.nursecommands;

import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.utils.doctorcommands.CreateDiagnosisCommand;
import hospital.utils.factories.DAOFactory;
import hospital.utils.factories.ServletCommand;
import hospital.utils.SingleTransaction;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Class, which represents performing of the diagnosis command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class NurseDoPrescrCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(NurseDoPrescrCommand.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Constructor for creating new object
     * @see NurseDoPrescrCommand(DAOFactory)
     */
    public NurseDoPrescrCommand() {
        this.factory = new DAOFactory();
    }
    /**
     * Constructor for creating new object
     * @see NurseDoPrescrCommand()
     */
    public NurseDoPrescrCommand(DAOFactory factory) {
        this.factory = factory;
    }

    /**
     * Execute command method
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException on servlet exception
     * @throws IOException on error occured while processing the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrescriptionDAOImpl prescriptionDAO = factory.createPrescriptionDao();
            log.info("Prescription performing");
            int id = Integer.parseInt(request.getParameter("id"));
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String prescriptionClass =  prescriptionDAO.findById(id).getPrescriptionClass();
            log.debug("Prescription class" + prescriptionClass);
            if (prescriptionClass.equals("medicine")) {
                SingleTransaction singleTransaction = factory.createSingleTransaction();
                singleTransaction.transfer(id,patientId);
            }
            else {
                prescriptionDAO.delete(id,patientId);
            }
            log.info("Prescription successfully performed");
            request.getServletContext().getRequestDispatcher("/nursePage").forward(request, response);
        }
        catch(Exception ex) {
            log.error("An error occured during prescription performing",ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
