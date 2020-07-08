package hospital.utils.doctorcommands;

import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.utils.DAOFactory;
import hospital.utils.ServletCommand;
import hospital.utils.SingleTransaction;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoPrescriptionCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(DoPrescriptionCommand.class);

    DAOFactory factory;
    public DoPrescriptionCommand() {
        this.factory = new DAOFactory();
    }
    public DoPrescriptionCommand(DAOFactory factory) {
        this.factory = factory;
    }

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
