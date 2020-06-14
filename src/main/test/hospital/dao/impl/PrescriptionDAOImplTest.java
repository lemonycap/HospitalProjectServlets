package hospital.dao.impl;

import hospital.entity.prescription.Prescription;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PrescriptionDAOImplTest {

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByClass() {
        List<Prescription> medicine = PrescriptionDAOImpl.findByClass("medicine");
        for (int i = 0; i < medicine.size(); i ++) {
            System.out.println(medicine.get(i).getName());
        }
    }

    @Test
    public void insert() {
    }

    @Test
    public void findByPatientId() {
    /*  List<Integer> prescriptions =  PrescriptionDAOImpl.findByPatientId(1);
        for (int i = 0; i < prescriptions.size(); i ++) {
            System.out.println(prescriptions.get(i));
        }*/
    }


   /*/ public void findByPrescriptionsById() {
    //    List<Prescription> prescriptions1 = PrescriptionDAOImpl.findByPrescriptionsById(2);
        for (int i = 0; i < prescriptions1.size(); i ++) {
            System.out.println(prescriptions1.get(i).getName());
        }
        System.out.println(prescriptions1.isEmpty());
    }

    @Test
    public void findByName() {
       Prescription prescription = PrescriptionDAOImpl.findByName("otrivin");
       System.out.println(prescription.getName() + prescription.getAmountOfMedicine());
    }*/
}