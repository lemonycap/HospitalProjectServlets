package hospital.utils.doctorcommands;

import hospital.dao.impl.PrescriptionDAOImpl;
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

public class DoPrescriptionCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(DoPrescriptionCommand.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Constructor for creating new object
     * @see DoPrescriptionCommand(DAOFactory)
     */
    public DoPrescriptionCommand() {
        this.factory = new DAOFactory();
    }
    /**
     * Constructor for creating new object
     * @see DoPrescriptionCommand()
     */
    public DoPrescriptionCommand(DAOFactory factory) {
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
            log.info("Prescriptions performing");
            int id = Integer.parseInt(request.getParameter("id"));
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String prescriptionClass = prescriptionDAO.findById(id).getPrescriptionClass();
            log.debug("Prescription class is" + prescriptionClass);
            if (prescriptionClass.equals("medicine")) {
                SingleTransaction singleTransaction = factory.createSingleTransaction();
                singleTransaction.transfer(id, patientId);
            } else {
                prescriptionDAO.delete(id, patientId);
            }
            log.info("Prescriptions performed successfully");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
            log.error("An error occured while prescription performing:", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
